package com.sweetcat.storecommodity.interfaces.facade.assembler;

import com.sweetcat.storecommodity.domain.commodityinfo.entity.CommodityInfo;
import com.sweetcat.storecommodity.interfaces.facade.restdto.CommodityDetailDTO;
import com.sweetcat.storecommodity.interfaces.facade.restdto.CommoditySummaryInfoRestDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-22:59
 * @Version: 1.0
 */
@Component
public class CommodityRestAssembler {
    public CommodityDetailDTO converterToCommodityInfoDTO(CommodityInfo commodityInfo) {
        CommodityDetailDTO commodityDetailDTO = new CommodityDetailDTO();
        commodityDetailDTO.setCommodityId(commodityInfo.getCommodityId().toString());
        commodityDetailDTO.setStoreId(commodityInfo.getStoreId().toString());
        commodityDetailDTO.setCommodityName(commodityInfo.getCommodityName());
        commodityDetailDTO.setBrand(commodityInfo.getBrand());
        commodityDetailDTO.setPicSmall(commodityInfo.getPicSmall());
        commodityDetailDTO.setPicBig(commodityInfo.getPicBig());
        commodityDetailDTO.setFirstCategory(commodityInfo.getFirstCategory());
        commodityDetailDTO.setSecondCategory(commodityInfo.getSecondCategory());
        commodityDetailDTO.setThirdCategory(commodityInfo.getThirdCategory());
        commodityDetailDTO.setUseTimes(commodityInfo.getUseTimes());
        commodityDetailDTO.setProductionArea(commodityInfo.getProductionArea());
        commodityDetailDTO.setOldPrice(commodityInfo.getOldPrice());
        commodityDetailDTO.setCurrentPrice(commodityInfo.getCurrentPrice());
        commodityDetailDTO.setDescription(commodityInfo.getDescription());
        commodityDetailDTO.setStock(commodityInfo.getStock());
        commodityDetailDTO.setMonthlySales(commodityInfo.getMonthlySales());
        commodityDetailDTO.setAddToCarNumber(commodityInfo.getAddToCarNumber());
        commodityDetailDTO.setCollectNumber(commodityInfo.getCollectNumber());
        commodityDetailDTO.setGuarantee(commodityInfo.getGuarantee());
        commodityDetailDTO.setCreateTime(commodityInfo.getCreateTime());
        commodityDetailDTO.setStatus(commodityInfo.getStatus());
        commodityDetailDTO.setSpecification(commodityInfo.getSpecification());
        commodityDetailDTO.setCommentNumber(commodityInfo.getCommentNumber());
        commodityDetailDTO.setDefaultPostCharge(commodityInfo.getDefaultPostCharge());
        commodityDetailDTO.setSubjoinChargePerGood(commodityInfo.getSubjoinChargePerGood());
        commodityDetailDTO.setCoinCounteractNumber(commodityInfo.getCoinCounteractNumber());
        commodityDetailDTO.setFeedbackCoinNumber(commodityInfo.getFeedbackCoinNumber());
        return commodityDetailDTO;
    }

    public CommoditySummaryInfoRestDTO converterToCommoditySummaryInfoRestDTO(CommodityInfo commodityInfo) {
        CommoditySummaryInfoRestDTO summaryInfoRestDTO = new CommoditySummaryInfoRestDTO();
        summaryInfoRestDTO.setCommodityId(commodityInfo.getCommodityId().toString());
        summaryInfoRestDTO.setCommodityName(commodityInfo.getCommodityName());
        summaryInfoRestDTO.setPicSmall(commodityInfo.getPicSmall());
        summaryInfoRestDTO.setOldPrice(commodityInfo.getOldPrice());
        summaryInfoRestDTO.setCurrentPrice(commodityInfo.getCurrentPrice());
        summaryInfoRestDTO.setMonthlySales(commodityInfo.getMonthlySales());
        summaryInfoRestDTO.setAddToCarNumber(commodityInfo.getAddToCarNumber());
        summaryInfoRestDTO.setCollectNumber(commodityInfo.getCollectNumber());
        summaryInfoRestDTO.setGuarantee(commodityInfo.getGuarantee());
        summaryInfoRestDTO.setStatus(commodityInfo.getStatus());
        summaryInfoRestDTO.setCoinCounteractNumber(commodityInfo.getCoinCounteractNumber());
        summaryInfoRestDTO.setFeedbackCoinNumber(commodityInfo.getFeedbackCoinNumber());
        return summaryInfoRestDTO;
    }
}
