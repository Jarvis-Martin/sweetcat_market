package com.sweetcat.user_relationship.infrastructure.dao;

import com.sweetcat.user_relationship.domain.followrelationship.entity.FollowRelationShip;
import com.sweetcat.user_relationship.infrastructure.po.FollowRelationshipPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author 86152
 */
@Mapper
public interface FollowRelationShipMapper {

    /**
     * 获得 互关关系列表：粉丝列表、关注列表（分页数据）
     *
     * @param userId userId
     * @param page   page
     * @param limit  limit
     * @return 互关关系列表：粉丝列表、关注列表（分页数据）
     */
    List<FollowRelationshipPO> getFansPage(@Param("userId") Long userId, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 获得 粉丝数
     *
     * @param userId userId
     * @return 粉丝数
     */
    BigInteger getFansNumber(@Param("userId") Long userId);

    /**
     * 获得 关注数
     *
     * @param userId userId
     * @return 关注数
     */
    BigInteger getSubscribeNumber(@Param("userId") Long userId);

    /**
     * 获得 我关注的人的分页数据
     *
     * @param userId userId
     * @param page   page
     * @param limit  limit
     * @return 关注数
     */
    List<FollowRelationshipPO> getSubscriberPage(@Param("userId") Long userId, @Param("page") Integer page, @Param("limit") Integer limit);

    void insertOne(FollowRelationShip followRelationShip);

    FollowRelationshipPO findOne(@Param("userId") Long userId, @Param("targetUserId") Long targetUserId);

    void delete(FollowRelationShip followRelationShip);
}