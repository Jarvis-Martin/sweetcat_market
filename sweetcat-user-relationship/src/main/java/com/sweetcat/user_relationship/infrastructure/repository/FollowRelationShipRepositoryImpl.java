package com.sweetcat.user_relationship.infrastructure.repository;

import com.sweetcat.user_relationship.domain.followrelationship.entity.FollowRelationShip;
import com.sweetcat.user_relationship.domain.followrelationship.repository.FollowRelationShipRepository;
import com.sweetcat.user_relationship.infrastructure.dao.FollowRelationShipMapper;
import com.sweetcat.user_relationship.infrastructure.factory.FollowRelationShipFactory;
import com.sweetcat.user_relationship.infrastructure.po.FollowRelationshipPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-20:23
 * @Version: 1.0
 */
@Repository
public class FollowRelationShipRepositoryImpl implements FollowRelationShipRepository {

    private FollowRelationShipMapper followRelationShipMapper;
    private FollowRelationShipFactory followRelationShipFactory;

    @Autowired
    public void setFollowRelationShipFactory(FollowRelationShipFactory followRelationShipFactory) {
        this.followRelationShipFactory = followRelationShipFactory;
    }

    @Autowired
    public void setFollowRelationShipMapper(FollowRelationShipMapper followRelationShipMapper) {
        this.followRelationShipMapper = followRelationShipMapper;
    }

    @Override
    public void remove(FollowRelationShip followRelationShip) {
        this.followRelationShipMapper.delete(followRelationShip);
    }

    @Override
    public FollowRelationShip find(Long userId, Long targetUserId) {
        FollowRelationshipPO followRelationshipPO = followRelationShipMapper.findOne(userId, targetUserId);
        // db 中不存在 userId, targetUserId
        if (followRelationshipPO == null) {
            return null;
        }
        return followRelationShipFactory.create(followRelationshipPO);
    }

    @Override
    public List<FollowRelationShip> getFansPage(Long userId, Integer page, Integer limit) {
        List<FollowRelationshipPO> followRelationshipPOPage = followRelationShipMapper.getFansPage(userId, page, limit);
        if (followRelationshipPOPage == null || followRelationshipPOPage.isEmpty()) {
            return Collections.emptyList();
        }
        return followRelationshipPOPage.stream().collect(
                ArrayList<FollowRelationShip>::new,
                (con, followRelationshipPO) -> {
                    // 获得我的粉丝数
                    BigInteger myFansNumber = followRelationShipMapper.getFansNumber(followRelationshipPO.getTargetUserId());
                    // 设置粉丝数
                    followRelationshipPO.setFansNumber(myFansNumber);
                    // 获得目标用户粉丝数
                    BigInteger targetUserFansNumber = followRelationShipMapper.getFansNumber(followRelationshipPO.getTargetUserId());
                    // 给 followRelationshipPO 设置目标粉丝数
                    followRelationshipPO.setTargetFansNumber(targetUserFansNumber);
                    // 判断targetuserid 是否以及被关注
                    FollowRelationshipPO one = followRelationShipMapper.findOne(userId, followRelationshipPO.getTargetUserId());
                    int isLike = one == null ? 0 : 1;
                    followRelationshipPO.setIsLiked(isLike);
                    con.add(this.followRelationShipFactory.create(followRelationshipPO));
                },
                ArrayList::addAll
        );
    }

    @Override
    public List<FollowRelationShip> getSubscriberPage(Long userId, Integer page, Integer limit) {
        List<FollowRelationshipPO> followRelationshipPOPage = followRelationShipMapper.getSubscriberPage(userId, page, limit);
        if (followRelationshipPOPage == null || followRelationshipPOPage.isEmpty()) {
            return Collections.emptyList();
        }
        return followRelationshipPOPage.stream().collect(
                ArrayList<FollowRelationShip>::new,
                (con, followRelationshipPO) -> {
                    // 获得我的粉丝数
                    BigInteger myFansNumber = followRelationShipMapper.getFansNumber(followRelationshipPO.getTargetUserId());
                    // 设置粉丝数
                    followRelationshipPO.setFansNumber(myFansNumber);
                    // 获得目标用户粉丝数
                    BigInteger targetUserFansNumber = followRelationShipMapper.getFansNumber(followRelationshipPO.getTargetUserId());
                    // 给 followRelationshipPO 设置目标粉丝数
                    followRelationshipPO.setTargetFansNumber(targetUserFansNumber);
                    followRelationshipPO.setIsLiked(1);
                    con.add(this.followRelationShipFactory.create(followRelationshipPO));
                },
                ArrayList::addAll
        );
    }

    @Override
    public void add(FollowRelationShip followRelationShip) {
        followRelationShipMapper.insertOne(followRelationShip);
    }

    @Override
    public BigInteger getFansNumber(Long userId) {
        return followRelationShipMapper.getFansNumber(userId);
    }
}
