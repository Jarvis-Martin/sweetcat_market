package com.sweetcat.user_relationship.infrastructure.factory;

import com.sweetcat.user_relationship.domain.followrelationship.entity.FollowRelationShip;
import com.sweetcat.user_relationship.domain.followrelationship.entity.User;
import com.sweetcat.user_relationship.infrastructure.po.FollowRelationshipPO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-20:38
 * @Version: 1.0
 */
@Component
public class FollowRelationShipFactory {

    public FollowRelationShip create(FollowRelationshipPO followRelationshipPO) {
        // user
        User user = new User(
                followRelationshipPO.getUserId(),
                followRelationshipPO.getUserAvatar(),
                followRelationshipPO.getUserNickName(),
                followRelationshipPO.getUserPersonalizedSignature(),
                followRelationshipPO.getFansNumber()
        );
        // targetUser
        User targetUser = new User(
                followRelationshipPO.getTargetUserId(),
                followRelationshipPO.getTargetAvatar(),
                followRelationshipPO.getTargetNickName(),
                followRelationshipPO.getTargetPersonalizedSignature(),
                followRelationshipPO.getTargetFansNumber()
        );
        return new FollowRelationShip(
                user,
                targetUser,
                followRelationshipPO.getIsLiked(),
                followRelationshipPO.getCreateTime()
        );
    }

}
