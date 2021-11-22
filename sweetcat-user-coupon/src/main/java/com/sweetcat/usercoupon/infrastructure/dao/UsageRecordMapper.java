package com.sweetcat.usercoupon.infrastructure.dao;

import com.sweetcat.usercoupon.domain.usagerecord.entity.UsageRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsageRecordMapper {
    /**
     * 添加一个 使用记录
     * @param usageRecord
     */
    void addOne(UsageRecord usageRecord);
}