package com.sweetcat.api.client;

import com.sweetcat.api.rpcdto.storeinfo.StoreIsExistedRpcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-16:32
 * @Version: 1.0
 */
@Component
@FeignClient("sweetcat-store-info")
public interface StoreInfoClient {
    @GetMapping("/rpc/store/{store_id}")
    StoreIsExistedRpcDTO storeIsExisted(@PathVariable("store_id") Long storeId);
}
