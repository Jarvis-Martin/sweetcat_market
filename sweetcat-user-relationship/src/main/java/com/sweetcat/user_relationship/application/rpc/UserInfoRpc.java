package com.sweetcat.user_relationship.application.rpc;

import com.sweetcat.commons.rpcdto.userinfo.UserInfoRpcDTO;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/5-10:00
 * @Version: 1.0
 */
public interface UserInfoRpc {
    /**
     * 获得用户详情
     *
     * @param userId userId
     * @return 用户详情
     */
    public UserInfoRpcDTO getUserInfo(Long userId);
}
