package com.sweetcat.storeorder.infrastructure.factory;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.storeorder.application.rpc.UserInfoRpc;
import com.sweetcat.storeorder.domain.order.entity.*;
import com.sweetcat.storeorder.infrastructure.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/4-23:32
 * @version: 1.0
 */
@Component
public class OrderFactory {
    private UserInfoRpc userInfoRpc;

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    public Order create(OrderPO orderPO, List<AmountOfCommodityPO> amountOfCommodityPOS, AmountOfOrderPO amountOfOrderPO, List<CommodityPO> commodityPOS, List<CouponOfCommodityPO> couponOfCommodityPOS, List<CouponOfOrderPO> couponOfOrderPOS, TimeInfoPO timeInfoPO, UserAddressPO userAddressPO) {
        Long orderId = orderPO.getOrderId();
        Order order = new Order(orderId);
        order.setOrderStatus(orderPO.getOrderStatus());

        ArrayList<CommodityInfo> commodityInfos = commodityPOS.stream().collect(
                ArrayList<CommodityInfo>::new,
                (con, commodityPO) -> {
                    Long commodityId = commodityPO.getCommodityId();
                    CommodityInfo commodityInfo = new CommodityInfo(orderId, commodityId);
                    commodityInfo.setCommodityName(commodityPO.getCommodityName());
                    commodityInfo.setCommodityPicSmall(commodityPO.getCommodityPicSmall());
                    commodityInfo.setPriceWhenMakeOrder(commodityPO.getPriceWhenMakeOrder());
                    commodityInfo.setSpecification(commodityPO.getSpecification());
                    commodityInfo.setCount(commodityPO.getCount());

                    AtomicReference<Integer> totalCreditOfCommodity = new AtomicReference<>(0);
                    AmountOfCommodityPO amountOfCommodityPO = amountOfCommodityPOS.stream().filter(
                            amountOfCommodityPO1 -> commodityId.equals(amountOfCommodityPO1.getCommodityId())
                    ).findFirst().get();

                    AmountInfoOfCommodity amountInfoOfCommodity = new AmountInfoOfCommodity(orderId, commodityId);
                    amountInfoOfCommodity.setPriceOfPayment(amountOfCommodityPO.getPriceOfPayment());
                    amountInfoOfCommodity.setPriceOfCommodity(amountOfCommodityPO.getPriceOfCommodity());
                    DiscountPriceInfoOfCommodity discountPriceInfoOfCommodity = new DiscountPriceInfoOfCommodity(commodityId);
                    discountPriceInfoOfCommodity.setCredit(amountOfCommodityPO.getCredit());
                    ArrayList<CouponOfCommodity> couponOfCommodities = couponOfCommodityPOS.stream().filter(couponOfCommodityPO -> commodityId.equals(couponOfCommodityPO.getCommodityId())).collect(
                            ArrayList<CouponOfCommodity>::new,
                            (con1, couponOfOrderPO) -> {
                                CouponOfCommodity couponOfCommodity = new CouponOfCommodity(orderId, couponOfOrderPO.getCommodityId(), couponOfOrderPO.getCouponId());
                                couponOfCommodity.setThresholdPrice(couponOfOrderPO.getThresholdPrice());
                                couponOfCommodity.setCounteractPrice(couponOfOrderPO.getCounteractPrice());
                                con1.add(couponOfCommodity);
                            },
                            ArrayList<CouponOfCommodity>::addAll
                    );
                    discountPriceInfoOfCommodity.setCouponsOfCommodity(couponOfCommodities);
                    amountInfoOfCommodity.setDiscountPriceInfo(discountPriceInfoOfCommodity);
                    commodityInfo.setAmountInfo(amountInfoOfCommodity);

                    con.add(commodityInfo);
                },
                ArrayList<CommodityInfo>::addAll
        );

        order.setCommodityInfoList(commodityInfos);

        TimeInfo timeInfo = new TimeInfo(orderId);
        timeInfo.setPlaceOrderTime(timeInfoPO.getPlaceOrderTime());
        timeInfo.setPayOrderTime(timeInfoPO.getPayOrderTime());
        timeInfo.setTimeOutTime(timeInfoPO.getTimeOutTime());
        timeInfo.setCancelOrderTime(timeInfoPO.getCancelOrderTime());
        timeInfo.setFinishOrderTime(timeInfoPO.getFinishOrderTime());
        timeInfo.setDeliverCommodityTime(timeInfoPO.getDeliverCommodityTime());
        timeInfo.setReceivedCommodityTime(timeInfoPO.getReceivedCommodityTime());
        order.setTimeInfo(timeInfo);

        Long userId = orderPO.getUserId();
        UserInfoRpcDTO userInfoRpcDTO = userInfoRpc.getUserInfo(userId);
        checkUser(userInfoRpcDTO);
        UserInfo userInfo = new UserInfo(userId);
        userInfo.setUserName(userInfoRpcDTO.getNickname());
        userInfo.setUserAvatar(userInfoRpcDTO.getAvatarPath());
        AddressInfo addressInfo = new AddressInfo(orderId, userId, userAddressPO.getAddressId());
        addressInfo.setUserId(userAddressPO.getUserId());
        addressInfo.setReceiverName(userAddressPO.getReceiverName());
        addressInfo.setReceiverPhone(userAddressPO.getReceiverPhone());
        addressInfo.setProvinceName(userAddressPO.getProvinceName());
        addressInfo.setCityName(userAddressPO.getCityName());
        addressInfo.setAreaName(userAddressPO.getAreaName());
        addressInfo.setTownName(userAddressPO.getTownName());
        addressInfo.setDetailAddress(userAddressPO.getDetailAddress());
        userInfo.setAddressInfo(addressInfo);
        order.setUserInfo(userInfo);

        AmountInfoOfOrder amountInfoOfOrder = new AmountInfoOfOrder(orderId);
        amountInfoOfOrder.setPriceOfPayment(amountOfOrderPO.getPriceOfPayment());
        amountInfoOfOrder.setPriceOfCommodity(amountOfOrderPO.getPriceOfCommodity());
        amountInfoOfOrder.setCredit(amountOfOrderPO.getCredit());
        ArrayList<CouponOfOrder> couponOfOrders = couponOfOrderPOS.stream().collect(
                ArrayList<CouponOfOrder>::new,
                (con, couponOfOrderPO) -> {
                    Long couponId = couponOfOrderPO.getCouponId();
                    CouponOfOrder couponOfOrder = new CouponOfOrder(orderId, couponId);
                    couponOfOrder.setThresholdPrice(couponOfOrderPO.getThresholdPrice());
                    couponOfOrder.setCounteractPrice(couponOfOrderPO.getCounteractPrice());
                    couponOfOrder.setCouponId(couponOfOrderPO.getCouponId());
                    con.add(couponOfOrder);
                },
                ArrayList<CouponOfOrder>::addAll
        );
        amountInfoOfOrder.setCouponOfOrders(couponOfOrders);
        order.setAmountInfo(amountInfoOfOrder);

        return order;
    }

    private void checkUser(UserInfoRpcDTO userInfoRpcDTO) {
        if (userInfoRpcDTO == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }
}
