package com.sweetcat.user_info.interfaces.facade.dto;

import lombok.Data;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-15:40
 * @Version: 1.0
 */
@Data
public class UserInfoDTO {
    /**
     * 账号
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatarPath;

    /**
     * 性别,0: 女； 1: 男
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
    private Integer isReferrer;

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

}
