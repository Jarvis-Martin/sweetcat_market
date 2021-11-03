package com.sweetcat.user_relationship.domain.follow_relationship.repository;

import com.sweetcat.user_relationship.domain.follow_relationship.entity.FollowRelationShip;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-20:23
 * @Version: 1.0
 */
public interface FollowRelationShipRepository {

    /**
     * 获得 互关关系列表：粉丝列表、关注列表（分页数据）
     *
     * @param userId userId
     * @param page   page
     * @param limit  limit
     * @return 互关关系列表：粉丝列表、关注列表（分页数据）
     */
    List<FollowRelationShip> getFansPage(Long userId, Integer page, Integer limit);

    /**
     * 获得 互关关系列表：粉丝列表、关注列表（分页数据）
     *
     * @param userId userId
     * @param page   page
     * @param limit  limit
     * @return 互关关系列表：粉丝列表、关注列表（分页数据）
     */
    List<FollowRelationShip> getSubscriberPage(Long userId, Integer page, Integer limit);

}
