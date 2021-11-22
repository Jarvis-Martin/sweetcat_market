package com.sweetcat.usercoupon.application.rpc;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-19:36
 * @version: 1.0
 */
public interface UserInfoRpc {

    /**
     * 获得用户详情
     *
     * @param userId userId
     * @return 用户详情
     */
    UserInfoRpcDTO getUserInfo(Long userId);
}
