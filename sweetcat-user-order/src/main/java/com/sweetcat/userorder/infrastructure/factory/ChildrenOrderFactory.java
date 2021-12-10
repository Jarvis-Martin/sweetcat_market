package com.sweetcat.userorder.infrastructure.factory;

import com.sweetcat.userorder.domain.order.entity.*;
import com.sweetcat.userorder.infrastructure.po.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/1-22:13
 * @version: 1.0
 */
@Component
public class ChildrenOrderFactory {
    public ChildrenOrder create(ChildrenOrderPO childrenOrderPO, AddressPO addressPO,
                                List<AmountOfCommodityPO> amountOfCommodityPOPage, AmountOfOrderPO amountOfOrderPO,
                                List<CommodityInfoPO> commodityInfoPOs, List<CouponPO> couponPOs, StoreInfoOfCommodityPO storePO,
                                TimeInfoPO timeInfoPO) {
        Long childrenOrderId = childrenOrderPO.getChildrenOrderId();
        ChildrenOrder childrenOrder = new ChildrenOrder(childrenOrderPO.getParentOrderId());
        childrenOrder.setParentOrderId(childrenOrderPO.getParentOrderId());
        childrenOrder.setChildrenOrderId(childrenOrderId);
        childrenOrder.setType(Order.TYPE_SPLITED);
        childrenOrder.setUserId(childrenOrderPO.getUserId());
        childrenOrder.setOrderStatus(childrenOrderPO.getOrderStatus());
        ArrayList<CommodityInfo> commodityInfos = commodityInfoPOs.stream().collect(
                ArrayList<CommodityInfo>::new,
                (con, commodityInfoPO) -> {
                    CommodityInfo commodityInfo = new CommodityInfo(commodityInfoPO.getOrderId(), commodityInfoPO.getCommodityId());
                    commodityInfo.setCommodityName(commodityInfoPO.getCommodityName());
                    commodityInfo.setCommodityPicSmall(commodityInfoPO.getCommodityPicSmall());
                    commodityInfo.setPriceWhenMakeOrder(commodityInfoPO.getPriceWhenMakeOrder());
                    commodityInfo.setSpecification(commodityInfoPO.getSpecification());
                    commodityInfo.setCount(commodityInfoPO.getCount());
                    // 拆分后，子订单所有商品同属一家店铺
                    StoreInfo storeInfo = new StoreInfo(storePO.getOrderId(), storePO.getStoreId());
                    storeInfo.setStoreName(storePO.getStoreName());
                    storeInfo.setStoreLogo(storePO.getStoreLogo());
                    commodityInfo.setStoreInfo(storeInfo);

                    AmountInfoOfCommodity amountInfoOfCommodity = new AmountInfoOfCommodity(commodityInfoPO.getOrderId());
                    amountInfoOfCommodity.setCommodityId(commodityInfoPO.getCommodityId());
                    amountInfoOfCommodity.setPriceOfPayment(amountOfOrderPO.getPriceOfPayment());
                    amountInfoOfCommodity.setPriceOfCommodity(amountOfOrderPO.getPriceOfCommodity());

                    DiscountPriceInfo discountPriceInfo = new DiscountPriceInfo();
                    // 获取何当前循环的商品id相同的AmountOfCommodityPO
                    AmountOfCommodityPO amountOfCommodityPO1 = amountOfCommodityPOPage.stream().filter(amountOfCommodityPO -> amountOfCommodityPO.getCommodityId().equals(commodityInfoPO.getCommodityId())).collect(Collectors.toList()).get(0);
                    discountPriceInfo.setCredit(amountOfCommodityPO1.getCredit());
                    ArrayList<Coupon> coupons = couponPOs.stream().collect(
                            ArrayList<Coupon>::new,
                            (con1, couponPO) -> {
                                Coupon coupon = new Coupon(couponPO.getOrderId(), couponPO.getCouponId());
                                coupon.setCounteractPrice(couponPO.getCounteractPrice());
                                coupon.setThresholdPrice(couponPO.getThresholdPrice());
                                con1.add(coupon);
                            },
                            ArrayList<Coupon>::addAll
                    );
                    discountPriceInfo.setCoupons(coupons);
                    amountInfoOfCommodity.setDiscountPriceInfo(discountPriceInfo);
                    commodityInfo.setAmountInfo(amountInfoOfCommodity);

                    con.add(commodityInfo);
                },
                ArrayList<CommodityInfo>::addAll
        );
        childrenOrder.setCommodityInfoList(commodityInfos);

        OrderInfo orderInfo = new OrderInfo(childrenOrderId);
        orderInfo.setOrderStatus(childrenOrderPO.getOrderStatus());
        childrenOrder.setOrderInfo(orderInfo);

        TimeInfo timeInfo = new TimeInfo(childrenOrderPO.getChildrenOrderId());
        timeInfo.setPlaceOrderTime(timeInfoPO.getPlaceOrderTime());
        timeInfo.setPayOrderTime(timeInfoPO.getPayOrderTime());
        timeInfo.setTimeOutTime(timeInfoPO.getTimeOutTime());
        timeInfo.setCancelOrderTime(timeInfoPO.getCancelOrderTime());
        timeInfo.setFinishOrderTime(timeInfoPO.getFinishOrderTime());
        timeInfo.setDeliverCommodityTime(timeInfoPO.getDeliverCommodityTime());
        timeInfo.setReceivedCommodityTime(timeInfoPO.getReceivedCommodityTime());
        childrenOrder.setTimeInfo(timeInfo);

        UserInfo userInfo = new UserInfo(addressPO.getUserId());
        AddressInfo addressInfo = new AddressInfo(childrenOrderId, addressPO.getAddressId());
        addressInfo.setReceiverName(addressPO.getReceiverName());
        addressInfo.setReceiverPhone(addressPO.getReceiverPhone());
        addressInfo.setProvinceName(addressPO.getProvinceName());
        addressInfo.setCityName(addressPO.getCityName());
        addressInfo.setAreaName(addressPO.getAreaName());
        addressInfo.setTownName(addressPO.getTownName());
        addressInfo.setDetailAddress(addressPO.getDetailAddress());
        userInfo.setAddressInfo(addressInfo);
        childrenOrder.setUserInfo(userInfo);

        AmountInfo amountInfo = new AmountInfo(orderInfo.getOrderId());
        amountInfo.setPriceOfPayment(amountOfOrderPO.getPriceOfPayment());
        amountInfo.setPriceOfCommodity(amountOfOrderPO.getPriceOfCommodity());
        amountInfo.setCredit(amountOfOrderPO.getCredit());
        ArrayList<Coupon> coupons = couponPOs.stream().collect(
                ArrayList<Coupon>::new,
                (con1, couponPO) -> {
                    Coupon coupon = new Coupon(couponPO.getOrderId(), couponPO.getCouponId());
                    coupon.setCounteractPrice(couponPO.getCounteractPrice());
                    coupon.setThresholdPrice(couponPO.getThresholdPrice());
                    con1.add(coupon);
                },
                ArrayList<Coupon>::addAll
        );
        amountInfo.setCoupons(coupons);
        childrenOrder.setAmountInfo(amountInfo);

        return childrenOrder;
    }
}
