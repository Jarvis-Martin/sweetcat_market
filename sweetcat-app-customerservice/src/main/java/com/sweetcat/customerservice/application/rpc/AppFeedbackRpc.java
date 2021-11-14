package com.sweetcat.customerservice.application.rpc;

import com.sweetcat.api.rpcdto.appfeedback.AppFeedbackRpcDTO;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-17:02
 * @version: 1.0
 */
public interface AppFeedbackRpc {
    AppFeedbackRpcDTO findByFeedbackId(Long feedbackId);
}
