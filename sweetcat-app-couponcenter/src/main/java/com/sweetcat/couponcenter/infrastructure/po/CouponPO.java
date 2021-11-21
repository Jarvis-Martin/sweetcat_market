package com.sweetcat.couponcenter.infrastructure.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_app_couponcenter
 * @author 
 */
@Data
public class CouponPO implements Serializable {
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
     * 创建人id
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 优惠券库存
     */
    private Long stock;

    private static final long serialVersionUID = 1L;
}