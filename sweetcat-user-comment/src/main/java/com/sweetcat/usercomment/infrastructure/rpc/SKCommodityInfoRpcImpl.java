package com.sweetcat.usercomment.infrastructure.rpc;

import com.sweetcat.api.client.SKCommodityClient;
import com.sweetcat.api.rpcdto.secondkill.SKCommodityInfoRpcDTO;
import com.sweetcat.usercomment.application.rpc.SKCommodityInfoRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-17:05
 * @version: 1.0
 */
@Component
public class SKCommodityInfoRpcImpl implements SKCommodityInfoRpc {

    private SKCommodityClient skCommodityClient;

    @Autowired
    public void setCommodityClient(SKCommodityClient skCommodityClient) {
        this.skCommodityClient = skCommodityClient;
    }

    @Override
    public SKCommodityInfoRpcDTO findByCommodityId(Long commodityId) {
        return skCommodityClient.findByCommodityId(commodityId);
    }
}
