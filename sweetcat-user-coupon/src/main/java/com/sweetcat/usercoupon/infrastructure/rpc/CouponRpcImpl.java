package com.sweetcat.usercoupon.infrastructure.rpc;

import com.sweetcat.api.client.StoreInfoClient;
import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreIsExistedRpcDTO;
import com.sweetcat.usercoupon.application.rpc.CouponRpc;
import com.sweetcat.usercoupon.application.rpc.StoreInfoRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-16:38
 * @Version: 1.0
 */
@Component
public class CouponRpcImpl implements CouponRpc {
    private CouponRpcImpl couponRpc;

    @Autowired
    public void setCouponRpc(CouponRpcImpl couponRpc) {
        this.couponRpc = couponRpc;
    }


    @Override
    public StoreInfoRpcDTO findOneByCouponId(Long couponId) {
        return couponRpc.findOneByCouponId(couponId);
    }
}
