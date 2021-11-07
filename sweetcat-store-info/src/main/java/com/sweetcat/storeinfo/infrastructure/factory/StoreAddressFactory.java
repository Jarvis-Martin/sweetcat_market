package com.sweetcat.storeinfo.infrastructure.factory;

import com.sweetcat.storeinfo.domain.storeaddress.entity.StoreAddress;
import com.sweetcat.storeinfo.infrastructure.po.StoreAddressPO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-16:43
 * @Version: 1.0
 */
@Component
public class StoreAddressFactory {
    public StoreAddress create(StoreAddressPO storeAddressPO) {
        StoreAddress storeAddress = new StoreAddress(storeAddressPO.getStoreId());
        storeAddress.setProvinceName(storeAddressPO.getProvinceName());
        storeAddress.setCityName(storeAddressPO.getCityName());
        storeAddress.setAreaName(storeAddressPO.getAreaName());
        storeAddress.setTownName(storeAddressPO.getTownName());
        storeAddress.setDetailAddress(storeAddressPO.getDetailAddress());
        storeAddress.setCreateTime(storeAddressPO.getCreateTime());
        storeAddress.setUpdateTime(storeAddressPO.getUpdateTime());
        return storeAddress;
    }
}
