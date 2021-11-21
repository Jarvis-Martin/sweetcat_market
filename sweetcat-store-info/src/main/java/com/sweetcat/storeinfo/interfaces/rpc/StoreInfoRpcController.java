package com.sweetcat.storeinfo.interfaces.rpc;

import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreIsExistedRpcDTO;
import com.sweetcat.storeinfo.domain.storeinfo.entity.StoreInfo;
import com.sweetcat.storeinfo.interfaces.facade.StoreInfoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-16:05
 * @Version: 1.0
 */

@RestController
@RequestMapping("/rpc/store")
public class StoreInfoRpcController {
    private StoreInfoFacade storeInfoFacade;

    @Autowired
    public void setStoreInfoFacade(StoreInfoFacade storeInfoFacade) {
        this.storeInfoFacade = storeInfoFacade;
    }

    @GetMapping("/{store_id}")
    public StoreIsExistedRpcDTO storeIsExisted(@PathVariable("store_id") Long storeId) {
        Boolean storeIsExisted = storeInfoFacade.storeIsExisted(storeId);
        return new StoreIsExistedRpcDTO(storeId.toString(), storeIsExisted, Instant.now().toEpochMilli());
    }

    @GetMapping("/storeinfo/{store_id}")
    public StoreInfoRpcDTO findStoreInfo(@PathVariable("store_id") Long storeId) {
        StoreInfo storeInfo = storeInfoFacade.getOneById(storeId);
        StoreInfoRpcDTO storeInfoRpcDTO = new StoreInfoRpcDTO(storeId);
        storeInfoRpcDTO.setStoreName(storeInfo.getStoreName());
        storeInfoRpcDTO.setPrincipalName(storeInfo.getPrincipalName());
        storeInfoRpcDTO.setPrincipalPhone(storeInfo.getPrincipalPhone());
        storeInfoRpcDTO.setMainBusiness(storeInfo.getMainBusiness());
        storeInfoRpcDTO.setType(storeInfo.getType());
        storeInfoRpcDTO.setCreateTime(storeInfo.getCreateTime());
        return storeInfoRpcDTO;
    }

}
