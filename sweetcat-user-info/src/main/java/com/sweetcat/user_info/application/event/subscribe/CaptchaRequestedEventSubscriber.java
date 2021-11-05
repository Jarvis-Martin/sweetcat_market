package com.sweetcat.user_info.application.event.subscribe;

import com.aliyuncs.exceptions.ClientException;
import com.sweetcat.user_info.domain.user.event.CaptchaRequestedEvent;
import com.sweetcat.user_info.infrastructure.service.sms_service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/5-20:19
 * @Version: 1.0
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "buyer_topic",
        consumerGroup = "sweetcat-user-info",
        consumeMode = ConsumeMode.ORDERLY
)
public class CaptchaRequestedEventSubscriber implements RocketMQListener<CaptchaRequestedEvent> {
    private SmsService smsService;


    @Autowired
    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public void onMessage(CaptchaRequestedEvent captchaRequestedEvent) {
        String phone = captchaRequestedEvent.phone();
//        try {
//        smsService.send(phone);
        System.out.println(phone);
        System.out.println("------------------调用发送短信接口 --------------------");
//        } catch (ClientException e) {
//            log.info("邮件发送异常");
//            e.printStackTrace();
//        }
    }
}
