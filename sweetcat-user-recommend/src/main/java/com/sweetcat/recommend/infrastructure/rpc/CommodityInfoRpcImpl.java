package com.sweetcat.recommend.infrastructure.rpc;

import com.sweetcat.recommend.application.rpc.CommodityInfoRpc;
import com.sweetcat.api.client.CommodityClient;
import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-17:05
 * @version: 1.0
 */
@Component
public class CommodityInfoRpcImpl implements CommodityInfoRpc {

    private CommodityClient commodityClient;

    @Autowired
    public void setCommodityClient(CommodityClient commodityClient) {
        this.commodityClient = commodityClient;
    }

    @Override
    public CommodityInfoRpcDTO findByCommodityId(Long commodityId) {
        return commodityClient.findByCommodityId(commodityId);
    }
}
