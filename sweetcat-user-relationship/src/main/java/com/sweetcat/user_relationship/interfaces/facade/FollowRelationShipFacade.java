package com.sweetcat.user_relationship.interfaces.facade;

import com.sweetcat.user_relationship.application.service.FollowRelationShipApplicationService;
import com.sweetcat.user_relationship.domain.followrelationship.entity.FollowRelationShip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-16:00
 * @Version: 1.0
 */
@Component
public class FollowRelationShipFacade {
    private FollowRelationShipApplicationService followRelationShipApplicationService;

    @Autowired
    public void setFollowRelationShipApplicationService(FollowRelationShipApplicationService followRelationShipApplicationService) {
        this.followRelationShipApplicationService = followRelationShipApplicationService;
    }

    /**
     * 获得粉丝列表分页数据）
     * @param userId userId
     * @param page page
     * @param limit limit
     * @return 粉丝列表（分页数据）
     */
    public List<FollowRelationShip> getFansPage(Long userId, Integer page, Integer limit) {
        return followRelationShipApplicationService.getFansPage(userId, page, limit);
    }

    /**
     * 获得关注列表（分页数据）
     * @param userId userId
     * @param page page
     * @param limit limit
     * @return 关注列表（分页数据）
     */
    public List<FollowRelationShip> getSubscriberPage(Long userId, Integer page, Integer limit) {
        return followRelationShipApplicationService.getSubscriberPage(userId, page, limit);
    }

    /**
     * 关注操作
     * @param userId userId
     * @param targetUserId targetUserId
     */
    public void like(Long userId, Long targetUserId) {
        followRelationShipApplicationService.like(userId, targetUserId);
    }

    /**
     * 关注操作
     * @param userId userId
     * @param targetUserId targetUserId
     */
    public void disLike(Long userId, Long targetUserId) {
        followRelationShipApplicationService.disLike(userId, targetUserId);
    }

    /**
     * 取关
     * @param userId userId
     * @param targetUserId targetUserId
     */
    public void dislike(Long userId, Long targetUserId) {
        followRelationShipApplicationService.disLike(userId, targetUserId);
    }
}
