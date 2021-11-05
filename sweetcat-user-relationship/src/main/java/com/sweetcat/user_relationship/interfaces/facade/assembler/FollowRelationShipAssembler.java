package com.sweetcat.user_relationship.interfaces.facade.assembler;

import com.sweetcat.user_relationship.domain.followrelationship.entity.FollowRelationShip;
import com.sweetcat.user_relationship.interfaces.facade.restdto.FollowRelationShipDTO;
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
        followRelationShipDTO.setTargetUserId(followRelationShip.getUserId());
        followRelationShipDTO.setTargetAvatar(followRelationShip.getTargetUser().getAvatar());
        followRelationShipDTO.setTargetNickname(followRelationShip.getTargetUser().getNickName());
        followRelationShipDTO.setTargetPersonalizedSignature(followRelationShip.getTargetUser().getPersonalizedSignature());
        followRelationShipDTO.setFansNumber(followRelationShip.getTargetUser().getFansNumber());
        followRelationShipDTO.setCreateTime(followRelationShip.getCreateTime());
        return followRelationShipDTO;
    }
}
