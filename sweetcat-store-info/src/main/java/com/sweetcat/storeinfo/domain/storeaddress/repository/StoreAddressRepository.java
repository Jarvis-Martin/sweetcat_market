package com.sweetcat.storeinfo.domain.storeaddress.repository;

import com.sweetcat.storeinfo.domain.storeaddress.entity.StoreAddress;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-16:40
 * @Version: 1.0
 */
public interface StoreAddressRepository {

    /**
     * 添加要给 store address
     *
     * @param address address
     */
    void addOne(StoreAddress address);

    /**
     * find store address by storeId
     *
     * @param storeId storeId
     * @return StoreAddress
     */
    StoreAddress find(Long storeId);

}
