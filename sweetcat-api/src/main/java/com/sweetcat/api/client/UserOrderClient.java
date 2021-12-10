package com.sweetcat.api.client;

import com.sweetcat.api.rpcdto.userorder.UserOrderRpcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/7-19:53
 * @version: 1.0
 */
@Component
@FeignClient("sweetcat-user-order")
public interface UserOrderClient {
    @GetMapping("/rpc/user_order/order/{order_id}")
    UserOrderRpcDTO findOneByUserIdAndOrderId(@RequestParam("userId") Long userId, @PathVariable("order_id") Long orderId);
}
