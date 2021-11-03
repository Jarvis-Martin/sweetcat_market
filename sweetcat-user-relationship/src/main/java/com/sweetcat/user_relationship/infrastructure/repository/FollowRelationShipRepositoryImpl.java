package com.sweetcat.user_relationship.infrastructure.repository;

import com.sweetcat.user_relationship.domain.follow_relationship.entity.FollowRelationShip;
import com.sweetcat.user_relationship.domain.follow_relationship.repository.FollowRelationShipRepository;
import com.sweetcat.user_relationship.infrastructure.dao.FollowRelationShipMapper;
import com.sweetcat.user_relationship.infrastructure.factory.FollowRelationShipFactory;
import com.sweetcat.user_relationship.infrastructure.po.FollowRelationshipPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
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
    public List<FollowRelationShip> getSubscriberPage(Long userId, Integer page, Integer limit) {
        return null;
    }

    @Override
    public List<FollowRelationShip> getFansPage(Long userId, Integer page, Integer limit) {
        List<FollowRelationshipPO> followRelationshipPOPage = followRelationShipMapper.getFansPage(userId, page, limit);
        return followRelationshipPOPage.stream().collect(
                ArrayList<FollowRelationShip>::new,
                (con, followRelationshipPO) -> {
                    // 获得目标用户粉丝数
                    BigInteger targetUserFansNumber = followRelationShipMapper.getFansNumber(followRelationshipPO.getTargetUserId());
                    // 给 followRelationshipPO 设置目标粉丝数
                    followRelationshipPO.setFansNumber(targetUserFansNumber);
                    con.add(this.followRelationShipFactory.create(followRelationshipPO));
                },
                ArrayList::addAll
        );
    }

}
