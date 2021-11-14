package com.sweetcat.customerservice.infrastructure.rpc;

import com.sweetcat.api.client.AppFeedbackClient;
import com.sweetcat.api.rpcdto.appfeedback.AppFeedbackRpcDTO;
import com.sweetcat.customerservice.application.rpc.AppFeedbackRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-17:15
 * @version: 1.0
 */
@Component
public class AppFeedbackRpcImpl implements AppFeedbackRpc {
    private AppFeedbackClient feedbackClient;

    @Autowired
    public void setFeedbackClient(AppFeedbackClient feedbackClient) {
        this.feedbackClient = feedbackClient;
    }

    @Override
    public AppFeedbackRpcDTO findByFeedbackId(Long feedbackId) {
        return feedbackClient.findByFeedbackId(feedbackId);
    }
}
