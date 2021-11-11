package com.sweetcat.api.client;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-16:49
 * @version: 1.0
 */
@Component
@FeignClient("sweetcat-store-commodity")
public interface CommodityClient {

    /**
     * 根据商品id 找商品
     *
     * @param commodityId
     * @return
     */
    @GetMapping("/rpc/commodity/{commodity_id}")
    CommodityInfoRpcDTO findByCommodityId(@PathVariable("commodity_id") Long commodityId);
}
