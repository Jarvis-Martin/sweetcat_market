package com.sweetcat.storeinfo.domain.storeinfo.repository;

import com.sweetcat.storeinfo.domain.storeinfo.entity.StoreInfo;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-16:28
 * @Version: 1.0
 */
public interface StoreInfoRepository {

    /**
     * find store info by storeId
     *
     * @param storeId storeId
     * @return
     */
    StoreInfo find(Long storeId);

    /**
     * 添加一个 storeinfo
     *
     * @param storeInfo storeInfo
     */
    void addOne(StoreInfo storeInfo);
}
