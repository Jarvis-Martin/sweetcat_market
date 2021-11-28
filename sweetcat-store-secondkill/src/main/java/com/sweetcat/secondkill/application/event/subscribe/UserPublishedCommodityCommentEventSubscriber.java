package com.sweetcat.secondkill.application.event.subscribe;

import com.sweetcat.commons.domainevent.usercomment.UserPublishedSKCommodityCommentEvent;
import com.sweetcat.secondkill.application.command.AddSKCommodityCommentCommand;
import com.sweetcat.secondkill.application.service.SKCommodityCommentApplicationoService;
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
 * @date: 2021-11-2021/11/24-15:58
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "sweetcat_user_comment",
        selectorType = SelectorType.TAG,
        selectorExpression = "add_comment_skCommodity",
        consumerGroup = "CG_store_commodity_comment_add",
        consumeMode = ConsumeMode.ORDERLY
)
public class UserPublishedCommodityCommentEventSubscriber implements RocketMQListener<UserPublishedSKCommodityCommentEvent> {
    private SKCommodityCommentApplicationoService commentApplicationoService;

    @Autowired
    public void setCommentApplicationoService(SKCommodityCommentApplicationoService commentApplicationoService) {
        this.commentApplicationoService = commentApplicationoService;
    }

    @Override
    public void onMessage(UserPublishedSKCommodityCommentEvent event) {
        System.out.println("sweetcat-store-commodity 接收触发领域事件 UserPublishedCommodityCommentEvent 时间为：" + Instant.now().toEpochMilli());
        AddSKCommodityCommentCommand command = new AddSKCommodityCommentCommand();
        // 填充 addCommodityCommentCommand
        inflateAddCommodityCommentCommand(event, command);
        // 添加商品评论
        commentApplicationoService.addOne(command);
    }

    /**
     * 填充 addCommodityCommentCommand
     *
     * @param event
     * @param command
     */
    private void inflateAddCommodityCommentCommand(UserPublishedSKCommodityCommentEvent event, AddSKCommodityCommentCommand command) {
        command.setCommentId(event.getCommentId());
        command.setUserId(event.getPublisherId());
        command.setCommodityId(event.getCommodityId());
        command.setContent(event.getContent());
        command.setContentPics(event.getContentPics());
        command.setStars(event.getStars());
        command.setCreateTime(event.getCreateTime());
    }
}
