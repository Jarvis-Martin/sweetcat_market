package com.sweetcat.customerservice.infrastructure.dao;

import com.sweetcat.customerservice.domain.feedback.entity.Feedback;
import com.sweetcat.customerservice.infrastructure.po.FeedbackPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedbackMapper {
    /**
     * 添加
     * @param feedback
     */
    void addOne(Feedback feedback);

    /**
     * 保存
     * @param feedback
     */
    void save(Feedback feedback);

    FeedbackPO findOneByRecordId(Long recordId);
}