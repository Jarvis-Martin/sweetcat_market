package com.sweetcat.user_relationship.infrastructure.po;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.Data;

/**
 * t_user_fans
 *
 * @author
 */
@Data
public class FollowRelationshipPO extends FollowRelationshipPOKey implements Serializable {
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

    private static final long serialVersionUID = 1L;
}