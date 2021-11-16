package com.sweetcat.credit.application.event.subscribe;

import com.sweetcat.commons.domainevent.creditcenter.CreditCenterCheckedInEvent;
import com.sweetcat.credit.application.command.AddCreditLogCommand;
import com.sweetcat.credit.application.service.CreditLogApplicationService;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/16-15:13
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "credit_center_topic",
        selectorType = SelectorType.TAG,
        selectorExpression = "checkin",
        consumerGroup = "CG_sweetcat_app_credit_checkin",
        consumeMode = ConsumeMode.ORDERLY
)
public class CreditCenterCheckedInEventSubscriber implements RocketMQListener<CreditCenterCheckedInEvent> {
    private CreditLogApplicationService logApplicationService;

    @Autowired
    public void setLogApplicationService(CreditLogApplicationService logApplicationService) {
        this.logApplicationService = logApplicationService;
    }

    @Override
    public void onMessage(CreditCenterCheckedInEvent creditCenterCheckedInEvent) {
        System.out.println("sweetcat-app-credit 接收到领域事件 CreditCenterCheckedInEvent 时间为：" + Instant.now().toEpochMilli());
        AddCreditLogCommand command = new AddCreditLogCommand(creditCenterCheckedInEvent.getUserId());
        command.setLogType(creditCenterCheckedInEvent.getLogType());
        command.setDescription(creditCenterCheckedInEvent.getDescription());
        command.setCreditNumber(creditCenterCheckedInEvent.getCreditNumber());
        command.setCreateTime(creditCenterCheckedInEvent.getOccurOn());
        logApplicationService.addOne(command);
    }
}
