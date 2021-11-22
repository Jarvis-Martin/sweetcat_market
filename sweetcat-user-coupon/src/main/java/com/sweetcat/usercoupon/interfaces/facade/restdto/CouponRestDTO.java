package com.sweetcat.usercoupon.interfaces.facade.restdto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-21:59
 * @version: 1.0
 */
@Data
public class CouponRestDTO {
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

    /**
     * 获得时间
     */
    public Long obtainTime;

    public CouponRestDTO(Long couponId, BigDecimal thresholdPrice, BigDecimal counteractPrice, Long obtainTime) {
        this.couponId = couponId;
        this.thresholdPrice = thresholdPrice;
        this.counteractPrice = counteractPrice;
        this.obtainTime = obtainTime;
    }
}
