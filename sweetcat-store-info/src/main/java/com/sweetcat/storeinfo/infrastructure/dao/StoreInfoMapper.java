package com.sweetcat.storeinfo.infrastructure.dao;

import com.sweetcat.storeinfo.domain.storeinfo.entity.StoreInfo;
import com.sweetcat.storeinfo.infrastructure.po.StoreInfoPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreInfoMapper {
    /**
     * find store info by storeId
     *
     * @param storeId storeId
     * @return
     */
    StoreInfoPO find(Long storeId);

    /**
     * 向 db 中添加一个 storeInfo
     *
     * @param storeInfo storeInfo
     */
    void insertOne(StoreInfo storeInfo);

    Long isExisted(Long storeId);
}