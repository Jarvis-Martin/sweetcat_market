package com.sweetcat.user_info.infrastructure.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * t_user_info
 * @author 
 */
@Data
@NoArgsConstructor
public class UserPO implements Serializable {
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

    private static final long serialVersionUID = 1L;
}