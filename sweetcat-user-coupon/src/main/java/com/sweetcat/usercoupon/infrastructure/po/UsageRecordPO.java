package com.sweetcat.usercoupon.infrastructure.po;

import java.io.Serializable;
import lombok.Data;

/**
 * t_user_coupon_usage_record
 * @author 
 */
@Data
public class UsageRecordPO implements Serializable {
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
     * 记录类型。0：失效；1：使用
     */
    private Byte type;

    /**
     * 使用时间/失效时间
     */
    private Long time;

    private static final long serialVersionUID = 1L;
}