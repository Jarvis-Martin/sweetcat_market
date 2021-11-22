package com.sweetcat.commons.domainevent;

import lombok.Data;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-23:10
 * @version: 1.0
 */
@Data
public class ConsumedCouponEvent {
    /**
     * 使用优惠券的人
     */
    private Long userId;
    /**
     * 被使用的优惠券
     */
    private Long couponId;
    /**
     * 使用事件
     */
    private Long consumeTime;
    /**
     * 领域事件触发时间
     */
    private Long occurOne;
}
