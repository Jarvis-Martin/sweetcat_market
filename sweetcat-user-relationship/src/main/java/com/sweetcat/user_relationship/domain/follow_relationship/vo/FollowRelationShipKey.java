package com.sweetcat.user_relationship.domain.follow_relationship.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-15:53
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class FollowRelationShipKey {
    /**
     * 发起关注的人的 id
     */
    private Long userId;

    /**
     * 被关注id
     */
    private Long targetUserId;
}
