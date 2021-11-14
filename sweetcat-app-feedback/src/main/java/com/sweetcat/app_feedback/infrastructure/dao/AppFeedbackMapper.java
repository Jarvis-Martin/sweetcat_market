package com.sweetcat.app_feedback.infrastructure.dao;

import com.sweetcat.app_feedback.domain.feedback.entity.AppFeedback;
import com.sweetcat.app_feedback.infrastructure.po.AppFeedbackPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppFeedbackMapper {

    void insertOne(AppFeedback feedback);

    AppFeedbackPO findByFeedbackId(Long feedbackId);

    void update(AppFeedback feedback);
}