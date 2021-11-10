package com.sweetcat.api.client;

import com.sweetcat.api.rpcdto.geography.GeographyRpcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-15:47
 * @version: 1.0
 */
@Component
@FeignClient("sweetcat-app-geography")
public interface GeographyClient {
    /**
     * 根据 addressCode 查找一个 geography
     *
     * @param addressCode addressCode
     * @return
     */
    @GetMapping("/rpc/app/geography/{address_code}")
    GeographyRpcDTO getGeographyInfo(@PathVariable("address_code") String addressCode);

}
