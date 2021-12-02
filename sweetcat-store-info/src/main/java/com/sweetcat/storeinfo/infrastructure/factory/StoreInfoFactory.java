package com.sweetcat.storeinfo.infrastructure.factory;

import com.sweetcat.storeinfo.domain.storeinfo.entity.StoreInfo;
import com.sweetcat.storeinfo.infrastructure.po.StoreInfoPO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-16:31
 * @Version: 1.0
 */
@Component
public class StoreInfoFactory {
    public StoreInfo create(StoreInfoPO storeInfoPO) {
        StoreInfo storeInfo = new StoreInfo(storeInfoPO.getStoreId());
        storeInfo.setStoreName(storeInfoPO.getStoreName());
        storeInfo.setStoreLogo(storeInfoPO.getStoreLogo());
        storeInfo.setPrincipalName(storeInfoPO.getPrincipalName());
        storeInfo.setPrincipalPhone(storeInfoPO.getPrincipalPhone());
        storeInfo.setMainBusiness(storeInfoPO.getMainBusiness());
        storeInfo.setType(storeInfoPO.getType());
        storeInfo.setCreateTime(storeInfoPO.getCreateTime());
        storeInfo.setUpdateTime(storeInfoPO.getUpdateTime());
        return storeInfo;
    }
}
