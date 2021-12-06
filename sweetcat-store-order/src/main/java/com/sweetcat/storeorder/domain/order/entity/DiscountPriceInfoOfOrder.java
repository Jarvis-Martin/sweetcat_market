package com.sweetcat.storeorder.domain.order.entity;

import lombok.Getter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-18:34
 * @version: 1.0
 */
@Getter
public class DiscountPriceInfoOfOrder {
    /**
     * 订单优惠信息对应的订单id
     */
    private Long orderId;
    /**
     * 本订单使用的积分数
     */
    private Integer credit;
    /**
     * 该订单使用到的优惠券信息
     */
    private List<CouponOfOrder> couponOfOrders;

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public void setCouponOfOrders(List<CouponOfOrder> couponOfOrders) {
        this.couponOfOrders = couponOfOrders;
    }
}
