package com.sweetcat.storeinfo.interfaces.facade;

import com.sweetcat.storeinfo.application.service.StoreAddressApplicationService;
import com.sweetcat.storeinfo.domain.storeaddress.entity.StoreAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-19:50
 * @Version: 1.0
 */
@Component
public class StoreAddressFacade {
    private StoreAddressApplicationService storeAddressApplicationService;

    @Autowired
    public void setStoreAddressApplicationService(StoreAddressApplicationService storeAddressApplicationService) {
        this.storeAddressApplicationService = storeAddressApplicationService;
    }

    public StoreAddress getOneById(Long storeId) {
        return storeAddressApplicationService.getOneById(storeId);
    }

    public void addOne(Long storeId, String provinceName, String cityName, String areaName, String townName, String detailAddress,
                      Long createTime) {
        storeAddressApplicationService.addOne(storeId, provinceName, cityName, areaName, townName, detailAddress, createTime);
    }
}
