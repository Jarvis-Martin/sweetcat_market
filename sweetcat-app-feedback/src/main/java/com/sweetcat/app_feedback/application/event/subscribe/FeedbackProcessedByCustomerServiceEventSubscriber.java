package com.sweetcat.app_feedback.application.event.subscribe;

import com.sweetcat.app_feedback.application.command.ProcessFeedbackCommand;
import com.sweetcat.app_feedback.application.service.AppFeedbackApplicationService;
import com.sweetcat.commons.domainevent.appcustomerservice.FeedbackProcessedByCustomerServiceEvent;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-19:27
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "customer_service_topic",
        consumerGroup = "sweetcat-app-feedback",
        consumeMode = ConsumeMode.ORDERLY
)
public class FeedbackProcessedByCustomerServiceEventSubscriber implements RocketMQListener<FeedbackProcessedByCustomerServiceEvent> {
    private AppFeedbackApplicationService feedbackApplicationService;

    @Autowired
    public void setFeedbackApplicationService(AppFeedbackApplicationService feedbackApplicationService) {
        this.feedbackApplicationService = feedbackApplicationService;
    }

    @Override
    public void onMessage(FeedbackProcessedByCustomerServiceEvent feedbackProcessedByCustomerServiceEvent) {
        System.out.println("sweetcat-app-customerservice 接收到领域事件 feedbackProcessedByCustomerServiceEvent 时间为：" + Instant.now().toEpochMilli());
        ProcessFeedbackCommand processFeedbackCommand = new ProcessFeedbackCommand();
        processFeedbackCommand.setFeedbackId(feedbackProcessedByCustomerServiceEvent.getFeedbackId());
        processFeedbackCommand.setProcessorId(feedbackProcessedByCustomerServiceEvent.getProcessorId());
        processFeedbackCommand.setProcessTime(feedbackProcessedByCustomerServiceEvent.getProcessTime());
        processFeedbackCommand.setResponseContent(feedbackProcessedByCustomerServiceEvent.getResponseContent());
        feedbackApplicationService.processFeedback(processFeedbackCommand);
    }
}
