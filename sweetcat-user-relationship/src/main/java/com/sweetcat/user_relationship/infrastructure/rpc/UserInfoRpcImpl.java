package com.sweetcat.user_relationship.infrastructure.rpc;

import com.sweetcat.api.client.UserInfoClient;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.user_relationship.application.rpc.UserInfoRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/4-21:07
 * @Version: 1.0
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
