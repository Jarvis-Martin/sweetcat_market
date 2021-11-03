package com.sweetcat.user_relationship.infrastructure.po;

import java.io.Serializable;
import lombok.Data;

/**
 * t_user_fans
 * @author 
 */
@Data
public class FollowRelationshipPOKey implements Serializable {
    /**
     * 发起关注的人的 id
     */
    private Long userId;

    /**
     * 被关注id
     */
    private Long targetUserId;

    private static final long serialVersionUID = 1L;
}