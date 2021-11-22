package com.sweetcat.usercoupon.application.rpc;

import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-16:39
 * @Version: 1.0
 */
public interface CouponRpc {

    /**
     * find store by store id
     *
     * @param couponId couponId
     * @return
     */
    StoreInfoRpcDTO findOneByCouponId(Long couponId);

}
