package com.sweetcat.userorder.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.usercoupon.CouponInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserAddressRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.domainevent.userinfo.UserChangedAddressEvent;
import com.sweetcat.commons.exception.*;
import com.sweetcat.userorder.application.command.AddOrderCommand;
import com.sweetcat.userorder.application.command.CommodityCouponMap;
import com.sweetcat.userorder.application.event.publish.DomainEventPublisher;
import com.sweetcat.userorder.application.rpc.CommodityInfoRpc;
import com.sweetcat.userorder.application.rpc.StoreInfoRpc;
import com.sweetcat.userorder.application.rpc.UserCouponInfoRpc;
import com.sweetcat.userorder.application.rpc.UserInfoRpc;
import com.sweetcat.userorder.domain.order.entity.*;
import com.sweetcat.userorder.domain.order.repository.OrderRepository;
import com.sweetcat.userorder.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.userorder.infrastructure.service.snowflake_service.SnowFlakeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-23:04
 * @version: 1.0
 */
@Service
@Slf4j
public class OrderApplicationService {
    Logger logger = LoggerFactory.getLogger(OrderApplicationService.class);
    private OrderRepository orderRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private UserInfoRpc userInfoRpc;
    private StoreInfoRpc storeInfoRpc;
    private CommodityInfoRpc commodityInfoRpc;
    private UserCouponInfoRpc userCouponInfoRpc;
    private DomainEventPublisher domainEventPublisher;

