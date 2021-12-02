package com.sweetcat.userorder.infrastructure.factory;

import com.sweetcat.userorder.domain.order.entity.*;
import com.sweetcat.userorder.infrastructure.po.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/1-22:13
 * @version: 1.0
 */
@Component
public class ChildrenOrderFactory {
    public ChildrenOrder create(ChildrenOrderPO childrenOrderPO, AddressPO addressPO,
                                AmountOfCommodityPO amountOfCommodityPO, AmountOfOrderPO amountOfOrderPO,
                                List<CommodityInfoPO> commodityInfoPOs, List<CouponPO> couponPOs, StoreInfoOfCommodityPO storePO,
                                TimeInfoPO timeInfoPO) {
        ChildrenOrder childrenOrder = new ChildrenOrder(childrenOrderPO.getChildrenOrderId());
        childrenOrder.setParentOrderId(childrenOrderPO.getParentOrderId());
        childrenOrder.setChildrenOrderId(childrenOrderPO.getChildrenOrderId());
        childrenOrder.setType(Order.TYPE_SPLITED);
        ArrayList<CommodityInfo> commodityInfos = commodityInfoPOs.stream().collect(
                ArrayList<CommodityInfo>::new,
                (con, commodityInfoPO) -> {
                    CommodityInfo commodityInfo = new CommodityInfo(commodityInfoPO.getCommodityId());
                    commodityInfo.setCommodityName(commodityInfoPO.getCommodityName());
                    commodityInfo.setCommodityPicSmall(commodityInfoPO.getCommodityPicSmall());
                    commodityInfo.setPriceWhenMakeOrder(commodityInfoPO.getPriceWhenMakeOrder());
                    commodityInfo.setSpecification(commodityInfoPO.getSpecification());
                    commodityInfo.setCount(commodityInfoPO.getCount());
                    // 拆分后，子订单所有商品同属一家店铺
                    StoreInfo storeInfo = new StoreInfo(storePO.getOrderId());
                    storeInfo.setStoreName(storePO.getStoreName());
                    storeInfo.setStoreLogo(storePO.getStoreLogo());
                    commodityInfo.setStoreInfo(storeInfo);

                    AmountInfoOfCommodity amountInfoOfCommodity = new AmountInfoOfCommodity();
                    amountInfoOfCommodity.setPriceOfPayment(amountOfOrderPO.getPriceOfPayment());
                    amountInfoOfCommodity.setPriceOfCommodity(amountOfOrderPO.getPriceOfCommodity());
                    DiscountPriceInfo discountPriceInfo = new DiscountPriceInfo();
                    discountPriceInfo.setCredit(amountOfCommodityPO.getCredit());
                    ArrayList<Coupon> coupons = couponPOs.stream().collect(
                            ArrayList<Coupon>::new,
                            (con1, couponPO) -> {
                                Coupon coupon = new Coupon(couponPO.getCouponId());
                                coupon.setCounteractPrice(couponPO.getCounteractPrice());
                                coupon.setThresholdPrice(couponPO.getThresholdPrice());
                                con1.add(coupon);
                            },
                            ArrayList<Coupon>::addAll
                    );
                    discountPriceInfo.setCoupons(coupons);
                },
                ArrayList<CommodityInfo>::addAll
        );
        childrenOrder.setCommodityInfoList(commodityInfos);
        OrderInfo orderInfo = new OrderInfo(childrenOrder.getChildrenOrderId());
        orderInfo.setOrderStatus(childrenOrderPO.getOrderStatus());
        childrenOrder.setOrderInfo(orderInfo);
        TimeInfo timeInfo = new TimeInfo();
        timeInfo.setPlaceOrderTime(timeInfoPO.getPlaceOrderTime());
        timeInfo.setPayOrderTime(timeInfoPO.getPayOrderTime());
        timeInfo.setTimeOutTime(timeInfoPO.getTimeOutTime());
        timeInfo.setCancelOrderTime(timeInfoPO.getCancelOrderTime());
        timeInfo.setFinishOrderTime(timeInfoPO.getFinishOrderTime());
        timeInfo.setDeliverCommodityTime(timeInfoPO.getDeliverCommodityTime());
        timeInfo.setReceivedCommodityTime(timeInfoPO.getReceivedCommodityTime());
        childrenOrder.setTimeInfo(timeInfo);
        AmountInfo amountInfo = new AmountInfo();
        amountInfo.setPriceOfPayment(amountOfOrderPO.getPriceOfPayment());
        amountInfo.setPriceOfCommodity(amountOfOrderPO.getPriceOfCommodity());
        amountInfo.setCredit(amountOfOrderPO.getCredit());
        ArrayList<Coupon> coupons = couponPOs.stream().collect(
                ArrayList<Coupon>::new,
                (con1, couponPO) -> {
                    Coupon coupon = new Coupon(couponPO.getCouponId());
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
