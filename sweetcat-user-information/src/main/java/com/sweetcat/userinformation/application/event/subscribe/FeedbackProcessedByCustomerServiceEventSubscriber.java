package com.sweetcat.userinformation.application.event.subscribe;

import com.sweetcat.commons.domainevent.appcustomerservice.FeedbackProcessedByCustomerServiceEvent;
import com.sweetcat.userinformation.application.command.AddSystemInformationCommand;
import com.sweetcat.userinformation.application.service.InformationApplicationService;
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
        consumerGroup = "sweetcat-user-information",
        consumeMode = ConsumeMode.ORDERLY
)
public class FeedbackProcessedByCustomerServiceEventSubscriber implements RocketMQListener<FeedbackProcessedByCustomerServiceEvent> {
    private InformationApplicationService informationApplicationService;

    @Autowired
    public void setInformationApplicationService(InformationApplicationService informationApplicationService) {
        this.informationApplicationService = informationApplicationService;
    }

    @Override
    public void onMessage(FeedbackProcessedByCustomerServiceEvent event) {
        System.out.println("sweetcat-user-information 接收到领域事件 feedbackProcessedByCustomerServiceEvent 时间为：" + Instant.now().toEpochMilli());
        AddSystemInformationCommand addSystemInformationCommand = new AddSystemInformationCommand();
        inflateAddSystemInformationCommand(event, addSystemInformationCommand);
        informationApplicationService.addOneSystemInformation(addSystemInformationCommand);
    }



    private void inflateAddSystemInformationCommand(FeedbackProcessedByCustomerServiceEvent event, AddSystemInformationCommand addSystemInformationCommand) {
        addSystemInformationCommand.setInformationId(event.getFeedbackId());
        addSystemInformationCommand.setPublisherId(event.getProcessorId());
        addSystemInformationCommand.setReceiverId(event.getReceiverId());
        addSystemInformationCommand.setContent(event.getResponseContent());
        addSystemInformationCommand.setContentPics(null);
        addSystemInformationCommand.setCreateTime(event.getOccurOn());
        addSystemInformationCommand.setProcessTime(event.getProcessTime());
        addSystemInformationCommand.setResponseTitle(event.getResponseTitle());
    }
}
