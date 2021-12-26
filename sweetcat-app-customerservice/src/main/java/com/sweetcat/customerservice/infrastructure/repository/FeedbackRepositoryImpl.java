package com.sweetcat.customerservice.infrastructure.repository;

import com.sweetcat.customerservice.domain.feedback.entity.Feedback;
import com.sweetcat.customerservice.domain.feedback.repository.FeedbackRepository;
import com.sweetcat.customerservice.infrastructure.dao.FeedbackMapper;
import com.sweetcat.customerservice.infrastructure.factory.FeedbackFactory;
import com.sweetcat.customerservice.infrastructure.po.FeedbackPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-13:30
 * @version: 1.0
 */
@Repository
public class FeedbackRepositoryImpl implements FeedbackRepository {
    private FeedbackMapper feedbackMapper;
    private FeedbackFactory feedbackFactory;

    @Autowired
    public void setFeedbackFactory(FeedbackFactory feedbackFactory) {
        this.feedbackFactory = feedbackFactory;
    }

    @Autowired
    public void setFeedbackMapper(FeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

    /**
     * find feedback by recordId
     *
     * @param recordId
     * @return
     */
    @Override
    public Feedback findOneByRecordId(Long recordId) {
        FeedbackPO feedbackPO = feedbackMapper.findOneByRecordId(recordId);
        if (feedbackPO == null) {
            return null;
        }
        return feedbackFactory.create(feedbackPO);
    }

    /**
     * 添加
     *
     * @param feedback
     */
    @Override
    public void addOne(Feedback feedback) {
        feedbackMapper.addOne(feedback);
    }

    /**
     * 保存
     *
     * @param feedback
     */
    @Override
    public void save(Feedback feedback) {
        feedbackMapper.save(feedback);
    }
}
