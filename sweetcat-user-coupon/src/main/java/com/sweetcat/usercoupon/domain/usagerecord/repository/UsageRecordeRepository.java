package com.sweetcat.usercoupon.domain.usagerecord.repository;

import com.sweetcat.usercoupon.domain.usagerecord.entity.UsageRecord;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-22:48
 * @version: 1.0
 */
public interface UsageRecordeRepository {
    /**
     * 添加一个 使用记录
     * @param usageRecord
     */
    void addOne(UsageRecord usageRecord);
}
