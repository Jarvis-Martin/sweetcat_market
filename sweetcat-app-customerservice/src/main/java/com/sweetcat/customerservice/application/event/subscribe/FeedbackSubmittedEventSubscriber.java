package com.sweetcat.customerservice.application.event.subscribe;

import com.sweetcat.commons.domainevent.appfeedback.FeedbackSubmittedEvent;
import com.sweetcat.customerservice.application.command.AddFeedbackCommand;
import com.sweetcat.customerservice.application.service.FeedbackApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-18:11
 * @version: 1.0
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "feedback_topic",
        consumerGroup = "sweetcat-app-customerservice",
        consumeMode = ConsumeMode.ORDERLY
)
public class FeedbackSubmittedEventSubscriber implements RocketMQListener<FeedbackSubmittedEvent> {
    private FeedbackApplicationService feedbackApplicationService;

    @Autowired
    public void setFeedbackApplicationService(FeedbackApplicationService feedbackApplicationService) {
        this.feedbackApplicationService = feedbackApplicationService;
    }

    @Override
    public void onMessage(FeedbackSubmittedEvent event) {
        System.out.println("sweetcat-app-customerservice 接收到领域事件 FeedbackSubmittedEvent 时间为：" + Instant.now().toEpochMilli());
        // 创建间 添加反馈命令
        AddFeedbackCommand command = new AddFeedbackCommand();
        command.setFeedbackId(event.getFeedbackId());
        command.setCreateTime(event.getOccurOn());
        command.setReceiverId(event.getReceiverId());
        // 添加入db
        feedbackApplicationService.addOne(command);
    }
}
