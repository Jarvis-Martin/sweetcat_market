package com.sweetcat.storecommodity.application.event.subscribe;

import com.sweetcat.commons.domainevent.usercomment.UserDeletedCommodityCommentEvent;
import com.sweetcat.storecommodity.application.service.CommodityCommentApplicationoService;
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
 * @date: 2021-11-2021/11/24-21:19
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "sweetcat_user_comment",
        selectorType = SelectorType.TAG,
        selectorExpression = "delete_comment_commodity",
        consumerGroup = "CG_store_commodity_comment_delete",
        consumeMode = ConsumeMode.ORDERLY
)
public class UserDeletedCommodityCommentEventSubscriber implements RocketMQListener<UserDeletedCommodityCommentEvent> {
    private CommodityCommentApplicationoService commentApplicationoService;

    @Autowired
    public void setCommentApplicationoService(CommodityCommentApplicationoService commentApplicationoService) {
        this.commentApplicationoService = commentApplicationoService;
    }

    @Override
    public void onMessage(UserDeletedCommodityCommentEvent event) {
        System.out.println("sweetcat-store-commodity 接收触发领域事件 CaptchaRequestedEvent 时间为：" + Instant.now().toEpochMilli());
        commentApplicationoService.removeOne(event.getCommentId());
    }
}
