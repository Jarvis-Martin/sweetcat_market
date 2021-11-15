package com.sweetcat.api.rpcdto.userinfo;

import lombok.*;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/4-21:31
 * @Version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
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
