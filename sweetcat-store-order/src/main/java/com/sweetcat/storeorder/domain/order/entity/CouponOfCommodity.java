package com.sweetcat.storeorder.domain.order.entity;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-18:38
 * @version: 1.0
 */
@Getter
public class CouponOfCommodity implements Cloneable{
    private Long orderId;
    /**
     * 使用该优惠券的商品的id
     */
    private Long commodityId;
    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 最低可使用价格
     */
    private BigDecimal thresholdPrice;

    /**
     * 抵扣价格
     */
    private BigDecimal counteractPrice;

    public CouponOfCommodity(Long orderId, Long commodityId, Long couponId) {
        this.orderId = orderId;
        this.commodityId = commodityId;
        this.couponId = couponId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public void setThresholdPrice(BigDecimal thresholdPrice) {
        this.thresholdPrice = thresholdPrice;
    }

    public void setCounteractPrice(BigDecimal counteractPrice) {
        this.counteractPrice = counteractPrice;
    }

    @Override
    public CouponOfCommodity clone() throws CloneNotSupportedException {
        return ((CouponOfCommodity) super.clone());
    }
}
