package com.sweetcat.api.rpcdto.couponcenter;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-21:59
 * @version: 1.0
 */
@Data
public class CouponRpcDTO {
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
     * 库存
     */
    private Long stock;

    public CouponRpcDTO(Long couponId, BigDecimal thresholdPrice, BigDecimal counteractPrice, Long stock) {
        this.couponId = couponId;
        this.thresholdPrice = thresholdPrice;
        this.counteractPrice = counteractPrice;
        this.stock = stock;
    }
}
