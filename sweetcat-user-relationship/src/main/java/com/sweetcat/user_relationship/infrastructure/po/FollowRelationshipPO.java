package com.sweetcat.user_relationship.infrastructure.po;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * t_user_fans
 *
 * @author
 */
@Getter
@AllArgsConstructor
public class FollowRelationshipPO implements Serializable {
    /**
     * 发起关注的人的 id
     */
    private Long userId;

    /**
     * 目标头像
     */
    private String userAvatar;

    /**
     * 昵称
     */
    private String userNickName;

    /**
     * 个性签名
     */
    private String userPersonalizedSignature;

    /**
     * 粉丝数
     */
    private BigInteger fansNumber;

    /**
     * 被关注id
     */
    private Long targetUserId;

    /**
     * 目标头像
     */
    private String targetAvatar;

    /**
     * 昵称
     */
    private String targetNickName;

    /**
     * 个性签名
     */
    private String targetPersonalizedSignature;

    /**
     * 粉丝数
     */
    private BigInteger targetFansNumber;

    /**
     * 是否以及关注过它: 0 未关注; 1 已关注
     */
    private Integer isLiked;

    /**
     * 关注时间
     */
    private Long createTime;

    public FollowRelationshipPO() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserPersonalizedSignature() {
        return userPersonalizedSignature;
    }

    public void setUserPersonalizedSignature(String userPersonalizedSignature) {
        this.userPersonalizedSignature = userPersonalizedSignature;
    }

    public BigInteger getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(BigInteger fansNumber) {
        this.fansNumber = fansNumber;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getTargetAvatar() {
        return targetAvatar;
    }

    public void setTargetAvatar(String targetAvatar) {
        this.targetAvatar = targetAvatar;
    }

    public String getTargetNickName() {
        return targetNickName;
    }

    public void setTargetNickName(String targetNickName) {
        this.targetNickName = targetNickName;
    }

    public String getTargetPersonalizedSignature() {
        return targetPersonalizedSignature;
    }

    public void setTargetPersonalizedSignature(String targetPersonalizedSignature) {
        this.targetPersonalizedSignature = targetPersonalizedSignature;
    }

    public BigInteger getTargetFansNumber() {
        return targetFansNumber;
    }

    public void setTargetFansNumber(BigInteger targetFansNumber) {
        this.targetFansNumber = targetFansNumber;
    }

    public Integer getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Integer isLiked) {
        this.isLiked = isLiked;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;
}