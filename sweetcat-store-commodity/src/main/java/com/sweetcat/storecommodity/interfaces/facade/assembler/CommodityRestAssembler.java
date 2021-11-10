package com.sweetcat.storecommodity.interfaces.facade.assembler;

import com.sweetcat.storecommodity.domain.commodityinfo.entity.CommodityInfo;
import com.sweetcat.storecommodity.interfaces.facade.restdto.CommodityInfoDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-22:59
 * @Version: 1.0
 */
@Component
public class CommodityRestAssembler {
    public CommodityInfoDTO converterToCommodityInfoDTO(CommodityInfo commodityInfo) {
        CommodityInfoDTO commodityInfoDTO = new CommodityInfoDTO();
        commodityInfoDTO.setCommodityId(commodityInfo.getCommodityId().toString());
        commodityInfoDTO.setStoreId(commodityInfo.getStoreId().toString());
        commodityInfoDTO.setCommodityName(commodityInfo.getCommodityName());
        commodityInfoDTO.setBrand(commodityInfo.getBrand());
        commodityInfoDTO.setPicSmall(commodityInfo.getPicSmall());
        commodityInfoDTO.setPicBig(commodityInfo.getPicBig());
        commodityInfoDTO.setFirstCategory(commodityInfo.getFirstCategory());
        commodityInfoDTO.setSecondCategory(commodityInfo.getSecondCategory());
        commodityInfoDTO.setThirdCategory(commodityInfo.getThirdCategory());
        commodityInfoDTO.setUseTimes(commodityInfo.getUseTimes());
        commodityInfoDTO.setProductionArea(commodityInfo.getProductionArea());
        commodityInfoDTO.setOldPrice(commodityInfo.getOldPrice());
        commodityInfoDTO.setCurrentPrice(commodityInfo.getCurrentPrice());
        commodityInfoDTO.setDescription(commodityInfo.getDescription());
        commodityInfoDTO.setStock(commodityInfo.getStock());
        commodityInfoDTO.setMonthlySales(commodityInfo.getMonthlySales());
        commodityInfoDTO.setAddToCarNumber(commodityInfo.getAddToCarNumber());
        commodityInfoDTO.setCollectNumber(commodityInfo.getCollectNumber());
        commodityInfoDTO.setGuarantee(commodityInfo.getGuarantee());
        commodityInfoDTO.setCreateTime(commodityInfo.getCreateTime());
        commodityInfoDTO.setStatus(commodityInfo.getStatus());
        commodityInfoDTO.setSpecification(commodityInfo.getSpecification());
        commodityInfoDTO.setCommentNumber(commodityInfo.getCommentNumber());
        commodityInfoDTO.setDefaultPostCharge(commodityInfo.getDefaultPostCharge());
        commodityInfoDTO.setSubjoinChargePerGood(commodityInfo.getSubjoinChargePerGood());
        commodityInfoDTO.setCoinCounteractNumber(commodityInfo.getCoinCounteractNumber());
        commodityInfoDTO.setFeedbackCoinNumber(commodityInfo.getFeedbackCoinNumber());
        return commodityInfoDTO;
    }
}
