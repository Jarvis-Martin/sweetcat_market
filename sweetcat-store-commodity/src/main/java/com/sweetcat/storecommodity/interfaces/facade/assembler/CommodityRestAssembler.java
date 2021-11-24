package com.sweetcat.storecommodity.interfaces.facade.assembler;

import com.sweetcat.storecommodity.domain.commodityinfo.entity.Commodity;
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
    public CommodityDetailDTO converterToCommodityInfoDTO(Commodity commodity) {
        CommodityDetailDTO commodityDetailDTO = new CommodityDetailDTO();
        commodityDetailDTO.setCommodityId(commodity.getCommodityId().toString());
        commodityDetailDTO.setStoreId(commodity.getStoreId().toString());
        commodityDetailDTO.setCommodityName(commodity.getCommodityName());
        commodityDetailDTO.setBrand(commodity.getBrand());
        commodityDetailDTO.setPicSmall(commodity.getPicSmall());
        commodityDetailDTO.setPicBig(commodity.getPicBig());
        commodityDetailDTO.setFirstCategory(commodity.getFirstCategory());
        commodityDetailDTO.setSecondCategory(commodity.getSecondCategory());
        commodityDetailDTO.setThirdCategory(commodity.getThirdCategory());
        commodityDetailDTO.setUseTimes(commodity.getUseTimes());
        commodityDetailDTO.setProductionArea(commodity.getProductionArea());
        commodityDetailDTO.setOldPrice(commodity.getOldPrice());
        commodityDetailDTO.setCurrentPrice(commodity.getCurrentPrice());
        commodityDetailDTO.setDescription(commodity.getDescription());
        commodityDetailDTO.setStock(commodity.getStock());
        commodityDetailDTO.setMonthlySales(commodity.getMonthlySales());
        commodityDetailDTO.setAddToCarNumber(commodity.getAddToCarNumber());
        commodityDetailDTO.setCollectNumber(commodity.getCollectNumber());
        commodityDetailDTO.setGuarantee(commodity.getGuarantee());
        commodityDetailDTO.setCreateTime(commodity.getCreateTime());
        commodityDetailDTO.setStatus(commodity.getStatus());
        commodityDetailDTO.setSpecification(commodity.getSpecification());
        commodityDetailDTO.setCommentNumber(commodity.getCommentNumber());
        commodityDetailDTO.setDefaultPostCharge(commodity.getDefaultPostCharge());
        commodityDetailDTO.setSubjoinChargePerGood(commodity.getSubjoinChargePerGood());
        commodityDetailDTO.setCoinCounteractNumber(commodity.getCoinCounteractNumber());
        commodityDetailDTO.setFeedbackCoinNumber(commodity.getFeedbackCoinNumber());
        return commodityDetailDTO;
    }

    public CommoditySummaryInfoRestDTO converterToCommoditySummaryInfoRestDTO(Commodity commodity) {
        CommoditySummaryInfoRestDTO summaryInfoRestDTO = new CommoditySummaryInfoRestDTO();
        summaryInfoRestDTO.setCommodityId(commodity.getCommodityId().toString());
        summaryInfoRestDTO.setCommodityName(commodity.getCommodityName());
        summaryInfoRestDTO.setPicSmall(commodity.getPicSmall());
        summaryInfoRestDTO.setOldPrice(commodity.getOldPrice());
        summaryInfoRestDTO.setCurrentPrice(commodity.getCurrentPrice());
        summaryInfoRestDTO.setMonthlySales(commodity.getMonthlySales());
        summaryInfoRestDTO.setAddToCarNumber(commodity.getAddToCarNumber());
        summaryInfoRestDTO.setCollectNumber(commodity.getCollectNumber());
        summaryInfoRestDTO.setGuarantee(commodity.getGuarantee());
        summaryInfoRestDTO.setStatus(commodity.getStatus());
        summaryInfoRestDTO.setCoinCounteractNumber(commodity.getCoinCounteractNumber());
        summaryInfoRestDTO.setFeedbackCoinNumber(commodity.getFeedbackCoinNumber());
        return summaryInfoRestDTO;
    }
}
