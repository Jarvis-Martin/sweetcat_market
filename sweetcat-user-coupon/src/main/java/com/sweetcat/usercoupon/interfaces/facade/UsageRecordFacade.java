package com.sweetcat.usercoupon.interfaces.facade;

import com.sweetcat.usercoupon.application.service.UsageRecordApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-23:05
 * @version: 1.0
 */
@Component
public class UsageRecordFacade {
    private UsageRecordApplicationService usageRecordApplicationService;

    @Autowired
    public void setUsageRecordApplicationService(UsageRecordApplicationService usageRecordApplicationService) {
        this.usageRecordApplicationService = usageRecordApplicationService;
    }

    /**
     * 消费(使用)一张优惠券
     *
     * @param userId
     * @param couponId
     * @param consumeTime
     */
    public void consumeOneCoupon(Long userId, Long couponId, Long consumeTime) {
        usageRecordApplicationService.consumeOneCoupon(userId, couponId, consumeTime);
    }
}
