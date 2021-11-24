package com.sweetcat.customerservice.infrastructure.factory;

import com.sweetcat.customerservice.domain.feedback.entity.Feedback;
import com.sweetcat.customerservice.domain.feedback.entity.Informer;
import com.sweetcat.customerservice.domain.feedback.entity.Receiver;
import com.sweetcat.customerservice.infrastructure.po.FeedbackPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-14:10
 * @version: 1.0
 */
@Component
public class FeedbackFactory {
    public Feedback create(FeedbackPO feedbackPO) {
        Feedback feedback = new Feedback(feedbackPO.getRecordId(), feedbackPO.getFeedbackId());
        feedback.setCreateTime(feedbackPO.getCreateTime());
        feedback.setUpdateTime(feedbackPO.getUpdateTime());
        feedback.setProcessTime(feedbackPO.getProcessTime());
        Informer informer = new Informer(feedbackPO.getStaffId());
        Receiver receiver = new Receiver(feedbackPO.getReceiverId());
        feedback.setStatus(feedbackPO.getStatus());
        feedback.setInformer(informer);
        feedback.setReceiver(receiver);
        feedback.setResponseContent(feedbackPO.getResponseContent());
        feedback.setResponseTitle(feedbackPO.getResponseTitle());
        return feedback;
    }
}
