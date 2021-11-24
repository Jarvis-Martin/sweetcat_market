package com.sweetcat.storecommodity.application.event.subscribe;

import com.sweetcat.commons.domainevent.usercomment.UserPublishedCommodityCommentEvent;
import com.sweetcat.storecommodity.application.command.AddCommodityCommentCommand;
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
 * @date: 2021-11-2021/11/24-15:58
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "sweetcat_user_comment",
        selectorType = SelectorType.TAG,
        selectorExpression = "add_comment_commodity",
        consumerGroup = "CG_store_commodity_comment_add",
        consumeMode = ConsumeMode.ORDERLY
)
public class UserPublishedCommodityCommentEventSubscriber implements RocketMQListener<UserPublishedCommodityCommentEvent> {
    private CommodityCommentApplicationoService commentApplicationoService;

    @Autowired
    public void setCommentApplicationoService(CommodityCommentApplicationoService commentApplicationoService) {
        this.commentApplicationoService = commentApplicationoService;
    }

    @Override
    public void onMessage(UserPublishedCommodityCommentEvent event) {
        System.out.println("sweetcat-store-commodity 接收触发领域事件 UserPublishedCommodityCommentEvent 时间为：" + Instant.now().toEpochMilli());
        AddCommodityCommentCommand command = new AddCommodityCommentCommand();
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
    private void inflateAddCommodityCommentCommand(UserPublishedCommodityCommentEvent event, AddCommodityCommentCommand command) {
        command.setCommentId(event.getCommentId());
        command.setUserId(event.getPublisherId());
        command.setCommodityId(event.getCommodityId());
        command.setContent(event.getContent());
        command.setContentPics(event.getContentPics());
        command.setStars(event.getStars());
        command.setCreateTime(event.getCreateTime());
    }
}
