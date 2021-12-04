package com.sweetcat.api.client;

import com.sweetcat.api.rpcdto.usercoupon.CouponInfoRpcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/1-14:50
 * @version: 1.0
 */
@FeignClient("sweetcat-user-coupon")
public interface UserCouponClient {
    @GetMapping("/rpc/user_coupon/{coupon_id}")
    CouponInfoRpcDTO findOneByCouponId(@RequestParam("userId") Long userId, @PathVariable("coupon_id") Long couponId);
}
