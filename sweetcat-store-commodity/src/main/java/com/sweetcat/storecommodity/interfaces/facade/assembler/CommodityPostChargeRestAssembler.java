package com.sweetcat.storecommodity.interfaces.facade.assembler;

import com.sweetcat.storecommodity.domain.commonditypostcharge.entity.CommodityPostCharge;
import com.sweetcat.storecommodity.interfaces.facade.restdto.CommodityPostChargeDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-19:59
 * @version: 1.0
 */
@Component
public class CommodityPostChargeRestAssembler {
    public CommodityPostChargeDTO converterToCommodityPostChargeDTO(CommodityPostCharge charge) {
        CommodityPostChargeDTO chargeDTO = new CommodityPostChargeDTO();
        chargeDTO.setChargeId(charge.getChargeId());
        chargeDTO.setCommodityId(charge.getCommodityId());
        chargeDTO.setStoreId(charge.getStoreId());
        chargeDTO.setProvinceCode(charge.getProvinceCode());
        chargeDTO.setPostCharge(charge.getPostCharge());
        chargeDTO.setCreateTime(charge.getCreateTime());
        chargeDTO.setUpdateTime(charge.getUpdateTime());
        return chargeDTO;
    }
}
