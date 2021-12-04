package com.sweetcat.api.client;

import com.sweetcat.api.rpcdto.userinfo.UserAddressRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/4-20:46
 * @Version: 1.0
 */
@Component
@FeignClient("sweetcat-user-info")
public interface UserInfoClient {

    /**
     * 获得用户详情
     *
     * @param userId userId
     * @return 用户详情
     */
    @GetMapping("/rpc/user/{user_id}")
    UserInfoRpcDTO getUserInfo(@PathVariable("user_id") Long userId);

    @GetMapping("/rpc/user/address")
    UserAddressRpcDTO findOneAddressByUserIdAndAddressId(@RequestParam("userId") Long userId, @RequestParam("addressId") Long addressId);
}
