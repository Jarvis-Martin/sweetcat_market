package com.sweetcat.app_feedback.domain.feedback.repository;

import com.sweetcat.app_feedback.domain.feedback.entity.AppFeedback;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/6-19:08
 * @Version: 1.0
 */
public interface AppFeedbackRepository {

    /**
     * 添加 feedbac
     * @param feedback feedback
     */
    void add(AppFeedback feedback);
}
