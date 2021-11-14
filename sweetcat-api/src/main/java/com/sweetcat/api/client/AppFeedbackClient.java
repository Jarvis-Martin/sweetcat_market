package com.sweetcat.api.client;

import com.sweetcat.api.rpcdto.appfeedback.AppFeedbackRpcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-17:15
 * @version: 1.0
 */
@FeignClient("sweetcat-app-feedback")
public interface AppFeedbackClient {

    @GetMapping("/rpc/feedback/{feedback_id}")
    AppFeedbackRpcDTO findByFeedbackId(@PathVariable("feedback_id") Long feedbackId);
}
