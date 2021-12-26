package com.sweetcat.user_relationship.application.service;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.user_relationship.application.rpc.UserInfoRpc;
import com.sweetcat.user_relationship.domain.followrelationship.entity.FollowRelationShip;
import com.sweetcat.user_relationship.domain.followrelationship.entity.User;
import com.sweetcat.user_relationship.domain.followrelationship.exception.UserLikedException;
import com.sweetcat.user_relationship.domain.followrelationship.exception.UserNotLikedException;
import com.sweetcat.user_relationship.domain.followrelationship.repository.FollowRelationShipRepository;
import com.sweetcat.user_relationship.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-15:57
 * @Version: 1.0
 */
@Service
public class FollowRelationShipApplicationService {

    /**
     * 默认 粉丝列表 limit
     */
    @Value("${default-fans-limit}")
    private Integer defaultFansLimit;
    private FollowRelationShipRepository followRelationShipRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private UserInfoRpc userInfoRpc;

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setFollowRelationShipRepository(FollowRelationShipRepository followRelationShipRepository) {
        this.followRelationShipRepository = followRelationShipRepository;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    /**
     * 获得 互关关系列表：粉丝列表（分页数据）
     *
     * @param userId userId
     * @param page   page
     * @param limit  limit
     * @return 互关关系列表：粉丝列表、关注列表（分页数据）
     */
    @Transactional
    public List<FollowRelationShip> getFansPage(Long userId, Integer page, Integer limit) {
        // 检查 userId
        verifyIdFormatService.verifyId(userId);
        // page limit 参数检查 + 调整
        page = page < 0 ? 0 : page * limit;
        limit = limit < 0 ? this.defaultFansLimit : limit;
        // 返回分页
        return followRelationShipRepository.getFansPage(userId, page, limit);
    }

    /**
     * 获得 互关关系列表：关注列表（分页数据）
     *
     * @param userId userId
     * @param page   page
     * @param limit  limit
     * @return 互关关系列表：粉丝列表、关注列表（分页数据）
     */
    @Transactional
    public List<FollowRelationShip> getSubscriberPage(Long userId, Integer page, Integer limit) {
        // 检查 userId
        verifyIdFormatService.verifyId(userId);
        // page limit 参数检查 + 调整
        page = page < 0 ? 0 : page * limit;
        limit = limit < 0 ? this.defaultFansLimit : limit;
        return followRelationShipRepository.getSubscriberPage(userId, page, limit);
    }

    /**
     * 发起关注
     *
     * @param userId       userId
     * @param targetUserId targetUserId
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void like(Long userId, Long targetUserId) {
        // 检查 id 格式
        verifyIdFormatService.verifyId(userId, targetUserId);
        // 根据 uesrId 和 targetUserId 查找记录
        FollowRelationShip followRelationShip = followRelationShipRepository.find(userId, targetUserId);
        // 记录已存在，说明已经关注过了，不做处理
        if (followRelationShip != null) {
            throw new UserLikedException("您已经关注过他了");
        }
        // rpc 获得我自己
        UserInfoRpcDTO myUserInfoRPCDTO = userInfoRpc.getUserInfo(userId);

        BigInteger myFansNumber = followRelationShipRepository.getFansNumber(targetUserId);
        // 创建被关注对象
        User myUserInfo = new User(
                myUserInfoRPCDTO.getUserId(),
                myUserInfoRPCDTO.getAvatarPath(),
                myUserInfoRPCDTO.getNickname(),
                myUserInfoRPCDTO.getPersonalizedSignature(),
                myFansNumber
        );
        // rpc 获得 被关注用户信息
        UserInfoRpcDTO targetUserInfoRpcDTO = userInfoRpc.getUserInfo(targetUserId);
        // 获得被关注目标粉丝数
        BigInteger targetFansNumber = followRelationShipRepository.getFansNumber(targetUserId);
        // 创建被关注对象
        User targetUser = new User(
                targetUserInfoRpcDTO.getUserId(),
                targetUserInfoRpcDTO.getAvatarPath(),
                targetUserInfoRpcDTO.getNickname(),
                targetUserInfoRpcDTO.getPersonalizedSignature(),
                targetFansNumber
        );
        // followRelationShip创建记录对象
        followRelationShip = new FollowRelationShip(myUserInfo);
        // 发起关注
        followRelationShip.like(targetUser);
        // 添加进db
        followRelationShipRepository.add(followRelationShip);
    }

    /**
     * 取消关注
     *
     * @param userId userId
     * @param targetUserId targetUserId
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void disLike(Long userId, Long targetUserId) {
        // 检查id
        verifyIdFormatService.verifyId(userId, targetUserId);
        // 查找记录
        FollowRelationShip followRelationShip = followRelationShipRepository.find(userId, targetUserId);
        // userId 未关注 targetUserId，故 do nothing
        if (followRelationShip == null) {
            throw new UserNotLikedException("您还未关注它喔！");
        }
        // 取关
        followRelationShip.disLike(new User(targetUserId));
        followRelationShipRepository.remove(followRelationShip);
    }
}
