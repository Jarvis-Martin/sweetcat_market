package com.sweetcat.userorder.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.usercoupon.CouponInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserAddressRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CouponNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.userorder.application.command.AddOrderCommand;
import com.sweetcat.userorder.application.command.CommodityCouponMap;
import com.sweetcat.userorder.application.rpc.*;
import com.sweetcat.userorder.domain.order.entity.*;
import com.sweetcat.userorder.domain.order.repository.OrderRepository;
import com.sweetcat.userorder.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.userorder.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class OrderApplicationService {
    private OrderRepository orderRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private UserInfoRpc userInfoRpc;
    private StoreInfoRpc storeInfoRpc;
    private CommodityInfoRpc commodityInfoRpc;
    private UserCouponInfoRpc userCouponInfoRpc;
    private UserAddressInfoRpc userAddressInfoRpc;

    @Autowired
    public void setUserCouponInfoRpc(UserCouponInfoRpc userCouponInfoRpc) {
        this.userCouponInfoRpc = userCouponInfoRpc;
    }

    @Autowired
    public void setUserAddressInfoRpc(UserAddressInfoRpc userAddressInfoRpc) {
        this.userAddressInfoRpc = userAddressInfoRpc;
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
        verifyIdFormatService.verifyIds(((Long[]) couponIdsForOrder.toArray()));
        // 商品-优惠券map
        commodities.parallelStream().forEach(
                comodity -> {
                    Long commodityId = comodity.getCommodityId();
                    Long storeId = comodity.getStoreId();
                    List<Long> couponIds = comodity.getCouponIds();
                    // 检查商品id、优惠券id、商家id
                    verifyIdFormatService.verifyIds(commodityId, storeId);
                    verifyIdFormatService.verifyIds(((Long[]) couponIds.toArray()));
                }
        );
        // 检查用户
        UserInfoRpcDTO userInfoRpcDTO = userInfoRpc.getUserInfo(userId);
        checkUser(userInfoRpcDTO);
        // 创建父订单
        long orderId = snowFlakeService.snowflakeId();
        Order order = new Order(orderId);
        //  -- 构造、填充父订单
        {
            order.setType(Order.TYPE_UNSPLITED);

            // 订单用户信息
            UserInfo userInfo = new UserInfo(userId);
            AddressInfo addressInfo = new AddressInfo(addressId);
            //      -- 订单用户信息：获得地址信息并检查
            UserAddressRpcDTO userAddressRpcDTO = userAddressInfoRpc.findOneAddressByUserIdAndAddressId(userId, addressId);
            checkUserAddress(userAddressRpcDTO);
            //      -- 订单用户信息：填充订单地址信息
            inflateAddressInfoOfOrder(addressInfo, userAddressRpcDTO);
            order.setUserInfo(userInfo);

            // 订单时间信息
            TimeInfo timeInfo = new TimeInfo();
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
                        Coupon coupon = new Coupon(couponId);
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
                CommodityInfo commodityInfo = new CommodityInfo(commodityId);
                // rpc获得店铺信息
                Long storeId = commodity.getStoreId();
                StoreInfoRpcDTO storeInfoRpcDTO = storeInfoRpc.findOneByStoreId(storeId);
                StoreInfo storeInfo = new StoreInfo(storeId);
                // 填充 storeInfo
                {
                    storeInfo.setStoreName(storeInfoRpcDTO.getStoreName());
                    storeInfo.setStoreLogo(storeInfoRpcDTO.getStoreLogo());
                }
                // 该商品金额信息
                AmountInfoOfCommodity amountInfoOfCommodity = new AmountInfoOfCommodity();
                // 商品总价 = 商品现价(单价) * 购买数量
                BigDecimal priceOfCommodity = commodityInfoRpcDTO.getCurrentPrice().multiply(BigDecimal.valueOf(commodity.getCount()));
                amountInfoOfCommodity.setPriceOfCommodity(priceOfCommodity);

                // discountPrice 折扣总金额 = 积分抵扣金额 + 优惠券折扣金额
                List<Long> couponIds = commodity.getCouponIds();
                // 商品总折扣金额
                BigDecimal totalDisAcount = BigDecimal.ZERO;
                // 商品涉及涉及的优惠券信息
                ArrayList<Coupon> couponList = new ArrayList<Coupon>();
                for (long couponId : couponIds) {
                    // rpc 获得优惠券信息
                    CouponInfoRpcDTO userCoupon = userCouponInfoRpc.findOneByCouponId(userId, couponId);
                    // 用户没有该优惠券
                    checkCoupon(userCoupon);
                    // 创建商品所使用的优惠券信息
                    Coupon coupon = new Coupon(couponId);
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
                {
                    discountPriceInfo.setCoupons(couponList);
                    discountPriceInfo.setCredit(commodity.getCredits());
                }
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
            AmountInfo amountInfo = new AmountInfo();
            inflateAmountInfo(priceOfPaymentOfOrder, priceOfCommodityOfOrder, creditOfOrder, couponListOfOrder, amountInfo);
            order.setAmountInfo(amountInfo);
        }
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
        for (Long storeIdOfCommodity: commodityIdSet) {
            ChildrenOrder childrenOrder = new ChildrenOrder(orderId);
            long childrenOrderId = snowFlakeService.snowflakeId();
            childrenOrder.setChildrenOrderId(childrenOrderId);
            childrenOrder.setType(Order.TYPE_SPLITED);
            OrderInfo childrenOrderInfo = new OrderInfo(childrenOrderId);
            childrenOrderInfo.setOrderStatus(order.getOrderInfo().getOrderStatus());
            childrenOrder.setOrderInfo(childrenOrderInfo);
            childrenOrder.setTimeInfo(order.getTimeInfo());
            childrenOrder.setUserInfo(order.getUserInfo());
            List<CommodityInfo> commodityInfoOfChildrenOfOneStore = commodityInfoList.stream().filter(
                    commodityInfoUnSplit -> storeIdOfCommodity.equals(commodityInfoUnSplit.getStoreInfo().getStoreId())
            ).collect(Collectors.toList());
            childrenOrder.setCommodityInfoList(commodityInfoOfChildrenOfOneStore);

            BigDecimal childrenOrderPriceOfPayment = BigDecimal.ZERO;
            BigDecimal childrenOrderPriceOfCommodity = BigDecimal.ZERO;
            int childrenOrderCredits = 0;
            ArrayList<Coupon> childrenOrderCoupons = new ArrayList<>();
            // 提取拆分后子订单内商品信息，以构造 子订单的 金额信息AmountInfo
            for (CommodityInfo childrenCommodityInfo : commodityInfoOfChildrenOfOneStore) {
                AmountInfoOfCommodity childrenCommodityAmountInfo = childrenCommodityInfo.getAmountInfo();
                childrenOrderPriceOfPayment = childrenOrderPriceOfPayment.add(childrenCommodityAmountInfo.getPriceOfPayment());
                childrenOrderPriceOfCommodity = childrenOrderPriceOfCommodity.add(childrenCommodityAmountInfo.getPriceOfCommodity());
                childrenOrderCredits += childrenCommodityAmountInfo.getDiscountPriceInfo().getCredit();
                childrenOrderCoupons.addAll(childrenCommodityAmountInfo.getDiscountPriceInfo().getCoupons());
            }
            AmountInfo childrenOrderAmountInfo = new AmountInfo();
            inflateChildrenOrderAmountInfo(childrenOrderPriceOfPayment, childrenOrderPriceOfCommodity, childrenOrderCredits, childrenOrderCoupons, childrenOrderAmountInfo);
            childrenOrder.setAmountInfo(childrenOrderAmountInfo);
            // 每个子订单入库
            orderRepository.addOne(childrenOrder);
        }

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
        addressInfo.setReceiverName(userAddressRpcDTO.getReceiverName());
        addressInfo.setReceiverPhone(userAddressRpcDTO.getReceiverPhone());
        addressInfo.setProvinceName(userAddressRpcDTO.getProvinceName());
        addressInfo.setCityName(userAddressRpcDTO.getCityName());
        addressInfo.setAreaName(userAddressRpcDTO.getAreaName());
        addressInfo.setTownName(userAddressRpcDTO.getTownName());
        addressInfo.setDetailAddress(userAddressRpcDTO.getDetailAddress());
    }

    private void checkUserAddress(UserAddressRpcDTO userAddressRpcDTO) {
        if (userAddressRpcDTO == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.ADDRESSNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.ADDRESSNOTEXISTED.getErrorMessage()
            );
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
    public void removeOne(Long orderId) {
        verifyIdFormatService.verifyIds(orderId);
        ChildrenOrder childrenOrder = orderRepository.findOneByOrderId(orderId);
        orderRepository.removeOne(childrenOrder);
    }

    public List<ChildrenOrder> findPageByUserId(Long userId, Integer page, Integer limit) {
        verifyIdFormatService.verifyIds(userId);
        // 检查 user
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        checkUser(userInfo);
        // 查找返回
        return orderRepository.findPageByUserId(userId, page, limit);
    }

}
