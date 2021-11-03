package com.sweetcat.user_relationship.domain.follow_relationship.entity;

import com.sweetcat.user_relationship.domain.follow_relationship.vo.FollowRelationShipKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-15:41
 * @Version: 1.0
 */
@Getter
public class FollowRelationShip {
    /**
     * 主键：userId 和 targetUserId
     */
    private FollowRelationShipKey followRelationShipKey;
    /**
     * 目标头像
     */
    private String targetAvatar;

    /**
     * 昵称
     */
    private String targetNickname;

    /**
     * 个性签名
     */
    private String targetPersonalizedSignature;

    /**
     * 粉丝数
     */
    private BigInteger fansNumber;

    /**
     * 关注时间
     */
    private Long createTime;

    public FollowRelationShip(FollowRelationShipKey followRelationShipKey) {
        this.followRelationShipKey = followRelationShipKey;
    }

    public FollowRelationShip(FollowRelationShipKey followRelationShipKey, String targetAvatar,
                              String targetNickname, String targetPersonalizedSignature,
                              BigInteger fansNumber, Long createTime) {
        this.followRelationShipKey = followRelationShipKey;
        this.targetAvatar = targetAvatar;
        this.targetNickname = targetNickname;
        this.targetPersonalizedSignature = targetPersonalizedSignature;
        this.fansNumber = fansNumber;
        this.createTime = createTime;
    }
}
