package com.sweetcat.customerservice.domain.feedback.repository;

import com.sweetcat.customerservice.domain.feedback.entity.Feedback;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-13:27
 * @version: 1.0
 */
public interface FeedbackRepository {
    /**
     * find feedback by recordId
     *
     * @param recordId
     * @return
     */
    Feedback findOneByRecordId(Long recordId);

    /**
     * 添加
     *
     * @param feedback
     */
    void addOne(Feedback feedback);

    /**
     * 保存
     *
     * @param feedback
     */
    void save(Feedback feedback);
}
