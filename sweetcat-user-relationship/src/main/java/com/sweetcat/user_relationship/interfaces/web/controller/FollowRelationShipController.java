package com.sweetcat.user_relationship.interfaces.web.controller;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.user_relationship.domain.followrelationship.entity.FollowRelationShip;
import com.sweetcat.user_relationship.interfaces.facade.FollowRelationShipFacade;
import com.sweetcat.user_relationship.interfaces.facade.assembler.FollowRelationShipAssembler;
import com.sweetcat.user_relationship.interfaces.facade.restdto.FollowRelationShipDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-15:59
 * @Version: 1.0
 */
@RestController
@RequestMapping("/relationship")
public class FollowRelationShipController {
    private FollowRelationShipFacade followRelationShipFacade;
    private FollowRelationShipAssembler followRelationShipAssembler;

    @Autowired
    public void setFollowRelationShipAssembler(FollowRelationShipAssembler followRelationShipAssembler) {
        this.followRelationShipAssembler = followRelationShipAssembler;
    }

    @Autowired
    public void setFollowRelationShipFacade(FollowRelationShipFacade followRelationShipFacade) {
        this.followRelationShipFacade = followRelationShipFacade;
    }

    @GetMapping("/user/{user_id}/fans_list")
    public ResponseDTO getFansPage(@PathVariable("user_id") Long userId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<FollowRelationShip> fansPage = followRelationShipFacade.getFansPage(userId, page, limit);
        List<FollowRelationShipDTO> fansDTOList = fansPage.stream().collect(
                ArrayList<FollowRelationShipDTO>::new,
                (con, followRelationShip) -> con.add(followRelationShipAssembler.converterToFollowRelationShipFansDTO(followRelationShip)),
                ArrayList::addAll
        );
        Map<String, Object> fansList = new HashMap<>(12);
        fansList.put("data_list", fansDTOList);
        String tip = "一切OK！";
        return response(tip, fansList);
    }

    @GetMapping("/user/{user_id}/subscriber_list")
    public ResponseDTO getSubscriberPage(@PathVariable("user_id") Long userId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<FollowRelationShip> subscriberPage = followRelationShipFacade.getSubscriberPage(userId, page, limit);
        List<FollowRelationShipDTO> subscriberDTOList = subscriberPage.stream().collect(
                ArrayList<FollowRelationShipDTO>::new,
                (con, followRelationShip) -> con.add(followRelationShipAssembler.converterToFollowRelationShipSubscriberDTO(followRelationShip)),
                ArrayList::addAll
        );
        Map<String, Object> subscriberList = new HashMap<>(12);
        subscriberList.put("data_list", subscriberDTOList);
        String tip = "一切OK！";
        return response(tip, subscriberList);
    }

    @GetMapping("/user/{user_id}/like/{target_user_id}")
    public ResponseDTO like(@PathVariable("user_id") Long userId, @PathVariable("target_user_id") Long targetUserId) {
        followRelationShipFacade.like(userId, targetUserId);

        return response("关注成功！", "{}");
    }

    @GetMapping("/user/{user_id}/dislike/{target_user_id}")
    public ResponseDTO disLike(@PathVariable("user_id") Long userId, @PathVariable("target_user_id") Long targetUserId) {
        followRelationShipFacade.dislike(userId, targetUserId);

        return response("取消关注成功！", "{}");
    }

    /**
     * 通用的放回 ResponseDTO
     *
     * @param tip  用户提示信息
     * @param data 数据部分
     * @return ResponseDTO
     */
    private ResponseDTO response(String tip, Object data) {
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                tip,
                data
        );
    }
}
