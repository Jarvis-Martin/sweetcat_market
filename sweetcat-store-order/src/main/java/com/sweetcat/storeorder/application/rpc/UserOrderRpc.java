package com.sweetcat.storeorder.application.rpc;

import com.sweetcat.api.rpcdto.userorder.UserOrderRpcDTO;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/7-19:55
 * @version: 1.0
 */
public interface UserOrderRpc {
    UserOrderRpcDTO findOneByUserIdAndOrderId(Long userId, Long orderId);
}
