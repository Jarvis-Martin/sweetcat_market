package com.sweetcat.commons.domainevent.usercomment;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/24-21:16
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class UserDeletedCommodityCommentEvent {
    /**
     * 领域事件唯一标识
     */
    private Long domainEventId;
    /**
     * 领域事件触发时间
     */
    private Long occurOn;
    /**
     * 发起删除操作的 userid
     */
    private Long userId;
    /**
     * 被删除的评论id
     */
    private Long commentId;

    public UserDeletedCommodityCommentEvent(Long domainEventId) {
        this.domainEventId = domainEventId;
    }
}
