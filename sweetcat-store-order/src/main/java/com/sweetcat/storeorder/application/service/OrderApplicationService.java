package com.sweetcat.storeorder.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.usercoupon.CouponInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserAddressRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.AddressNotExistedException;
import com.sweetcat.commons.exception.CouponNotExistedException;
import com.sweetcat.commons.exception.OrderNotExistException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.storeorder.application.command.AddOrderCommand;
import com.sweetcat.storeorder.application.command.CommodityCouponMap;
import com.sweetcat.storeorder.application.command.changecustomeraddresscommand.ChangeCustomerAddressCommand;
import com.sweetcat.storeorder.application.rpc.CommodityInfoRpc;
import com.sweetcat.storeorder.application.rpc.UserCouponInfoRpc;
import com.sweetcat.storeorder.application.rpc.UserInfoRpc;
import com.sweetcat.storeorder.application.rpc.UserOrderRpc;
import com.sweetcat.storeorder.domain.order.entity.*;
import com.sweetcat.storeorder.domain.order.repository.OrderRepository;
import com.sweetcat.storeorder.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.storeorder.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/4-23:00
 * @version: 1.0
 */
@Service
public class OrderApplicationService {
    private SnowFlakeService snowFlakeService;
    private OrderRepository orderRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private UserInfoRpc userInfoRpc;
    private CommodityInfoRpc commodityInfoRpc;
    private UserCouponInfoRpc userCouponInfoRpc;
    private UserOrderRpc userOrderRpc;

    @Autowired
    public void setUserOrderRpc(UserOrderRpc userOrderRpc) {
        this.userOrderRpc = userOrderRpc;
    }

