package com.sweetcat.storeinfo.infrastructure.dao;

import com.sweetcat.storeinfo.domain.storeaddress.entity.StoreAddress;
import com.sweetcat.storeinfo.infrastructure.po.StoreAddressPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreAddressMapper {

    /**
     * 往 db 中加入要给 StoreAddress
     *
     * @param address address
     */
    void insertOne(StoreAddress address);

    /**
     * find store address by store id
     * @param storeId storeId
     */
    StoreAddressPO getById(Long storeId);
}