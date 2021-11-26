package com.sweetcat.commons.domainevent.usercomment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/24-15:44
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class UserPublishedCommentCommentEvent {
    /**
     * 事件唯一标识
     */
    private Long domainEventId;
    /**
     * 事件发送事件
     */
    private Long occurOne;

    /**
     * 评论的id
     */
    private Long commentId;
    /**
     * 发布人
     */
    private Long publisherId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论配图
     */
    private List<String> contentPics;
    /**
     * 评论事件
     */
    private Long createTime;
    /**
     * 被评论的那条评论的id
     */
    private Long parentCommentId;

    private Long commodityId;
    private Long storeId;
    private Long receiverId;

    public UserPublishedCommentCommentEvent(Long domainEventId) {
        this.domainEventId = domainEventId;
    }
}
