package com.sweetcat.storecommodity.interfaces.facade.assembler.rpc;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.CommodityInfo;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-16:54
 * @version: 1.0
 */
@Component
public class CommodityInfoRpcAssemlber {
    public CommodityInfoRpcDTO converterToCommodityInfoRpcDTO(CommodityInfo commodityInfo) {
        CommodityInfoRpcDTO commodityInfoRpcDTO = new CommodityInfoRpcDTO();
        commodityInfoRpcDTO.setCommodityId(commodityInfo.getCommodityId().toString());
        commodityInfoRpcDTO.setStoreId(commodityInfo.getStoreId().toString());
        commodityInfoRpcDTO.setCommodityName(commodityInfo.getCommodityName());
        commodityInfoRpcDTO.setBrand(commodityInfo.getBrand());
        commodityInfoRpcDTO.setPicSmall(commodityInfo.getPicSmall());
        commodityInfoRpcDTO.setPicBig(commodityInfo.getPicBig());
        commodityInfoRpcDTO.setFirstCategory(commodityInfo.getFirstCategory());
        commodityInfoRpcDTO.setSecondCategory(commodityInfo.getSecondCategory());
        commodityInfoRpcDTO.setThirdCategory(commodityInfo.getThirdCategory());
        commodityInfoRpcDTO.setUseTimes(commodityInfo.getUseTimes());
        commodityInfoRpcDTO.setProductionArea(commodityInfo.getProductionArea());
        commodityInfoRpcDTO.setOldPrice(commodityInfo.getOldPrice());
        commodityInfoRpcDTO.setCurrentPrice(commodityInfo.getCurrentPrice());
        commodityInfoRpcDTO.setDescription(commodityInfo.getDescription());
        commodityInfoRpcDTO.setStock(commodityInfo.getStock());
        commodityInfoRpcDTO.setMonthlySales(commodityInfo.getMonthlySales());
        commodityInfoRpcDTO.setAddToCarNumber(commodityInfo.getAddToCarNumber());
        commodityInfoRpcDTO.setCollectNumber(commodityInfo.getCollectNumber());
        commodityInfoRpcDTO.setGuarantee(commodityInfo.getGuarantee());
        commodityInfoRpcDTO.setCreateTime(commodityInfo.getCreateTime());
        commodityInfoRpcDTO.setStatus(commodityInfo.getStatus());
        commodityInfoRpcDTO.setSpecification(commodityInfo.getSpecification());
        commodityInfoRpcDTO.setCommentNumber(commodityInfo.getCommentNumber());
        commodityInfoRpcDTO.setDefaultPostCharge(commodityInfo.getDefaultPostCharge());
        commodityInfoRpcDTO.setSubjoinChargePerGood(commodityInfo.getSubjoinChargePerGood());
        commodityInfoRpcDTO.setCoinCounteractNumber(commodityInfo.getCoinCounteractNumber());
        commodityInfoRpcDTO.setFeedbackCoinNumber(commodityInfo.getFeedbackCoinNumber());
        return commodityInfoRpcDTO;
    }
}