    @Autowired
    public void setUserCouponInfoRpc(UserCouponInfoRpc userCouponInfoRpc) {
        this.userCouponInfoRpc = userCouponInfoRpc;
    }

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 添加订单
     *
     * @param command
     */
    @Transactional
    public void addOne(AddOrderCommand command) {
        long userId = command.getUserId();
        long addressId = command.getAddressId();
        Long orderId = command.getOrderId();
        verifyIdFormatService.verifyIds(userId, addressId, orderId);
        UserInfoRpcDTO userInfoRpcDTO = userInfoRpc.getUserInfo(userId);
        checkUser(userInfoRpcDTO);
        UserAddressRpcDTO userAddress = userInfoRpc.findOneAddressByUserIdAndAddressId(userId, addressId);
        checkUserAddress(userAddress);
        Order order = new Order(orderId);
        order.setOrderStatus(Order.STATUS_UNPAY);

        // 订单的商品总价
        AtomicReference<BigDecimal> commodityPriceOfOrder = new AtomicReference<>(BigDecimal.ZERO);

        ArrayList<CommodityInfo> commodityInfos = command.getCommodities().stream().collect(
                ArrayList<CommodityInfo>::new,
                (con, commodityCouponMap) -> {
                    Long commodityId = commodityCouponMap.getCommodityId();
                    CommodityInfoRpcDTO commodityInfoRpcDTO = commodityInfoRpc.findByCommodityId(commodityId);
                    CommodityInfo commodityInfo = new CommodityInfo(orderId, commodityId);
                    inflateCommodityInfo(commodityCouponMap, commodityInfoRpcDTO, commodityInfo);

                    AmountInfoOfCommodity amountInfoOfCommodity = new AmountInfoOfCommodity(orderId, commodityId);
                    BigDecimal totalCommodityPrice = commodityInfoRpcDTO.getCurrentPrice().multiply(BigDecimal.valueOf(commodityCouponMap.getCount()));
                    commodityPriceOfOrder.updateAndGet(v -> v.add(totalCommodityPrice));
                    amountInfoOfCommodity.setPriceOfCommodity(totalCommodityPrice);
                    // 单件商品的优惠总价
                    BigDecimal totalDiscountPrice = BigDecimal.ZERO;

                    DiscountPriceInfoOfCommodity discountPriceInfoOfCommodity = new DiscountPriceInfoOfCommodity(commodityId);
                    discountPriceInfoOfCommodity.setCredit(commodityCouponMap.getCredits());
                    List<CouponOfCommodity> couponOfCommodities = new ArrayList<>();
                    for (Long couponId : commodityCouponMap.getCouponIds()) {
                        CouponInfoRpcDTO userCouponInfoRpc = this.userCouponInfoRpc.findOneByCouponId(userId, couponId);
                        checkUserCoupon(userCouponInfoRpc);
                        CouponOfCommodity couponOfCommodity = new CouponOfCommodity(orderId, commodityId, couponId);
                        inflateCouponOfCommodity(couponOfCommodities, userCouponInfoRpc, couponOfCommodity);
                        totalDiscountPrice = totalDiscountPrice.add(userCouponInfoRpc.getCounteractPrice());
                    }

                    discountPriceInfoOfCommodity.setCouponsOfCommodity(couponOfCommodities);
                    amountInfoOfCommodity.setDiscountPriceInfo(discountPriceInfoOfCommodity);
                    // 应付金额 = 商品总价 - 优惠券抵扣金额 - (积分数 / 10000) :积分与现金比率为10000积分=1RMB
                    amountInfoOfCommodity.setPriceOfPayment(
                            amountInfoOfCommodity.getPriceOfCommodity()
                                    .subtract(totalDiscountPrice)
                                    .subtract(BigDecimal.valueOf((amountInfoOfCommodity.getDiscountPriceInfo().getCredit() / 1000)))
                    );
                    commodityInfo.setAmountInfo(amountInfoOfCommodity);
                    con.add(commodityInfo);
                },
                ArrayList<CommodityInfo>::addAll
        );

        order.setCommodityInfoList(commodityInfos);

        TimeInfo timeInfo = new TimeInfo(orderId);
        timeInfo.setPlaceOrderTime(command.getPlaceOrderTime());
        order.setTimeInfo(timeInfo);

        UserInfo userInfo = new UserInfo(userId);
        userInfo.setUserName(userInfoRpcDTO.getNickname());
        userInfo.setUserAvatar(userInfoRpcDTO.getAvatarPath());
        AddressInfo addressInfo = new AddressInfo(orderId, userId, addressId);
        inflateAddressInfo(userAddress, addressInfo);
        userInfo.setAddressInfo(addressInfo);
        order.setUserInfo(userInfo);

        // 订单的总消耗积分数
        int creditOfOrder = 0;
        for (CommodityCouponMap commodityCouponMap : command.getCommodities()) {
            creditOfOrder += commodityCouponMap.getCredits();
        }
        AmountInfoOfOrder amountInfoOfOrder = new AmountInfoOfOrder(orderId);
        amountInfoOfOrder.setCredit(creditOfOrder);
        amountInfoOfOrder.setPriceOfCommodity(commodityPriceOfOrder.get());
        AtomicReference<BigDecimal> discountPriceOfOrder = new AtomicReference<>(BigDecimal.ZERO);
        ArrayList<CouponOfOrder> couponOfOrders = new ArrayList<>();
        command.getCouponIdsForOrder().forEach(
                couponId -> {
                    CouponInfoRpcDTO coupon = userCouponInfoRpc.findOneByCouponId(userId, couponId);
                    CouponOfOrder couponOfOrder = new CouponOfOrder(orderId, couponId);
                    couponOfOrder.setThresholdPrice(coupon.getThresholdPrice());
                    couponOfOrder.setCounteractPrice(coupon.getCounteractPrice());
                    discountPriceOfOrder.updateAndGet(v -> v.add(coupon.getCounteractPrice()));
                    couponOfOrders.add(couponOfOrder);
                }
        );
        amountInfoOfOrder.setPriceOfPayment(amountInfoOfOrder.getPriceOfCommodity()
                .subtract(discountPriceOfOrder.get())
                .subtract(BigDecimal.valueOf(amountInfoOfOrder.getCredit() / 1000)));
        amountInfoOfOrder.setCouponOfOrders(couponOfOrders);
        order.setAmountInfo(amountInfoOfOrder);
        orderRepository.addOne(order);
    }

    private void inflateAddressInfo(UserAddressRpcDTO userAddress, AddressInfo addressInfo) {
        inflateAddressInfo(addressInfo, userAddress.getReceiverName(), userAddress.getReceiverPhone(), userAddress.getProvinceName(), userAddress.getCityName(), userAddress.getAreaName(), userAddress.getTownName(), userAddress.getDetailAddress());
    }

