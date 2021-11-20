package com.sweetcat.credit.application.event.subscribe;

import com.sweetcat.commons.domainevent.creditcenter.CreditRedeemedCommodityEvent;
import com.sweetcat.credit.application.command.AddRedeemLogCommand;
import com.sweetcat.credit.application.service.RedeemLogApplicationService;
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
 * @date: 2021-11-2021/11/19-12:44
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "credit_center_topic",
        selectorType = SelectorType.TAG,
        selectorExpression = "credit_redeem_coupon",
        consumerGroup = "CG_sweetcat_app_credit_redeem",
        consumeMode = ConsumeMode.ORDERLY
)
public class CreditRedeemedCommodityEventSubscriber implements RocketMQListener<CreditRedeemedCommodityEvent> {
    private RedeemLogApplicationService redeemLogApplicationService;

    @Autowired
    public void setRedeemLogApplicationService(RedeemLogApplicationService redeemLogApplicationService) {
        this.redeemLogApplicationService = redeemLogApplicationService;
    }

    @Override
    public void onMessage(CreditRedeemedCommodityEvent event) {
        System.out.println("sweetcat-app-credit 接收到领域事件 CreditRedeemedCommodityEvent 时间为：" + Instant.now().toEpochMilli());
        AddRedeemLogCommand command = new AddRedeemLogCommand(event.getRedeemUserId());
        command.setCommodityId(event.getCommodityId());
        command.setCostCreditNumber(event.getCostCreditNumber());
        command.setCreateTime(event.getCreateTime());
        redeemLogApplicationService.addOne(command);
    }
}
