package com.sweetcat.user_relationship.domain.followrelationship.repository;

import com.sweetcat.user_relationship.domain.followrelationship.entity.FollowRelationShip;

import java.math.BigInteger;
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

    /**
     * 向 db 中添加一个 followRelationShip
     *
     * @param followRelationShip followRelationShip
     */
    void add(FollowRelationShip followRelationShip);

    /**
     * 获得粉丝数
     *
     * @param userId
     * @return
     */
    BigInteger getFansNumber(Long userId);

    /**
     * 根据 userId, targetUserId 查找记录
     *
     * @param userId
     * @param targetUserId
     * @return
     */
    FollowRelationShip find(Long userId, Long targetUserId);

    /**
     * 移除 followRelationShip
     *
     * @param followRelationShip followRelationShip
     */
    void remove(FollowRelationShip followRelationShip);
}
