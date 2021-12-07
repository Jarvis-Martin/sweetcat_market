package com.sweetcat.takewayorder.infrastructure.rpc;

import com.sweetcat.api.client.UserInfoClient;
import com.sweetcat.api.rpcdto.userinfo.UserAddressRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.takewayorder.application.rpc.UserInfoRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-19:38
 * @version: 1.0
 */
@Component
public class UserInfoRpcImpl implements UserInfoRpc {
    private UserInfoClient userInfoClient;

    @Autowired
    public void setUserInfoClient(UserInfoClient userInfoClient) {
        this.userInfoClient = userInfoClient;
    }

    @Override
    public UserInfoRpcDTO getUserInfo(Long userId) {
        return userInfoClient.getUserInfo(userId);
    }

    @Override
    public UserAddressRpcDTO findOneAddressByUserIdAndAddressId(long userId, long addressId) {
        return userInfoClient.findOneAddressByUserIdAndAddressId(userId, addressId);
    }
}