    private void inflateCouponOfCommodity(List<CouponOfCommodity> couponOfCommodities, CouponInfoRpcDTO userCouponInfoRpc, CouponOfCommodity couponOfCommodity) {
        couponOfCommodity.setThresholdPrice(userCouponInfoRpc.getThresholdPrice());
        couponOfCommodity.setCounteractPrice(userCouponInfoRpc.getCounteractPrice());
        couponOfCommodities.add(couponOfCommodity);
    }

    private void inflateCommodityInfo(com.sweetcat.storeorder.application.command.CommodityCouponMap commodityCouponMap, CommodityInfoRpcDTO commodityInfoRpcDTO, CommodityInfo commodityInfo) {
        commodityInfo.setCommodityName(commodityInfoRpcDTO.getCommodityName());
        commodityInfo.setCommodityPicSmall(commodityInfoRpcDTO.getPicSmall());
        commodityInfo.setPriceWhenMakeOrder(commodityInfoRpcDTO.getCurrentPrice());
        commodityInfo.setSpecification(commodityCouponMap.getSpecification());
        commodityInfo.setCount(commodityCouponMap.getCount());
    }

    private void checkUserCoupon(CouponInfoRpcDTO userCouponInfoRpc) {
        if (userCouponInfoRpc == null) {
            throw new CouponNotExistedException(
                    ResponseStatusEnum.CouponNotExisted.getErrorCode(),
                    ResponseStatusEnum.CouponNotExisted.getErrorMessage()
            );
        }
    }

    private void checkUserAddress(UserAddressRpcDTO userAddress) {
        if (userAddress == null) {
            throw new AddressNotExistedException(
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
     * 查分页数据 by customer id
     *
     * @param customerId
     * @param page
     * @param limit
     */
    @Transactional
    public List<Order> findPageByCustomerId(Long customerId, Integer page, Integer limit) {
        verifyIdFormatService.verifyIds(customerId);
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(customerId);
        checkUser(userInfo);
        return orderRepository.findPageByCustomerId(customerId, page, limit);
    }

    /**
     * 更具订单id 查订单信息
     *
     * @param orderId
     * @return
     */
    @Transactional
    public Order findOneByOrderId(Long orderId) {
        verifyIdFormatService.verifyIds(orderId);
        return orderRepository.findOneByOrderId(orderId);
    }

    @Transactional
    public void changeUserAddress(ChangeCustomerAddressCommand command) {
        Long userId = command.getUserId();
        Long addressId = command.getAddressId();
        // 检查id
        verifyIdFormatService.verifyIds(userId, addressId);
        // 检查 user
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        checkUser(userInfo);

        List<Order> orders = orderRepository.findAllByUserIdAndAddressId(userId);

        orders.forEach(
                order -> {
                    // 创建新地址信息
                    Long orderId = order.getOrderId();
                    AddressInfo addressInfo = new AddressInfo(orderId, userId, addressId);
                    inflateAddressInfo(addressInfo, command.getReceiverName(), command.getReceiverPhone(), command.getProvinceName(), command.getCityName(), command.getAreaName(), command.getTownName(), command.getDetailAddress());
                    // 修改地址信息
                    order.changeUserAddress(addressInfo);
                    // 保存入库
                    orderRepository.saveOne(order);
                }
        );
    }


    private void inflateAddressInfo(AddressInfo addressInfo, String receiverName, String receiverPhone, String provinceName, String cityName, String areaName, String townName, String detailAddress) {
        addressInfo.setReceiverName(receiverName);
        addressInfo.setReceiverPhone(receiverPhone);
        addressInfo.setProvinceName(provinceName);
        addressInfo.setCityName(cityName);
        addressInfo.setAreaName(areaName);
        addressInfo.setTownName(townName);
        addressInfo.setDetailAddress(detailAddress);
    }


    private void checkOrder(Order order) {
        if (order == null) {
            throw new OrderNotExistException(
                    ResponseStatusEnum.ORDERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.ORDERNOTEXISTED.getErrorMessage()
            );
        }
    }
}
