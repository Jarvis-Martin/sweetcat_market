package com.sweetcat.userorder.infrastructure.rpc;

import com.sweetcat.api.client.UserAddressClient;
import com.sweetcat.api.rpcdto.userinfo.UserAddressRpcDTO;
import com.sweetcat.userorder.application.rpc.UserAddressInfoRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/1-19:25
 * @version: 1.0
 */
@Component
public class UserAddressInfoRpcImpl implements UserAddressInfoRpc {
    private UserAddressClient addressClient;

    @Autowired
    public void setAddressClient(UserAddressClient addressClient) {
        this.addressClient = addressClient;
    }

    @Override
    public UserAddressRpcDTO findOneAddressByUserIdAndAddressId(Long userId, Long addressId) {
        return addressClient.findOneAddressByUserIdAndAddressId(userId, addressId);
    }
}
