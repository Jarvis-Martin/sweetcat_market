package com.sweetcat.secondkill.interfaces.facade.assembler;

import com.sweetcat.secondkill.domain.commonditypostcharge.entity.SKCommodityPostCharge;
import com.sweetcat.secondkill.interfaces.facade.restdto.SKCommodityPostChargeDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-19:59
 * @version: 1.0
 */
@Component
public class SKCommodityPostChargeRestAssembler {
    public SKCommodityPostChargeDTO converterToCommodityPostChargeDTO(SKCommodityPostCharge charge) {
        SKCommodityPostChargeDTO chargeDTO = new SKCommodityPostChargeDTO();
        chargeDTO.setChargeId(charge.getChargeId());
        chargeDTO.setCommodityId(charge.getCommodityId());
        chargeDTO.setStoreId(charge.getStore().getStoreId());
        chargeDTO.setProvinceCode(charge.getProvinceCode());
        chargeDTO.setPostCharge(charge.getPostCharge());
        chargeDTO.setCreateTime(charge.getCreateTime());
        chargeDTO.setUpdateTime(charge.getUpdateTime());
        return chargeDTO;
    }
}
