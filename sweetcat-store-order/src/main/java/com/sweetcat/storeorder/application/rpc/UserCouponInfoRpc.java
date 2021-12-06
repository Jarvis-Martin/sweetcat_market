package com.sweetcat.storeorder.application.rpc;

import com.sweetcat.api.rpcdto.usercoupon.CouponInfoRpcDTO;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-16:39
 * @Version: 1.0
 */
public interface UserCouponInfoRpc {

    /**
     * find store by store id
     *
     *
     * @param userId
     * @param couponId couponId
     * @return
     */
    CouponInfoRpcDTO findOneByCouponId(Long userId, Long couponId);

}
