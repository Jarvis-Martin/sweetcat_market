package com.sweetcat.usercoupon.domain.usagerecord.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/21-20:57
 * @version: 1.0
 */
@Getter
public class UsageRecord {
    /**
     * 失效
     */
    public static final Integer USAGETYPE_INVALIDATE = 0;
    /**
     * 正常使用
     */
    public static final Integer USAGETYPE_USE = 1;
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
    private Integer type;

    /**
     * 使用时间/失效时间
     */
    private Long time;

    public UsageRecord(Long recordId, Long userId, Long couponId) {
        this.recordId = recordId;
        this.userId = userId;
        this.couponId = couponId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
