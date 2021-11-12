package com.sweetcat.favorite.application.rpc;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-16:48
 * @version: 1.0
 */
public interface CommodityInfoRpc {
    /**
     * 根据商品id 找商品
     *
     * @param commodityId
     * @return
     */
    CommodityInfoRpcDTO findByCommodityId(Long commodityId);
}
