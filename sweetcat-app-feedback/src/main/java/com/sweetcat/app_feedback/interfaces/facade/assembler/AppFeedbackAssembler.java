package com.sweetcat.app_feedback.interfaces.facade.assembler;

import com.sweetcat.api.rpcdto.appfeedback.AppFeedbackRpcDTO;
import com.sweetcat.app_feedback.domain.feedback.entity.AppFeedback;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-16:29
 * @version: 1.0
 */
@Component
public class AppFeedbackAssembler {
    public AppFeedbackRpcDTO converterToAppFeedbackRpcDTO(AppFeedback feedback) {
        AppFeedbackRpcDTO appFeedbackRpcDTO = new AppFeedbackRpcDTO();
        appFeedbackRpcDTO.setFeedbackId(feedback.getFeedbackId());
        appFeedbackRpcDTO.setUserId(feedback.getUserId());
        appFeedbackRpcDTO.setContent(feedback.getContent());
        appFeedbackRpcDTO.setFeedbackPics(feedback.getFeedbackPics());
        appFeedbackRpcDTO.setStatus(feedback.getStatus());
        appFeedbackRpcDTO.setCreateTime(feedback.getCreateTime());
        appFeedbackRpcDTO.setProcessorId(feedback.getProcessorId());
        appFeedbackRpcDTO.setResponseContent(feedback.getResponseContent());
        appFeedbackRpcDTO.setResponseTitle(feedback.getResponseTitle());
        appFeedbackRpcDTO.setProcessTime(feedback.getProcessTime());
        return appFeedbackRpcDTO;
    }
}
