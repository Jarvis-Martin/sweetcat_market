package com.sweetcat.usercoupon.infrastructure.po;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * t_user_coupon
 * @author 
 */
@Data
public class UserCouponPO implements Serializable {
    /**
     * 记录id
     */
    private Long recordId;

    /**
     * 用户id
     */
    private Long userId;

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
     * 创建时间/获得优惠券的时间
     */
    private Long obtainTime;

    /**
     * 优惠券类型；0商品券，1通用券
     */
    private Integer targetType;

    private static final long serialVersionUID = 1L;
}