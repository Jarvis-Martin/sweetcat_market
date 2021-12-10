package com.sweetcat.storeinfo.infrastructure.repository;

import com.sweetcat.storeinfo.domain.storeaddress.entity.StoreAddress;
import com.sweetcat.storeinfo.domain.storeaddress.repository.StoreAddressRepository;
import com.sweetcat.storeinfo.infrastructure.dao.StoreAddressMapper;
import com.sweetcat.storeinfo.infrastructure.factory.StoreAddressFactory;
import com.sweetcat.storeinfo.infrastructure.po.StoreAddressPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-16:42
 * @Version: 1.0
 */
@Repository
public class StoreAddressRepositoryImpl implements StoreAddressRepository {
    private StoreAddressFactory storeAddressFactory;
    private StoreAddressMapper storeAddressMapper;

    @Autowired
    public void setStoreAddressFactory(StoreAddressFactory storeAddressFactory) {
        this.storeAddressFactory = storeAddressFactory;
    }

    @Autowired
    public void setStoreAddressMapper(StoreAddressMapper storeAddressMapper) {
        this.storeAddressMapper = storeAddressMapper;
    }

    /**
     * 添加要给 store address
     *
     * @param address address
     */
    @Override
    public void addOne(StoreAddress address) {
        storeAddressMapper.insertOne(address);
    }

    /**
     * find store address by storeId
     *
     * @param storeId storeId
     * @return StoreAddress
     */
    @Override
    public StoreAddress find(Long storeId) {
        StoreAddressPO storeAddressPO = storeAddressMapper.getById(storeId);
        if (storeAddressPO == null) {
            return null;
        }
        return storeAddressFactory.create(storeAddressPO);
    }
}
