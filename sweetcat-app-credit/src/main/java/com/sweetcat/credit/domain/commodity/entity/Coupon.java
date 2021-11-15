package com.sweetcat.credit.domain.commodity.entity;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-18:07
 * @version: 1.0
 */
public class Coupon extends BaseCommodity{
    /**
     * 优惠券id
     */
    private Long couponId;
    /**
     * 最低可使用价格
     */
    private BigDecimal thresholdPrice;
    /**
     *
     */
    private BigDecimal counteractPrice;

    public Coupon(Long marketItemId) {
        super(marketItemId);
    }
}
