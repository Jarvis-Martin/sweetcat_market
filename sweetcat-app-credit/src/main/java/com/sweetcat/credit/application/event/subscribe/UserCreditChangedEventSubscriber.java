package com.sweetcat.credit.application.event.subscribe;

import com.sweetcat.commons.domainevent.creditcenter.UserCreditChangedEvent;
import com.sweetcat.credit.application.command.AddCreditLogCommand;
import com.sweetcat.credit.application.service.CreditLogApplicationService;
import com.sweetcat.credit.application.service.UserApplicationService;
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
        selectorExpression = "credit_change",
        consumerGroup = "CG_sweetcat_app_credit_checkin",
        consumeMode = ConsumeMode.ORDERLY
)
public class UserCreditChangedEventSubscriber implements RocketMQListener<UserCreditChangedEvent> {
    private CreditLogApplicationService logApplicationService;
    private UserApplicationService userApplicationService;

    @Autowired
    public void setUserApplicationService(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Autowired
    public void setLogApplicationService(CreditLogApplicationService logApplicationService) {
        this.logApplicationService = logApplicationService;
    }

    @Override
    public void onMessage(UserCreditChangedEvent userCreditChangedEvent) {
        System.out.println("sweetcat-app-credit 接收到领域事件 CreditCenterCheckedInEvent 时间为：" + Instant.now().toEpochMilli());
        Long userId = userCreditChangedEvent.getUserId();
        Long incrementOfCredit = userCreditChangedEvent.getCreditNumber();
        Long occurTime = userCreditChangedEvent.getOccurOn();
        AddCreditLogCommand command = new AddCreditLogCommand(userId);
        command.setLogType(userCreditChangedEvent.getLogType());
        command.setDescription(userCreditChangedEvent.getDescription());
        command.setCreditNumber(Math.abs(incrementOfCredit));
        command.setCreateTime(occurTime);
        logApplicationService.addOne(command);
        // 更新用户积分
        userApplicationService.updateUserCredit(userId, incrementOfCredit, occurTime);
    }
}
