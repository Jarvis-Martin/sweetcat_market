package com.sweetcat.storeinfo.application.service;

import com.sweetcat.storeinfo.application.commmand.AddStoreAddressCommand;
import com.sweetcat.storeinfo.domain.storeaddress.entity.StoreAddress;
import com.sweetcat.storeinfo.domain.storeaddress.repository.StoreAddressRepository;
import com.sweetcat.storeinfo.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.storeinfo.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-17:11
 * @Version: 1.0
 */
@Service
public class StoreAddressApplicationService {
    private StoreAddressRepository storeAddressRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setStoreAddressRepository(StoreAddressRepository storeAddressRepository) {
        this.storeAddressRepository = storeAddressRepository;
    }

    public StoreAddress getOneById(Long storeId) {
        // 检查 storeId 格式
        verifyIdFormatService.verifyIds(storeId);
        return storeAddressRepository.find(storeId);
    }

    public void addOne(AddStoreAddressCommand command) {
        long addressId = snowFlakeService.snowflakeId();
        // 构造店铺地址
        StoreAddress storeAddress = new StoreAddress(command.getStoreId(), addressId);
        // 添加
        storeAddressRepository.addOne(storeAddress);
    }

}
