package com.sweetcat.storecommodity.infrastructure.rpc;

import com.sweetcat.api.client.UserInfoClient;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.storecommodity.application.rpc.UserInfoRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/9-19:46
 * @version: 1.0
 */
@Component
public class UserInfoRpcImpl implements UserInfoRpc {
    private UserInfoClient userInfoClient;

    @Autowired
    public void setUserInfoClient(UserInfoClient userInfoClient) {
        this.userInfoClient = userInfoClient;
    }

    /**
     * 获得用户详情
     *
     * @param userId userId
     * @return 用户详情
     */
    @Override
    public UserInfoRpcDTO getUserInfo(Long userId) {
        return this.userInfoClient.getUserInfo(userId);
    }
}