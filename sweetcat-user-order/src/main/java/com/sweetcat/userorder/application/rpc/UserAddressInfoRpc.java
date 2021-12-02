package com.sweetcat.userorder.application.rpc;

import com.sweetcat.api.rpcdto.userinfo.UserAddressRpcDTO;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-16:39
 * @Version: 1.0
 */
public interface UserAddressInfoRpc {

    /**
     * find store by store id
     *
     * @param userId    userId
     * @param addressId addressId
     * @return
     */
    UserAddressRpcDTO findOneAddressByUserIdAndAddressId(Long userId, Long addressId);
}
