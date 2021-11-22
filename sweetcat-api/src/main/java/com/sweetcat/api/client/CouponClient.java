package com.sweetcat.api.client;


import com.sweetcat.api.rpcdto.couponcenter.CouponRpcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-11:03
 * @version: 1.0
 */
@Component
@FeignClient("sweetcat-app-couponcenter")
public interface CouponClient {
    @GetMapping("/{coupon_id}")
    CouponRpcDTO findOneByCouponId(@PathVariable("coupon_id") Long couponId);
}
