package com.sweetcat.takewayorder.application.rpc;

import com.sweetcat.api.rpcdto.storeinfo.StoreAddressInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreIsExistedRpcDTO;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-16:39
 * @Version: 1.0
 */
public interface StoreInfoRpc {
    /**
     * 判断 store 是否已注册
     *
     * @param storeId storeId
     * @return
     */
    StoreIsExistedRpcDTO storeIsExisted(Long storeId);

    /**
     * find store by store id
     *
     * @param storeId storeId
     * @return
     */
    StoreInfoRpcDTO findOneByStoreId(Long storeId);

    StoreAddressInfoRpcDTO findOneStoreAddressByStoreId(Long storeId);
}
