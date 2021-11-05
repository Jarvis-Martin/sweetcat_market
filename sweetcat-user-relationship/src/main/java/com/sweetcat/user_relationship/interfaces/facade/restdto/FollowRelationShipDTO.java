package com.sweetcat.user_relationship.interfaces.facade.restdto;

import lombok.Data;

import java.math.BigInteger;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-15:59
 * @Version: 1.0
 */
@Data
public class FollowRelationShipDTO {
    /**
     * 主键：userId 和 targetUserId
     */
    private Long targetUserId;
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
}
