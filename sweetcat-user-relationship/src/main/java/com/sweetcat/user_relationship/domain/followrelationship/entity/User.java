package com.sweetcat.user_relationship.domain.followrelationship.entity;

import com.sweetcat.user_relationship.domain.followrelationship.exception.UserPropertyIlleagalException;
import lombok.Getter;

import java.math.BigInteger;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/4-19:10
 * @Version: 1.0
 */
@Getter
public class User {
    /**
     * 发起关注的人的 id
     */
    private Long userId;

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

    public User(Long userId, String avatar, String nickName, String personalizedSignature, BigInteger fansNumber) {
        this.setUserId(userId);
        this.setAvatar(avatar);
        this.setNickName(nickName);
        this.setPersonalizedSignature(personalizedSignature);
        this.setFansNumber(fansNumber);
    }

    private void setUserId(Long userId) {
        if (userId == null || userId < 0) {
            throw new UserPropertyIlleagalException(
                    "创建事件格式错误。"
            );
        }
        this.userId = userId;
    }

    private void setAvatar(String avatar) {
        if (userId == null || userId < 0) {
            throw new UserPropertyIlleagalException(
                    "创建事件格式错误。"
            );
        }
        this.avatar = avatar;
    }

    private void setNickName(String nickName) {
        if (nickName == null || nickName.length() < 0) {
            throw new UserPropertyIlleagalException(
                    "创建事件格式错误。"
            );
        }
        this.nickName = nickName;
    }

    private void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    private void setFansNumber(BigInteger fansNumber) {
        this.fansNumber = fansNumber == null || fansNumber.compareTo(BigInteger.ZERO) < 0 ? BigInteger.ZERO : fansNumber;
    }

    public User(Long userId) {
        this.setUserId(userId);
    }
}
