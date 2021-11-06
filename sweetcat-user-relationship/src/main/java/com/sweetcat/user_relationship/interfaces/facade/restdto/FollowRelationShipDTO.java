package com.sweetcat.user_relationship.interfaces.facade.restdto;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-15:59
 * @Version: 1.0
 */
public class FollowRelationShipDTO implements Serializable {
    /**
     * 主键：userId 和 tarUserId
     */
    private String userId;
    /**
     * 目标头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 个性签名
     */
    private String personalizedSignature;

    /**
     * 粉丝数
     */
    private BigInteger fansNumber;

    /**
     * 是否以及关注过它: 0 未关注; 1 已关注
     */
    private Integer isLiked;

    /**
     * 关注时间
     */
    private Long createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public BigInteger getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(BigInteger fansNumber) {
        this.fansNumber = fansNumber;
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
}
