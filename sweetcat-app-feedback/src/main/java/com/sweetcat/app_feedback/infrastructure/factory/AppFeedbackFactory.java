package com.sweetcat.app_feedback.infrastructure.factory;

import com.sweetcat.app_feedback.domain.feedback.entity.AppFeedback;
import com.sweetcat.app_feedback.infrastructure.po.AppFeedbackPO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/6-19:10
 * @Version: 1.0
 */
@Component
public class AppFeedbackFactory {
    public AppFeedback create(AppFeedbackPO feedbackPO) {
        AppFeedback appFeedback = new AppFeedback(feedbackPO.getFeedbackId());
        appFeedback.setUserId(feedbackPO.getUserId());
        appFeedback.setContent(feedbackPO.getContent());
        appFeedback.setFeedbackPics(feedbackPO.getFeedbackPics());
        appFeedback.setStatus(feedbackPO.getStatus());
        appFeedback.setCreateTime(feedbackPO.getCreateTime());
        appFeedback.setProcessTime(feedbackPO.getProcessTime());
        appFeedback.setResponseTitle(feedbackPO.getResponseTitle());
        return appFeedback;
    }

}
