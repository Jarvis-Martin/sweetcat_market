package com.sweetcat.storeorder.infrastructure.rpc;

import com.sweetcat.api.client.UserOrderClient;
import com.sweetcat.api.rpcdto.userorder.UserOrderRpcDTO;
import com.sweetcat.storeorder.application.rpc.UserOrderRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/7-19:56
 * @version: 1.0
 */
@Component
public class UserOrderRpcImpl implements UserOrderRpc {
    private UserOrderClient userOrderClient;

    @Autowired
    public void setUserOrderClient(UserOrderClient userOrderClient) {
        this.userOrderClient = userOrderClient;
    }

    @Override
    public UserOrderRpcDTO findOneByUserIdAndOrderId(Long userId, Long orderId) {
        return userOrderClient.findOneByUserIdAndOrderId(userId, orderId);
    }
}
