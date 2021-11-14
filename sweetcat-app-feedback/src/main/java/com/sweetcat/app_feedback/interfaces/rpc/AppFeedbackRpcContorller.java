package com.sweetcat.app_feedback.interfaces.rpc;

import com.sweetcat.api.rpcdto.appfeedback.AppFeedbackRpcDTO;
import com.sweetcat.app_feedback.domain.feedback.entity.AppFeedback;
import com.sweetcat.app_feedback.interfaces.facade.AppFeedbackFacade;
import com.sweetcat.app_feedback.interfaces.facade.assembler.AppFeedbackAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-15:33
 * @version: 1.0
 */
@RestController
@RequestMapping("/rpc/feedback")
public class AppFeedbackRpcContorller {
    private AppFeedbackAssembler feedbackAssembler;
    private AppFeedbackFacade feedbackFacade;

    @Autowired
    public void setFeedbackAssembler(AppFeedbackAssembler feedbackAssembler) {
        this.feedbackAssembler = feedbackAssembler;
    }

    @Autowired
    public void setFeedbackFacade(AppFeedbackFacade feedbackFacade) {
        this.feedbackFacade = feedbackFacade;
    }

    @GetMapping("/{feedback_id}")
    public AppFeedbackRpcDTO findByFeedbackId(@PathVariable("feedback_id") Long feedbackId) {
        AppFeedback feedback = feedbackFacade.findOneByFeedbackId(feedbackId);
        if (feedback == null) {
            return null;
        }
        return feedbackAssembler.converterToAppFeedbackRpcDTO(feedback);
    }
}
