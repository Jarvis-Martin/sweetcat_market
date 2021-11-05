package com.sweetcat.commons.rpcdto.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/4-21:31
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class UserInfoRpcDTO {
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
     * 个性签名
     */
    private String personalizedSignature;

}
