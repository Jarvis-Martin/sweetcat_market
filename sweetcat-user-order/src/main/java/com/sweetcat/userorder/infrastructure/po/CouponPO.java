package com.sweetcat.userorder.infrastructure.po;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * t_coupon_of_order
 * @author 
 */
@Data
public class CouponPO implements Serializable {
    /**
     * 拆分前订单编号
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