    @Autowired
    public void setDomainEventPublisher(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Autowired
    public void setUserCouponInfoRpc(UserCouponInfoRpc userCouponInfoRpc) {
        this.userCouponInfoRpc = userCouponInfoRpc;
    }

    @Autowired
    public void setCouponInfoRpc(UserCouponInfoRpc userCouponInfoRpc) {
        this.userCouponInfoRpc = userCouponInfoRpc;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setStoreInfoRpc(StoreInfoRpc storeInfoRpc) {
        this.storeInfoRpc = storeInfoRpc;
    }

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 添加一条 order
     *
     * @param command
     */
    @Transactional
    public void addOne(AddOrderCommand command) {
        // 下单用户id
        long userId = command.getUserId();
        // 本订单使用的所有优惠券id
        List<Long> couponIdsForOrder = command.getCouponIdsForOrder();
        // 订单收货地址id
        long addressId = command.getAddressId();
        List<CommodityCouponMap> commodities = command.getCommodities();
        // 检查ids
        verifyIdFormatService.verifyIds(userId, addressId);
        // 检查用户
        UserInfoRpcDTO userInfoRpcDTO = userInfoRpc.getUserInfo(userId);
        checkUser(userInfoRpcDTO);
        // 创建父订单
        long orderId = snowFlakeService.snowflakeId();
        log.error("创建父订单：orderId = " + orderId);
        Order order = new Order(orderId);
        //  -- 构造、填充父订单
        order.setType(Order.TYPE_UNSPLITED);

        // 订单用户信息
        UserInfo userInfo = new UserInfo(userId);
        AddressInfo addressInfo = new AddressInfo(orderId, addressId);
        //      -- 订单用户信息：获得地址信息并检查
        UserAddressRpcDTO userAddressRpcDTO = userInfoRpc.findOneAddressByUserIdAndAddressId(userId, addressId);
        checkUserAddress(userAddressRpcDTO);
        //      -- 订单用户信息：填充订单地址信息
        inflateAddressInfoOfOrder(addressInfo, userAddressRpcDTO);
        userInfo.setAddressInfo(addressInfo);
        order.setUserInfo(userInfo);

        // 订单时间信息
        TimeInfo timeInfo = new TimeInfo(orderId);
        //       -- 订单时间信息：创建订单时，只填充创建订单信息
        timeInfo.setPlaceOrderTime(command.getPlaceOrderTime());
        order.setTimeInfo(timeInfo);

        // 订单订单信息
        OrderInfo orderInfo = new OrderInfo(orderId);
        orderInfo.setOrderStatus(Order.STATUS_UNPAY);
        order.setOrderInfo(orderInfo);

        ArrayList<CommodityInfo> commodityInfos = new ArrayList<>();
        // 整个订单应付金额
        BigDecimal priceOfPaymentOfOrder = BigDecimal.ZERO;
        // 整个订单所含商品总额
        BigDecimal priceOfCommodityOfOrder = BigDecimal.ZERO;
        // 整个订单所使用的积分数
        int creditOfOrder = 0;
        // 整个订单的优惠券信息
        ArrayList<Coupon> couponListOfOrder = couponIdsForOrder.parallelStream().collect(
                ArrayList<Coupon>::new,
                (con, couponId) -> {
                    CouponInfoRpcDTO couponOfOrderRpcDTO = userCouponInfoRpc.findOneByCouponId(userId, couponId);
                    checkCoupon(couponOfOrderRpcDTO);
                    Coupon coupon = new Coupon(orderId, couponId);
                    BigDecimal thresholdPrice = couponOfOrderRpcDTO.getThresholdPrice();
                    BigDecimal counteractPrice = couponOfOrderRpcDTO.getCounteractPrice();
                    inflateCoupon(coupon, counteractPrice, thresholdPrice);
                    con.add(coupon);
                },
                ArrayList<Coupon>::addAll
        );

        for (CommodityCouponMap commodity : commodities) {
            long commodityId = commodity.getCommodityId();
            // rpc 获得商品信息
            CommodityInfoRpcDTO commodityInfoRpcDTO = commodityInfoRpc.findByCommodityId(commodityId);
            // 订单的商品信息
            CommodityInfo commodityInfo = new CommodityInfo(orderId, commodityId);
            // rpc获得店铺信息
            Long storeId = commodity.getStoreId();
            StoreInfoRpcDTO storeInfoRpcDTO = storeInfoRpc.findOneByStoreId(storeId);
            StoreInfo storeInfo = new StoreInfo(orderId, storeId);
            // 填充 storeInfo
            storeInfo.setStoreName(storeInfoRpcDTO.getStoreName());
            storeInfo.setStoreLogo(storeInfoRpcDTO.getStoreLogo());
            // 该商品金额信息
            AmountInfoOfCommodity amountInfoOfCommodity = new AmountInfoOfCommodity(orderId);
            amountInfoOfCommodity.setCommodityId(commodityId);
            // 商品总价 = 商品现价(单价) * 购买数量
            BigDecimal priceOfCommodity = commodityInfoRpcDTO.getCurrentPrice().multiply(BigDecimal.valueOf(commodity.getCount()));
            amountInfoOfCommodity.setPriceOfCommodity(priceOfCommodity);

            // discountPrice 折扣总金额 = 积分抵扣金额 + 优惠券折扣金额
            List<Long> couponIds = commodity.getCouponIds();
            // 商品总折扣金额
            BigDecimal totalDisAcount = BigDecimal.ZERO;
            // 商品涉及涉及的优惠券信息
            ArrayList<Coupon> couponList = new ArrayList<>();
            for (long couponId : couponIds) {
                // rpc 获得优惠券信息
                CouponInfoRpcDTO userCoupon = userCouponInfoRpc.findOneByCouponId(userId, couponId);
                // 用户没有该优惠券
                checkCoupon(userCoupon);
                // 创建商品所使用的优惠券信息
                Coupon coupon = new Coupon(orderId, couponId);
                // -- 填充
                BigDecimal counteractPrice = userCoupon.getCounteractPrice();
                BigDecimal thresholdPrice = userCoupon.getThresholdPrice();
                inflateCoupon(coupon, counteractPrice, thresholdPrice);
                couponList.add(coupon);
                // 计算总优惠金额
                totalDisAcount = totalDisAcount.add(userCoupon.getCounteractPrice());
            }
            // 构建 DiscountPriceInfo
            DiscountPriceInfo discountPriceInfo = new DiscountPriceInfo();
            // -- 填充
            discountPriceInfo.setCoupons(couponList);
            discountPriceInfo.setCredit(commodity.getCredits());
            amountInfoOfCommodity.setDiscountPriceInfo(discountPriceInfo);
            // 实际本商品应付金额为：商品总价 - 商品折扣总价
            BigDecimal priceOfPaymentOfCommodity = priceOfCommodity.subtract(totalDisAcount);
            amountInfoOfCommodity.setPriceOfPayment(priceOfPaymentOfCommodity);
            // 填充 commodityInfo
            inflateCommodityInfo(commodity, commodityInfoRpcDTO, commodityInfo, storeInfo, amountInfoOfCommodity);

            // 将获得商品信息放到 list 中
            commodityInfos.add(commodityInfo);

            // 初始化订单的金额信息
            priceOfPaymentOfOrder = priceOfCommodityOfOrder.add(priceOfPaymentOfCommodity);
            priceOfCommodityOfOrder = priceOfCommodityOfOrder.add(priceOfCommodity);
        }
        order.setCommodityInfoList(commodityInfos);
        AmountInfo amountInfo = new AmountInfo(orderId);
        inflateAmountInfo(priceOfPaymentOfOrder, priceOfCommodityOfOrder, creditOfOrder, couponListOfOrder, amountInfo);
        order.setAmountInfo(amountInfo);
        //  -- 父订单入库
        orderRepository.addOne(order);

        // 拆单 —— 按商家
        List<CommodityInfo> commodityInfoList = order.getCommodityInfoList();
        // set 对商品所属商家去重
        HashSet<Long> commodityIdSet = new HashSet<>();
        commodityInfoList.forEach(
                commodityInfo -> commodityIdSet.add(commodityInfo.getStoreInfo().getStoreId())
        );
        Set<CommodityInfo> commoditySet = commodityInfoList.stream().filter(
                commodityInfo -> commodityIdSet.contains(commodityInfo.getStoreInfo().getStoreId())
        ).collect(Collectors.toSet());
        for (Long storeIdOfCommodity : commodityIdSet) {
            ChildrenOrder childrenOrder = new ChildrenOrder(orderId);
            long childrenOrderId = snowFlakeService.snowflakeId();
            log.error("创建子订单：childrenOrderId = " + childrenOrderId);
            Integer orderStatus = order.getOrderInfo().getOrderStatus();
            childrenOrder.setChildrenOrderId(childrenOrderId);
            childrenOrder.setOrderId(childrenOrderId);
            childrenOrder.setOrderStatus(orderStatus);
            childrenOrder.setUserId(userId);
            childrenOrder.setType(Order.TYPE_SPLITED);

            OrderInfo childrenOrderInfo = new OrderInfo(childrenOrderId);
            childrenOrderInfo.setOrderStatus(orderStatus);
            childrenOrder.setOrderInfo(childrenOrderInfo);
            try {
                TimeInfo timeInfoCloned = order.getTimeInfo().clone();
                timeInfoCloned.setOrderId(childrenOrderId);
                childrenOrder.setTimeInfo(timeInfoCloned);
            } catch (CloneNotSupportedException e) {
                throwParameterFormatIllegalException();
            }

            UserInfo userInfoOfChildrenOrder = new UserInfo(order.getUserInfo().getUserId());
            try {
                AddressInfo addressInfoClone = order.getUserInfo().getAddressInfo().clone();
                addressInfoClone.setOrderId(childrenOrderId);
                userInfoOfChildrenOrder.setAddressInfo(addressInfoClone);
            } catch (CloneNotSupportedException e) {
                throwParameterFormatIllegalException();
                return;
            }
            childrenOrder.setUserInfo(userInfoOfChildrenOrder);

            // 过滤除属于该商家的商品信息
            List<CommodityInfo> commodityInfoOfChildrenOfOneStore = commodityInfoList.stream().filter(
                    commodityInfoUnSplit -> storeIdOfCommodity.equals(commodityInfoUnSplit.getStoreInfo().getStoreId())
            ).collect(Collectors.toList());
            // 修改改商家商品的 commodityInfo ->修改其 orderId
            ArrayList<CommodityInfo> commodityInfoOfChildrenOrderCloned = commodityInfoOfChildrenOfOneStore.stream().collect(
                    ArrayList<CommodityInfo>::new,
                    (con, commodityInfo) -> {
                        try {
                            // 修改 CommodityInfo 的 orderId
                            CommodityInfo commodityInfoCloned = commodityInfo.clone();
                            commodityInfoCloned.setOrderId(childrenOrderId);
                            // 修改 amountInfoOfCommodity 的 orderId
                            AmountInfoOfCommodity amountInfoOfCommodityCloned = commodityInfoCloned.getAmountInfo().clone();
                            amountInfoOfCommodityCloned.setOrderId(childrenOrderId);
                            commodityInfoCloned.setAmountInfo(amountInfoOfCommodityCloned);
                            // 修改 storeInfo 的 orderId
                            StoreInfo storeInfoCloned = commodityInfo.getStoreInfo().clone();
                            storeInfoCloned.setOrderId(childrenOrderId);
                            commodityInfoCloned.setStoreInfo(storeInfoCloned);
                            con.add(commodityInfoCloned);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    },
                    ArrayList<CommodityInfo>::addAll
            );
            childrenOrder.setCommodityInfoList(commodityInfoOfChildrenOrderCloned);

            BigDecimal childrenOrderPriceOfPayment = BigDecimal.ZERO;
            BigDecimal childrenOrderPriceOfCommodity = BigDecimal.ZERO;
            int childrenOrderCredits = 0;
            ArrayList<Coupon> childrenOrderCoupons = new ArrayList<>();
            // 提取拆分后子订单内商品信息，以构造 子订单的 金额信息AmountInfo
            for (CommodityInfo childrenCommodityInfo : commodityInfoOfChildrenOrderCloned) {
                AmountInfoOfCommodity childrenCommodityAmountInfo = childrenCommodityInfo.getAmountInfo();
                childrenCommodityAmountInfo.setCommodityId(childrenCommodityInfo.getCommodityId());
                childrenOrderPriceOfPayment = childrenOrderPriceOfPayment.add(childrenCommodityAmountInfo.getPriceOfPayment());
                childrenOrderPriceOfCommodity = childrenOrderPriceOfCommodity.add(childrenCommodityAmountInfo.getPriceOfCommodity());
                childrenOrderCredits += childrenCommodityAmountInfo.getDiscountPriceInfo().getCredit();
                List<Coupon> couponOfChildrenOrder = childrenCommodityAmountInfo.getDiscountPriceInfo().getCoupons().stream().map(
                        coupon -> {
                            Coupon couponCloned = coupon;
                            try {
                                couponCloned = coupon.clone();
                                couponCloned.setOrderId(childrenOrderId);
                            } catch (CloneNotSupportedException e) {
                                throwParameterFormatIllegalException();
                            }
                            return couponCloned;
                        }
                ).collect(Collectors.toList());
                childrenOrderCoupons.addAll(couponOfChildrenOrder);
            }
            AmountInfo childrenOrderAmountInfo = new AmountInfo(childrenOrderId);
            inflateChildrenOrderAmountInfo(childrenOrderPriceOfPayment, childrenOrderPriceOfCommodity, childrenOrderCredits, childrenOrderCoupons, childrenOrderAmountInfo);
            childrenOrder.setAmountInfo(childrenOrderAmountInfo);
            // 每个子订单入库
            orderRepository.addOne(childrenOrder);
        }

    }

    private void throwParameterFormatIllegalException() {
        throw new ParameterFormatIllegalityException(
                ResponseStatusEnum.PARAMETERERROR.getErrorCode(),
                ResponseStatusEnum.PARAMETERERROR.getErrorMessage()
        );
    }

    private void inflateAddressInfo(AddressInfo addressInfoOfChildrenOrder, String receiverName, String receiverPhone, String provinceName, String cityName, String areaName, String townName, String detailAddress) {
        addressInfoOfChildrenOrder.setReceiverName(receiverName);
        addressInfoOfChildrenOrder.setReceiverPhone(receiverPhone);
        addressInfoOfChildrenOrder.setProvinceName(provinceName);
        addressInfoOfChildrenOrder.setCityName(cityName);
        addressInfoOfChildrenOrder.setAreaName(areaName);
        addressInfoOfChildrenOrder.setTownName(townName);
        addressInfoOfChildrenOrder.setDetailAddress(detailAddress);
    }

    private void inflateChildrenOrderAmountInfo(BigDecimal childrenOrderPriceOfPayment, BigDecimal childrenOrderPriceOfCommodity, int childrenOrderCredits, ArrayList<Coupon> childrenOrderCoupons, AmountInfo childrenOrderAmountInfo) {
        childrenOrderAmountInfo.setPriceOfPayment(childrenOrderPriceOfPayment);
        childrenOrderAmountInfo.setPriceOfCommodity(childrenOrderPriceOfCommodity);
        childrenOrderAmountInfo.setCredit(childrenOrderCredits);
        childrenOrderAmountInfo.setCoupons(childrenOrderCoupons);
    }

    private void inflateCoupon(Coupon coupon, BigDecimal counteractPrice, BigDecimal thresholdPrice) {
        coupon.setCounteractPrice(counteractPrice);
        coupon.setThresholdPrice(thresholdPrice);
    }

    private void inflateCommodityInfo(CommodityCouponMap commodity, CommodityInfoRpcDTO commodityInfoRpcDTO, CommodityInfo commodityInfo, StoreInfo storeInfo, AmountInfoOfCommodity amountInfoOfCommodity) {
        commodityInfo.setCommodityName(commodityInfoRpcDTO.getCommodityName());
        commodityInfo.setCommodityPicSmall(commodityInfoRpcDTO.getPicSmall());
        commodityInfo.setPriceWhenMakeOrder(commodityInfoRpcDTO.getCurrentPrice());
        commodityInfo.setSpecification(commodityInfoRpcDTO.getSpecification());
        commodityInfo.setCount(commodity.getCount());
        commodityInfo.setStoreInfo(storeInfo);
        commodityInfo.setAmountInfo(amountInfoOfCommodity);
    }

    private void inflateAmountInfo(BigDecimal priceOfPaymentOfOrder, BigDecimal priceOfCommodityOfOrder, int creditOfOrder, ArrayList<Coupon> couponListOfOrder, AmountInfo amountInfo) {
        amountInfo.setPriceOfCommodity(priceOfCommodityOfOrder);
        amountInfo.setPriceOfPayment(priceOfPaymentOfOrder);
        amountInfo.setCredit(creditOfOrder);
        amountInfo.setCoupons(couponListOfOrder);
    }

    private void checkCoupon(CouponInfoRpcDTO userCoupon) {
        if (userCoupon == null) {
            throw new CouponNotExistedException(
                    ResponseStatusEnum.CouponNotExisted.getErrorCode(),
                    ResponseStatusEnum.CouponNotExisted.getErrorMessage()
            );
        }
    }

    private void inflateAddressInfoOfOrder(AddressInfo addressInfo, UserAddressRpcDTO userAddressRpcDTO) {
        addressInfo.setUserId(userAddressRpcDTO.getUserId());
        inflateAddressInfo(addressInfo, userAddressRpcDTO.getReceiverName(), userAddressRpcDTO.getReceiverPhone(), userAddressRpcDTO.getProvinceName(), userAddressRpcDTO.getCityName(), userAddressRpcDTO.getAreaName(), userAddressRpcDTO.getTownName(), userAddressRpcDTO.getDetailAddress());
    }

    private void checkUserAddress(UserAddressRpcDTO userAddressRpcDTO) {
        if (userAddressRpcDTO == null) {
            throwAddressNotExistException();
        }
    }

    private void checkUser(UserInfoRpcDTO userInfo) {
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * 移除一条订单
     *
     * @param orderId
     */
    @Transactional
    public void removeOne(Long userId, Long orderId) {
        verifyIdFormatService.verifyIds(userId, orderId);
        ChildrenOrder childrenOrder = orderRepository.findOneByUserIdAndOrderId(userId, orderId);
        orderRepository.removeOne(childrenOrder);
    }

    @Transactional
    public List<ChildrenOrder> findPageByUserId(Long userId, Integer page, Integer limit) {
        verifyIdFormatService.verifyIds(userId);
        // 检查 user
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        checkUser(userInfo);
        // 查找返回
        return orderRepository.findPageByUserId(userId, page, limit);
    }

    @Transactional
    public ChildrenOrder findOneByUserIdAndOrderId(Long userId, Long orderId) {
        verifyIdFormatService.verifyIds(userId, orderId);
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        checkUser(userInfo);
        return orderRepository.findOneByUserIdAndOrderId(userId, orderId);
    }

    @Transactional
    public void cancelOrder(Long userId, Long orderId, Long cancelTime) {
        verifyIdFormatService.verifyIds(userId, orderId);
        ChildrenOrder order = orderRepository.findOneByUserIdAndOrderId(userId, orderId);
        checkOrder(order);
        order.cancelOrder(cancelTime);
        orderRepository.saveOne(order);
    }

    private void checkOrder(ChildrenOrder order) {
        if (order == null) {
            throwOrderNotExistedException();
        }
    }

    private void throwOrderNotExistedException() {
        throw new OrderNotExistException(
                ResponseStatusEnum.ORDERNOTEXISTED.getErrorCode(),
                ResponseStatusEnum.ORDERNOTEXISTED.getErrorMessage()
        );
    }


    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void changeAddress(Long userId, Long orderId, Long addressId) {
        //  检查id
        verifyIdFormatService.verifyIds(userId, orderId, addressId);
        // 查找订单
        ChildrenOrder order = orderRepository.findOneByUserIdAndOrderId(userId, orderId);
        checkOrder(order);
        // 查找 userAddress
        UserAddressRpcDTO userAddressInfo = userInfoRpc.findOneAddressByUserIdAndAddressId(userId, addressId);
        checkUserAddress(userAddressInfo);
        AddressInfo addressInfo = new AddressInfo(orderId, addressId);
        String receiverName = userAddressInfo.getReceiverName();
        String receiverPhone = userAddressInfo.getReceiverPhone();
        String provinceName = userAddressInfo.getProvinceName();
        String cityName = userAddressInfo.getCityName();
        String areaName = userAddressInfo.getAreaName();
        String townName = userAddressInfo.getTownName();
        String detailAddress = userAddressInfo.getDetailAddress();
        addressInfo.setReceiverName(receiverName);
        addressInfo.setReceiverPhone(receiverPhone);
        addressInfo.setProvinceName(provinceName);
        addressInfo.setCityName(cityName);
        addressInfo.setAreaName(areaName);
        addressInfo.setTownName(townName);
        addressInfo.setDetailAddress(detailAddress);
        // 修改地址
        order.changeAddress(addressInfo);
        // 入库
        orderRepository.saveOne(order);

        // 触发领域事件通知其他订单微服务
        //      -- 构建领域事件
        long domainEventId = snowFlakeService.snowflakeId();
        UserChangedAddressEvent userChangedAddressEvent = new UserChangedAddressEvent(domainEventId);
        inflateUserChangedAddressEvent(userId, addressId, receiverName, receiverPhone, provinceName, cityName, townName, detailAddress, userChangedAddressEvent);
        //      -- log
        logger.info("sweetcat-app-credit: 触发领域事件 ConsumedCouponEvent 时间为：{}", Instant.now().toEpochMilli());
        //      -- 发送
        domainEventPublisher.syncSend("sweetcat-user-order:user_changed_address", userChangedAddressEvent);
    }

    private void inflateUserChangedAddressEvent(Long userId, Long addressId, String receiverName, String receiverPhone, String provinceName, String cityName, String townName, String detailAddress, UserChangedAddressEvent userChangedAddressEvent) {
        userChangedAddressEvent.setOccurOn(Instant.now().toEpochMilli());
        userChangedAddressEvent.setUserId(userId);
        userChangedAddressEvent.setAddressId(addressId);
        userChangedAddressEvent.setReceiverName(receiverName);
        userChangedAddressEvent.setReceiverPhone(receiverPhone);
        userChangedAddressEvent.setProvinceName(provinceName);
        userChangedAddressEvent.setCityName(cityName);
        userChangedAddressEvent.setTownName(townName);
        userChangedAddressEvent.setDetailAddress(detailAddress);
    }


    private void throwAddressNotExistException() {
        throw new AddressNotExistedException(
                ResponseStatusEnum.ADDRESSNOTEXISTED.getErrorCode(),
                ResponseStatusEnum.ADDRESSNOTEXISTED.getErrorMessage()
        );
    }

}
