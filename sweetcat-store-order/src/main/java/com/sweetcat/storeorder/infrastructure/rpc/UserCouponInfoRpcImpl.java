package com.sweetcat.storeorder.infrastructure.rpc;

import com.sweetcat.api.client.UserCouponClient;
import com.sweetcat.api.rpcdto.usercoupon.CouponInfoRpcDTO;
import com.sweetcat.storeorder.application.rpc.UserCouponInfoRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-16:38
 * @Version: 1.0
 */
@Component
public class UserCouponInfoRpcImpl implements UserCouponInfoRpc {
    private UserCouponClient userCouponClient;

    @Autowired
    public void setUserCouponClient(UserCouponClient userCouponClient) {
        this.userCouponClient = userCouponClient;
    }

    @Override
    public CouponInfoRpcDTO findOneByCouponId(Long userId, Long couponId) {
        return userCouponClient.findOneByCouponId(userId, couponId);
    }
}
