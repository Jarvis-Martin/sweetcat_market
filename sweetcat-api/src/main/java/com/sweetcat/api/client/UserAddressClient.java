package com.sweetcat.api.client;

import com.sweetcat.api.rpcdto.userinfo.UserAddressRpcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/1-14:50
 * @version: 1.0
 */
@Component
@FeignClient("sweetcat-user-info")
public interface UserAddressClient {
    @GetMapping("/address/{address_id}")
    UserAddressRpcDTO findOneAddressByUserIdAndAddressId(Long userId, @PathVariable("address_id") Long addressId);
}
