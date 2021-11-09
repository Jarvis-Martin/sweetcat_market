package com.sweetcat.storeinfo.interfaces.facade;

import com.sweetcat.storeinfo.application.service.StoreInfoApplicationService;
import com.sweetcat.storeinfo.domain.storeinfo.entity.StoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-17:29
 * @Version: 1.0
 */
@Component
public class StoreInfoFacade {
    private StoreInfoApplicationService storeInfoApplicationService;

    @Autowired
    public void setStoreInfoApplicationService(StoreInfoApplicationService storeInfoApplicationService) {
        this.storeInfoApplicationService = storeInfoApplicationService;
    }

    /**
     * get store info by storeId
     *
     * @param storeId storeId
     * @return
     */
    public StoreInfo getOneById(Long storeId) {
        return storeInfoApplicationService.getOneById(storeId);
    }

    /**
     * 添加一个 storeaddress
     * @param storeName storeName
     * @param principalName principalName
     * @param principalPhone principalPhone
     * @param mainBusiness mainBusiness
     * @param type type
     * @param createTime createTime
     */
    public void addOne(String storeName, String principalName, String principalPhone, String mainBusiness, Integer type, Long createTime) {
        storeInfoApplicationService.addOne(storeName, principalName, principalPhone, mainBusiness, type, createTime);
    }

    public Boolean storeIsExisted(Long storeId) {
        return storeInfoApplicationService.storeIsExisted(storeId);
    }
}
