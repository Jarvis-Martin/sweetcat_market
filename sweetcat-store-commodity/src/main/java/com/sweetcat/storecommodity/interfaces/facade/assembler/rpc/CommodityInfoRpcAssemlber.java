package com.sweetcat.storecommodity.interfaces.facade.assembler.rpc;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.Commodity;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-16:54
 * @version: 1.0
 */
@Component
public class CommodityInfoRpcAssemlber {
    public CommodityInfoRpcDTO converterToCommodityInfoRpcDTO(Commodity commodity) {
        CommodityInfoRpcDTO commodityInfoRpcDTO = new CommodityInfoRpcDTO();
        commodityInfoRpcDTO.setCommodityId(commodity.getCommodityId().toString());
        commodityInfoRpcDTO.setStoreId(commodity.getStoreId().toString());
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
        commodityInfoRpcDTO.setStock(commodity.getStock());
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
        commodityInfoRpcDTO.setCoinCounteractNumber(commodity.getCoinCounteractNumber());
        commodityInfoRpcDTO.setFeedbackCoinNumber(commodity.getFeedbackCoinNumber());
        return commodityInfoRpcDTO;
    }
}
