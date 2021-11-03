package com.sweetcat.user_relationship.infrastructure.factory;

import com.sweetcat.user_relationship.domain.follow_relationship.entity.FollowRelationShip;
import com.sweetcat.user_relationship.domain.follow_relationship.vo.FollowRelationShipKey;
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
        FollowRelationShipKey followRelationShipKey = new FollowRelationShipKey(
                followRelationshipPO.getUserId(),
                followRelationshipPO.getTargetUserId()
        );
        return new FollowRelationShip(
                followRelationShipKey,
                followRelationshipPO.getTargetAvatar(),
                followRelationshipPO.getTargetNickname(),
                followRelationshipPO.getTargetPersonalizedSignature(),
                followRelationshipPO.getFansNumber(),
                followRelationshipPO.getCreateTime()
        );
    }
}
