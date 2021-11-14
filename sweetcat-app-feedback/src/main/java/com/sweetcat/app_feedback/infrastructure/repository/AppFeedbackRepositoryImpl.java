package com.sweetcat.app_feedback.infrastructure.repository;

import com.sweetcat.app_feedback.domain.feedback.entity.AppFeedback;
import com.sweetcat.app_feedback.domain.feedback.repository.AppFeedbackRepository;
import com.sweetcat.app_feedback.infrastructure.dao.AppFeedbackMapper;
import com.sweetcat.app_feedback.infrastructure.factory.AppFeedbackFactory;
import com.sweetcat.app_feedback.infrastructure.po.AppFeedbackPO;
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

    /**
     * find feedback by feedback id
     * @param feedbackId
     * @return
     */
    @Override
    public AppFeedback findOneByFeedbackId(Long feedbackId) {
        AppFeedbackPO feedbackPO = feedbackMapper.findByFeedbackId(feedbackId);
        if (feedbackPO == null) {
            return null;
        }
        return feedbackFactory.create(feedbackPO);
    }


    /**
     * 保存对 feedback 的更新
     * @param feedback feedback
     */
    @Override
    public void save(AppFeedback feedback) {
        feedbackMapper.update(feedback);
    }
}
