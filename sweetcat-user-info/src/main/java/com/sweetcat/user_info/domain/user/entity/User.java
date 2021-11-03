package com.sweetcat.user_info.domain.user.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import java.util.regex.Pattern;

import com.sweetcat.user_info.domain.user.exception.UserPropertyIlleagalException;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * t_user_info
 *
 * @author
 */
@Getter
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 默认个性签名
     */
    public static String DEFAULTPERSONALIZEDSIGNATURE = "该用户什么也没留下喔";
    /**
     * 默认头像
     */
    public static String DEFAULTAVATAR = "http:localhost:80/img/icon_default_avatar.png";
    /**
     * 普通 vip
     */
    public static Integer NORMALVIP = 0;
    /**
     * 非鉴赏官身份
     */
    public static Integer NOTREFERRER = 0;
    /**
     * 鉴赏官身份
     */
    public static Integer REFERRER = 1;

    /**
     * 账号
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatarPath;

    /**
     * 性别,
     * 0: 女； 1: 男
     */
    private Integer gender;

    /**
     * 生日
     */
    private Long birthday;

    /**
     * vip 级别
     */
    private Integer vipLevel;

    /**
     * 推荐官？0: 不是， 1：是
     */
    private Integer referrer;

    /**
     * 个性签名
     */
    private String personalizedSignature;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    public User(Long userId) {
        this.userId = userId;
    }

    public User(Long userId,
                String nickname,
                String password,
                String avatarPath,
                Integer gender,
                Long birthday,
                Integer vipLevel,
                Integer referrer,
                String personalizedSignature,
                String phone,
                Long createTime,
                Long updateTime) {
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.avatarPath = avatarPath;
        this.gender = gender;
        this.birthday = birthday;
        this.vipLevel = vipLevel;
        this.referrer = referrer;
        this.personalizedSignature = personalizedSignature;
        this.phone = phone;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public void changeNickname(String nickname) {
        this.setNickname(nickname);
    }

    public void changePassword(String password) {
        this.setPassword(password);
    }

    public void changeAvatarPath(String avatarPath) {
        this.setAvatarPath(avatarPath);
    }

    public void changeGender(Integer gender) {
        this.setGender(gender);
    }

    public void changeBirthday(Long birthday) {
        this.setBirthday(birthday);
    }

    public void upgradeVipLevel() {
        this.setVipLevel(this.vipLevel + 1);
    }

    public void demoteVipLevel() {
        this.setVipLevel(this.vipLevel - 1);
    }

    public void becomeReferrer() {
        this.setVipLevel(1);
    }

    public void changePersonalizedSignature(String personalizedSignature) {
        this.setPersonalizedSignature(personalizedSignature);
    }

    public void changeUpdateTime(Long updateTime) {
        this.setUpdateTime(updateTime);
    }

    public void changePhone(String phone) {
        this.setPhone(phone);
    }

    private void setNickname(String nickname) {
        // 如果传入的 nickname 为 null，则设置默认的昵称： 蓝胖子xxx
        nickname = nickname == null ? "蓝胖子" + UUID.randomUUID().toString().substring(24) : nickname;
        this.nickname = nickname;
    }

    private void setPassword(String password) {
        if (password == null || password.length() <= 0) {
            throw new UserPropertyIlleagalException("密码不能空");
        }
        this.password = password;
    }

    private void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath == null || avatarPath.length() <= 0
                ? User.DEFAULTAVATAR : avatarPath;
    }

    private void setGender(Integer gender) {
        if (gender == null || gender < 0) {
            throw new UserPropertyIlleagalException("性别不正确");
        }
        this.gender = gender;
    }

    private void setBirthday(Long birthday) {
        this.birthday = birthday == null || birthday < 0 || birthday > Instant.now().toEpochMilli()
                ? 0 : birthday;
    }

    private void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel == null || vipLevel < 0 ? 0 : vipLevel;
    }

    private void setReferrer(Integer referrer) {
        this.referrer = referrer == null || referrer < 0 || referrer > 1 ? 0 : 1;
    }

    private void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature == null || personalizedSignature.length() <= 0
                ? User.DEFAULTPERSONALIZEDSIGNATURE : personalizedSignature;
    }

    private void setPhone(String phone) {
        boolean phoneMatches = Pattern.matches("^1[3-9]\\d{9}$", phone);
        if (!phoneMatches) {
            throw new UserPropertyIlleagalException("手机号格式错误");
        }
        this.phone = phone;
    }

    private void setCreateTime(Long createTime) {
        long milli = Instant.now().toEpochMilli();
        this.createTime = createTime < 0 || createTime > milli ? 0 : milli;
    }

    private void setUpdateTime(Long updateTime) {
        long milli = Instant.now().toEpochMilli();
        this.updateTime = updateTime < 0 || updateTime > milli ? 0 : milli;
    }

    private void register(String nickname, String password, Integer gender, Long birthday, String phone) {
        this.setNickname(nickname);
        this.setPassword(password);
        this.setAvatarPath(null);
        this.setGender(gender);
        this.setBirthday(birthday);
        this.setVipLevel(0);
        this.setReferrer(0);
        this.setPersonalizedSignature(null);
        this.setPhone(phone);
        long milli = Instant.now().toEpochMilli();
        this.setCreateTime(milli);
        this.setUpdateTime(milli);
    }

}