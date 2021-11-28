package com.sweetcat.usercomment.application.rpc;

import com.sweetcat.api.rpcdto.secondkill.SKCommodityInfoRpcDTO;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-16:48
 * @version: 1.0
 */
public interface SKCommodityInfoRpc {
    /**
     * 根据商品id 找商品
     *
     * @param commodityId
     * @return
     */
    SKCommodityInfoRpcDTO findByCommodityId(Long commodityId);
}
