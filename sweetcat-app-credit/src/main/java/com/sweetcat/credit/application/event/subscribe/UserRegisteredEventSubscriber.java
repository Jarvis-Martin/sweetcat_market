package com.sweetcat.credit.application.event.subscribe;

import com.sweetcat.commons.domainevent.userinfo.UserRegisteredEvent;
import com.sweetcat.credit.application.command.AddUserCommand;
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
 * @date: 2021-11-2021/11/14-20:08
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "user_info_topic",
        selectorType = SelectorType.TAG,
        selectorExpression = "user_registered",
        consumerGroup = "CG_sweetcat_app_credit_register",
        consumeMode = ConsumeMode.ORDERLY
)
public class UserRegisteredEventSubscriber implements RocketMQListener<UserRegisteredEvent> {
    private UserApplicationService userApplicationService;

    @Autowired
    public void setUserApplicationService(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Override
    public void onMessage(UserRegisteredEvent userRegisteredEvent) {
        System.out.println("sweetcat-app-credit 接收到领域事件 UserRegisteredEvent 时间为：" + Instant.now().toEpochMilli());
        AddUserCommand addUserCommand = new AddUserCommand(userRegisteredEvent.getUserId());
        addUserCommand.setCreateTime(userRegisteredEvent.getUserRegisterTime());
        userApplicationService.addOne(addUserCommand);
    }
}
