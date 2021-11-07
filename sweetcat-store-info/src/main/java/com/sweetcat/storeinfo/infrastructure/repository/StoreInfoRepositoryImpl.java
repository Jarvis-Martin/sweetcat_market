package com.sweetcat.storeinfo.infrastructure.repository;

import com.sweetcat.storeinfo.domain.storeinfo.entity.StoreInfo;
import com.sweetcat.storeinfo.domain.storeinfo.repository.StoreInfoRepository;
import com.sweetcat.storeinfo.infrastructure.dao.StoreInfoMapper;
import com.sweetcat.storeinfo.infrastructure.factory.StoreInfoFactory;
import com.sweetcat.storeinfo.infrastructure.po.StoreInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-16:30
 * @Version: 1.0
 */
@Service
public class StoreInfoRepositoryImpl implements StoreInfoRepository {
    private StoreInfoMapper storeInfoMapper;
    private StoreInfoFactory storeInfoFactory;

    @Autowired
    public void setStoreInfoFactory(StoreInfoFactory storeInfoFactory) {
        this.storeInfoFactory = storeInfoFactory;
    }

    @Autowired
    public void setStoreInfoMapper(StoreInfoMapper storeInfoMapper) {
        this.storeInfoMapper = storeInfoMapper;
    }

    /**
     * find store info by storeId
     *
     * @param storeId storeId
     * @return
     */
    @Override
    public StoreInfo find(Long storeId) {
        StoreInfoPO storeInfoPO = storeInfoMapper.find(storeId);
        return storeInfoFactory.create(storeInfoPO);
    }

    /**
     * 向 db 中添加一个 storeInfo
     *
     * @param storeInfo storeInfo
     */
    @Override
    public void addOne(StoreInfo storeInfo) {
        storeInfoMapper.insertOne(storeInfo);
    }
}
