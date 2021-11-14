package com.sweetcat.customerservice.interfaces.facade;

import com.sweetcat.customerservice.application.command.AddFeedbackCommand;
import com.sweetcat.customerservice.application.command.ProcessFeedbackCommand;
import com.sweetcat.customerservice.application.service.FeedbackApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-20:54
 * @version: 1.0
 */
@Component
public class FeedbackFacade {
    private FeedbackApplicationService feedbackApplicationService;

    @Autowired
    public void setFeedbackApplicationService(FeedbackApplicationService feedbackApplicationService) {
        this.feedbackApplicationService = feedbackApplicationService;
    }

    public void processFeedback(ProcessFeedbackCommand command) {
        feedbackApplicationService.processFeedback(command);
    }

    public void addOne(AddFeedbackCommand command) {
        feedbackApplicationService.addOne(command);
    }
}
