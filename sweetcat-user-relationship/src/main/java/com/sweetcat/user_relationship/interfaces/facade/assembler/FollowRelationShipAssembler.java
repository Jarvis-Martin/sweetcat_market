package com.sweetcat.user_relationship.interfaces.facade.assembler;

import com.sweetcat.user_relationship.domain.follow_relationship.entity.FollowRelationShip;
import com.sweetcat.user_relationship.interfaces.facade.dto.FollowRelationShipDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-15:59
 * @Version: 1.0
 */
@Component
public class FollowRelationShipAssembler {
    public FollowRelationShipDTO converterToFollowRelationShipDTO(FollowRelationShip followRelationShip) {
        FollowRelationShipDTO followRelationShipDTO = new FollowRelationShipDTO();
        followRelationShipDTO.setUserId(followRelationShip.getFollowRelationShipKey().getUserId());
        followRelationShipDTO.setTargetUserId(followRelationShip.getFollowRelationShipKey().getTargetUserId());
        followRelationShipDTO.setTargetAvatar(followRelationShip.getTargetAvatar());
        followRelationShipDTO.setTargetNickname(followRelationShip.getTargetNickname());
        followRelationShipDTO.setTargetPersonalizedSignature(followRelationShip.getTargetPersonalizedSignature());
        followRelationShipDTO.setFansNumber(followRelationShip.getFansNumber());
        followRelationShipDTO.setCreateTime(followRelationShip.getCreateTime());
        return followRelationShipDTO;
    }
}
