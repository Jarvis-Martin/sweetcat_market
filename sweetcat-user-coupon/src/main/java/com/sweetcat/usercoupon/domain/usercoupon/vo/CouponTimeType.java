package com.sweetcat.usercoupon.domain.usercoupon.vo;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-21:23
 * @version: 1.0
 */
public class CouponTimeType {
    /**
     * 时长型：自领取日期，validaDuration 内有效
     */
    public static final Integer TIMETYPE_DURATION = 0;
    /**
     * 时间区间型：自 startTime ~ deadline 内有效
     */
    public static final Integer TIMETYPE_TIMEZOON = 1;

}
