package com.sweetcat.secondkill.interfaces.facade.assembler.rpc;

import com.sweetcat.api.rpcdto.secondkill.SKCommodityInfoRpcDTO;
import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-16:54
 * @version: 1.0
 */
@Component
public class SKCommodityInfoRpcAssemlber {
    public SKCommodityInfoRpcDTO converterToCommodityInfoRpcDTO(SKCommodity commodity) {
        SKCommodityInfoRpcDTO commodityInfoRpcDTO = new SKCommodityInfoRpcDTO(commodity.getCommodityId().toString());
        inflateCommodityInfoRpcDTO(commodity, commodityInfoRpcDTO);
        return commodityInfoRpcDTO;
    }

    private void inflateCommodityInfoRpcDTO(SKCommodity commodity, SKCommodityInfoRpcDTO commodityInfoRpcDTO) {
        commodityInfoRpcDTO.setStoreId(commodity.getStore().getStoreId().toString());
        commodityInfoRpcDTO.setCommodityName(commodity.getCommodityName());
        commodityInfoRpcDTO.setBrand(commodity.getBrand());
        commodityInfoRpcDTO.setPicSmall(commodity.getPicSmall());
        commodityInfoRpcDTO.setPicBig(commodity.getPicBig());
        commodityInfoRpcDTO.setFirstCategory(commodity.getFirstCategory());
        commodityInfoRpcDTO.setSecondCategory(commodity.getSecondCategory());
        commodityInfoRpcDTO.setThirdCategory(commodity.getThirdCategory());
        commodityInfoRpcDTO.setUseTimes(commodity.getUseTimes());
        commodityInfoRpcDTO.setProductionArea(commodity.getProductionArea());
        commodityInfoRpcDTO.setOldPrice(commodity.getOldPrice());
        commodityInfoRpcDTO.setCurrentPrice(commodity.getCurrentPrice());
        commodityInfoRpcDTO.setDescription(commodity.getDescription());
        commodityInfoRpcDTO.setTotalStock(commodity.getTotalStock());
        commodityInfoRpcDTO.setMonthlySales(commodity.getMonthlySales());
        commodityInfoRpcDTO.setAddToCarNumber(commodity.getAddToCarNumber());
        commodityInfoRpcDTO.setCollectNumber(commodity.getCollectNumber());
        commodityInfoRpcDTO.setGuarantee(commodity.getGuarantee());
        commodityInfoRpcDTO.setCreateTime(commodity.getCreateTime());
        commodityInfoRpcDTO.setStatus(commodity.getStatus());
        commodityInfoRpcDTO.setSpecification(commodity.getSpecification());
        commodityInfoRpcDTO.setCommentNumber(commodity.getCommentNumber());
        commodityInfoRpcDTO.setDefaultPostCharge(commodity.getDefaultPostCharge());
        commodityInfoRpcDTO.setSubjoinChargePerGood(commodity.getSubjoinChargePerGood());
        commodityInfoRpcDTO.setStartTime(commodity.getStartTime());
        commodityInfoRpcDTO.setRemainStock(commodity.getRemainStock());
    }
}
