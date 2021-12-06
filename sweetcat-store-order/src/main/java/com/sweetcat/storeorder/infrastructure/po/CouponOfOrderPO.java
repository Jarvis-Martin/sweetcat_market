package com.sweetcat.storeorder.infrastructure.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_coupon_of_order
 * @author 
 */
@Data
public class CouponOfOrderPO implements Serializable {
    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 订单id
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

    private static final long serialVersionUID = 1L;
}