package com.sweetcat.api.rpcdto.userorder;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-18:38
 * @version: 1.0
 */
@Getter
public class CouponRpcDTO implements Cloneable{
    private Long orderId;
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

    public CouponRpcDTO(Long orderId, Long couponId) {
        this.setOrderId(orderId);
        this.couponId = couponId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
    public CouponRpcDTO clone() throws CloneNotSupportedException {
        return ((CouponRpcDTO) super.clone());
    }
}
