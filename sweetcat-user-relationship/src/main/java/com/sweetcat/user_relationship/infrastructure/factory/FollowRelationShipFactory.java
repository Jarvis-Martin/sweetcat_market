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
        User user = new User(
                followRelationshipPO.getTargetUserId(),
                followRelationshipPO.getTargetAvatar(),
                followRelationshipPO.getTargetNickname(),
                followRelationshipPO.getTargetPersonalizedSignature(),
                followRelationshipPO.getFansNumber()
        );

        return new FollowRelationShip(followRelationshipPO.getUserId(), user, followRelationshipPO.getCreateTime());
    }

}
