package com.sweetcat.userinformation.application.event.subscribe;

import com.sweetcat.commons.domainevent.usercomment.UserPublishedCommentCommentEvent;
import com.sweetcat.userinformation.application.command.AddCommentReplyCommand;
import com.sweetcat.userinformation.application.service.InformationApplicationService;
import com.sweetcat.userinformation.domain.information.entity.Information;
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
 * @date: 2021-11-2021/11/13-19:27
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "sweetcat_user_comment",
        selectorType = SelectorType.TAG,
        selectorExpression = "add_comment_commoent",
        consumerGroup = "CG_sweetcat_user_information_comment_comment",
        consumeMode = ConsumeMode.ORDERLY
)
public class UserPublishedCommentCommentEventSubscriber implements RocketMQListener<UserPublishedCommentCommentEvent> {
    private InformationApplicationService informationApplicationService;

    @Autowired
    public void setInformationApplicationService(InformationApplicationService informationApplicationService) {
        this.informationApplicationService = informationApplicationService;
    }

    @Override
    public void onMessage(UserPublishedCommentCommentEvent event) {
        System.out.println("sweetcat-user-information 接收到领域事件 UserPublishedCommentCommentEvent 时间为：" + Instant.now().toEpochMilli());
        AddCommentReplyCommand addCommentReplyCommand = new AddCommentReplyCommand();
        addCommentReplyCommand.setCommodityId(event.getCommodityId());
        addCommentReplyCommand.setInformationId(event.getCommentId());
        addCommentReplyCommand.setPublisherId(event.getPublisherId());
        addCommentReplyCommand.setReceiverId(event.getReceiverId());
        addCommentReplyCommand.setContent(event.getContent());
        addCommentReplyCommand.setContentPics(event.getContentPics());
        addCommentReplyCommand.setCreateTime(event.getCreateTime());
        addCommentReplyCommand.setStatus(Information.STATUS_UNREAD);
        addCommentReplyCommand.setStoreId(event.getStoreId());
        addCommentReplyCommand.setTargetUrl("");
        informationApplicationService.addOneCommentReply(addCommentReplyCommand);
    }
}
