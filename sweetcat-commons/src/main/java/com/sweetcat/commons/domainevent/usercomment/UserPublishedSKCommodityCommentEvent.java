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
public class UserPublishedSKCommodityCommentEvent {
    /**
     * 评论id
     */
    private Long commentId;
    /**
     * 领域事件唯一标识
     */
    private Long domainEventId;

    /**
     * 发生事件
     */
    private Long occurOn;

    /**
     * 发布分id
     */
    private Long publisherId;
    /**
     * 商品id
     */
    private Long commodityId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论配图
     */
    private List<String> contentPics;
    /**
     * 评论星级
     */
    private Integer stars;
    /**
     * 评论创建时间
     */
    private Long createTime;

    public UserPublishedSKCommodityCommentEvent(Long domainEventId) {
        this.domainEventId = domainEventId;
    }
}
