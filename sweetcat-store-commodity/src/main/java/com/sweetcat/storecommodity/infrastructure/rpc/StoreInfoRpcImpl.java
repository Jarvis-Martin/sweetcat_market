package com.sweetcat.storecommodity.infrastructure.rpc;

import com.sweetcat.api.client.StoreInfoClient;
import com.sweetcat.api.rpcdto.storeinfo.StoreIsExistedRpcDTO;
import com.sweetcat.storecommodity.application.rpc.StoreInfoRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-16:38
 * @Version: 1.0
 */
@Component
public class StoreInfoRpcImpl implements StoreInfoRpc {
    private StoreInfoClient storeInfoClient;

    @Autowired
    public void setStoreInfoClient(StoreInfoClient storeInfoClient) {
        this.storeInfoClient = storeInfoClient;
    }

    @Override
    public StoreIsExistedRpcDTO storeIsExisted(Long storeId) {
        return storeInfoClient.storeIsExisted(storeId);
    }
}
