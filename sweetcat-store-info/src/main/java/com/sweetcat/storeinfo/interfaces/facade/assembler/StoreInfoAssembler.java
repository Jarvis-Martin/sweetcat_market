package com.sweetcat.storeinfo.interfaces.facade.assembler;

import com.sweetcat.storeinfo.domain.storeinfo.entity.StoreInfo;
import com.sweetcat.storeinfo.interfaces.facade.restdto.StoreInfoDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-17:34
 * @Version: 1.0
 */
@Component
public class StoreInfoAssembler {
    public StoreInfoDTO converterToStoreInfoDTO(StoreInfo storeInfo) {
        StoreInfoDTO storeInfoDTO = new StoreInfoDTO(storeInfo.getStoreId());
        storeInfoDTO.setStoreName(storeInfo.getStoreName());
        storeInfoDTO.setStoreLogo(storeInfo.getStoreLogo());
        storeInfoDTO.setPrincipalName(storeInfo.getPrincipalName());
        storeInfoDTO.setPrincipalPhone(storeInfo.getPrincipalPhone());
        storeInfoDTO.setMainBusiness(storeInfo.getMainBusiness());
        storeInfoDTO.setType(storeInfo.getType());
        storeInfoDTO.setCreateTime(storeInfo.getCreateTime());
        return storeInfoDTO;
    }
}
