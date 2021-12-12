package com.sweetcat.userorder.application.event.publish;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/5-20:45
 * @Version: 1.0
 */
@Component
public class DomainEventPublisher {
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    public void setRocketMQTemplate(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void syncSend(String destination, Object payload) {
        rocketMQTemplate.syncSend(destination,payload);
    }

    public void convert(String destination, Object payload) {
        rocketMQTemplate.convertAndSend(destination, payload);
    }

}
