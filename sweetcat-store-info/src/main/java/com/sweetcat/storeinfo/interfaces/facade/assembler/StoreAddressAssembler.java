package com.sweetcat.storeinfo.interfaces.facade.assembler;

import com.sweetcat.api.rpcdto.storeinfo.StoreAddressInfoRpcDTO;
import com.sweetcat.storeinfo.domain.storeaddress.entity.StoreAddress;
import com.sweetcat.storeinfo.interfaces.facade.restdto.StoreAddressDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-19:58
 * @Version: 1.0
 */
@Component
public class StoreAddressAssembler {
    public StoreAddressDTO converterToStoreAddressDTO(StoreAddress storeAddress) {
        StoreAddressDTO storeAddressDTO = new StoreAddressDTO(storeAddress.getStoreId(), storeAddress.getAddressId());
        storeAddressDTO.setProvinceName(storeAddress.getProvinceName());
        storeAddressDTO.setCityName(storeAddress.getCityName());
        storeAddressDTO.setAreaName(storeAddress.getAreaName());
        storeAddressDTO.setTownName(storeAddress.getTownName());
        storeAddressDTO.setDetailAddress(storeAddress.getDetailAddress());
        storeAddressDTO.setCreateTime(storeAddress.getCreateTime());
        return storeAddressDTO;
    }

    public StoreAddressInfoRpcDTO converterToStoreAddressRpcDTO(StoreAddress storeAddress) {
        Long addressId = storeAddress.getAddressId();
        StoreAddressInfoRpcDTO storeAddressInfoRpcDTO = new StoreAddressInfoRpcDTO(storeAddress.getStoreId());
        storeAddressInfoRpcDTO.setAddressId(addressId);
        storeAddressInfoRpcDTO.setProvinceName(storeAddress.getProvinceName());
        storeAddressInfoRpcDTO.setCityName(storeAddress.getCityName());
        storeAddressInfoRpcDTO.setAreaName(storeAddress.getAreaName());
        storeAddressInfoRpcDTO.setTownName(storeAddress.getTownName());
        storeAddressInfoRpcDTO.setDetailAddress(storeAddress.getDetailAddress());
        return storeAddressInfoRpcDTO;
    }
}
