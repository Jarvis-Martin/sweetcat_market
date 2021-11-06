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
    public FollowRelationShipDTO converterToFollowRelationShipFansDTO(FollowRelationShip followRelationShip) {
        FollowRelationShipDTO followRelationShipDTO = new FollowRelationShipDTO();
        followRelationShipDTO.setUserId(followRelationShip.getUser().getUserId().toString());
        followRelationShipDTO.setAvatar(followRelationShip.getUser().getAvatar());
        followRelationShipDTO.setNickName(followRelationShip.getUser().getNickName());
        followRelationShipDTO.setPersonalizedSignature(followRelationShip.getUser().getPersonalizedSignature());
        followRelationShipDTO.setFansNumber(followRelationShip.getUser().getFansNumber());
        followRelationShipDTO.setIsLiked(followRelationShip.getIsLiked());
        followRelationShipDTO.setCreateTime(followRelationShip.getCreateTime());
        return followRelationShipDTO;
    }
    public FollowRelationShipDTO converterToFollowRelationShipSubscriberDTO(FollowRelationShip followRelationShip) {
        FollowRelationShipDTO followRelationShipDTO = new FollowRelationShipDTO();
        followRelationShipDTO.setUserId(followRelationShip.getTargetUser().getUserId().toString());
        followRelationShipDTO.setAvatar(followRelationShip.getTargetUser().getAvatar());
        followRelationShipDTO.setNickName(followRelationShip.getTargetUser().getNickName());
        followRelationShipDTO.setPersonalizedSignature(followRelationShip.getTargetUser().getPersonalizedSignature());
        followRelationShipDTO.setFansNumber(followRelationShip.getTargetUser().getFansNumber());
        followRelationShipDTO.setIsLiked(followRelationShip.getIsLiked());
        followRelationShipDTO.setCreateTime(followRelationShip.getCreateTime());
        return followRelationShipDTO;
    }
}
