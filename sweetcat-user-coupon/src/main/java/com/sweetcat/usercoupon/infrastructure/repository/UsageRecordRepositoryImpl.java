package com.sweetcat.usercoupon.infrastructure.repository;

import com.sweetcat.usercoupon.domain.usagerecord.entity.UsageRecord;
import com.sweetcat.usercoupon.domain.usagerecord.repository.UsageRecordeRepository;
import com.sweetcat.usercoupon.infrastructure.dao.UsageRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-22:50
 * @version: 1.0
 */
@Repository
public class UsageRecordRepositoryImpl implements UsageRecordeRepository {
    private UsageRecordMapper usageRecordMapper;

    @Autowired
    public void setUsageRecordMapper(UsageRecordMapper usageRecordMapper) {
        this.usageRecordMapper = usageRecordMapper;
    }

    /**
     * 添加一个 使用记录
     * @param usageRecord
     */
    @Override
    public void addOne(UsageRecord usageRecord) {
        usageRecordMapper.addOne(usageRecord);
    }
}
