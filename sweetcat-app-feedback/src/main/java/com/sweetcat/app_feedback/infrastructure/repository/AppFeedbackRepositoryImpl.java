package com.sweetcat.app_feedback.infrastructure.repository;

import com.sweetcat.app_feedback.domain.feedback.entity.AppFeedback;
import com.sweetcat.app_feedback.domain.feedback.repository.AppFeedbackRepository;
import com.sweetcat.app_feedback.infrastructure.dao.AppFeedbackMapper;
import com.sweetcat.app_feedback.infrastructure.factory.AppFeedbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/6-19:09
 * @Version: 1.0
 */
@Repository
public class AppFeedbackRepositoryImpl implements AppFeedbackRepository {
    private AppFeedbackMapper feedbackMapper;
    private AppFeedbackFactory feedbackFactory;

    @Autowired
    public void setFeedbackMapper(AppFeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

    @Autowired
    public void setFeedbackFactory(AppFeedbackFactory feedbackFactory) {
        this.feedbackFactory = feedbackFactory;
    }

    @Override
    public void add(AppFeedback feedback) {
        feedbackMapper.insertOne(feedback);
    }
}
