package com.sweetcat.trolley.interfaces.facade.assembler;

import com.sweetcat.trolley.domain.commodity.entity.Commodity;
import com.sweetcat.trolley.interfaces.facade.restdto.TrolleyCommodityRestDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-22:06
 * @version: 1.0
 */
@Component
public class TrolleyCommodityAssembler {
    public TrolleyCommodityRestDTO converterToTrolleyCommodityRestDTO(Commodity commodity) {
        TrolleyCommodityRestDTO commodityRestDTO = new TrolleyCommodityRestDTO(Long.parseLong(commodity.getKey().split(":")[2]));
        commodityRestDTO.setCount(commodity.getCount());
        commodityRestDTO.setUpdateTime(commodity.getUpdateTime());
        commodityRestDTO.setCommodityId(commodity.getBaseInfo().getCommodityId());
        commodityRestDTO.setCommodityName(commodity.getBaseInfo().getCommodityName());
        commodityRestDTO.setSpecification(commodity.getBaseInfo().getSpecification());
        commodityRestDTO.setOldPrice(commodity.getBaseInfo().getOldPrice());
        commodityRestDTO.setCurrentPrice(commodity.getBaseInfo().getCurrentPrice());
        commodityRestDTO.setPicSmall(commodity.getBaseInfo().getPicSmall());
        commodityRestDTO.setCreateTime(commodity.getBaseInfo().getCreateTime());
        return commodityRestDTO;
    }
}
