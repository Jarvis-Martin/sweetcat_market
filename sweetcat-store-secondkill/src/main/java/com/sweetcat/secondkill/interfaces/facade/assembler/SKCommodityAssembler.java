package com.sweetcat.secondkill.interfaces.facade.assembler;

import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;
import com.sweetcat.secondkill.interfaces.facade.restdto.SKCommodityDetailDTO;
import com.sweetcat.secondkill.interfaces.facade.restdto.SKCommoditySummaryInfoRestDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/27-17:32
 * @version: 1.0
 */
@Component
public class SKCommodityAssembler {
    public SKCommoditySummaryInfoRestDTO converterToSKCommoditySummaryInfoRestDTO(SKCommodity skCommodity) {
        SKCommoditySummaryInfoRestDTO skCommoditySummaryInfoRestDTO = new SKCommoditySummaryInfoRestDTO(skCommodity.getCommodityName());
        inflateSKCommoditySummaryInfoRestDTO(skCommodity, skCommoditySummaryInfoRestDTO);
        return skCommoditySummaryInfoRestDTO;
    }

    private void inflateSKCommoditySummaryInfoRestDTO(SKCommodity skCommodity, SKCommoditySummaryInfoRestDTO skCommoditySummaryInfoRestDTO) {
        skCommoditySummaryInfoRestDTO.setCommodityName(skCommodity.getCommodityName());
        skCommoditySummaryInfoRestDTO.setPicSmall(skCommodity.getPicSmall());
        skCommoditySummaryInfoRestDTO.setOldPrice(skCommodity.getOldPrice());
        skCommoditySummaryInfoRestDTO.setCurrentPrice(skCommodity.getCurrentPrice());
        skCommoditySummaryInfoRestDTO.setMonthlySales(skCommodity.getMonthlySales());
        skCommoditySummaryInfoRestDTO.setAddToCarNumber(skCommodity.getAddToCarNumber());
        skCommoditySummaryInfoRestDTO.setCollectNumber(skCommodity.getCollectNumber());
        skCommoditySummaryInfoRestDTO.setGuarantee(skCommodity.getGuarantee());
        skCommoditySummaryInfoRestDTO.setStatus(skCommodity.getStatus());
        skCommoditySummaryInfoRestDTO.setStartTime(skCommodity.getStartTime());
        skCommoditySummaryInfoRestDTO.setRemainStock(skCommodity.getRemainStock());
    }

    public SKCommodityDetailDTO converterToSKCommodityDetailDTO(SKCommodity skCommodity) {
        SKCommodityDetailDTO skCommodityDetailDTO = new SKCommodityDetailDTO(skCommodity.getCommodityId().toString());
        inflateSKCommodityDetailDTO(skCommodity, skCommodityDetailDTO);
        return skCommodityDetailDTO;
    }

    private void inflateSKCommodityDetailDTO(SKCommodity skCommodity, SKCommodityDetailDTO skCommodityDetailDTO) {
        skCommodityDetailDTO.setStoreId(skCommodity.getStore().getStoreId().toString());
        skCommodityDetailDTO.setCommodityName(skCommodity.getCommodityName());
        skCommodityDetailDTO.setBrand(skCommodity.getBrand());
        skCommodityDetailDTO.setPicSmall(skCommodity.getPicSmall());
        skCommodityDetailDTO.setPicBig(skCommodity.getPicBig());
        skCommodityDetailDTO.setFirstCategory(skCommodity.getFirstCategory());
        skCommodityDetailDTO.setSecondCategory(skCommodity.getSecondCategory());
        skCommodityDetailDTO.setThirdCategory(skCommodity.getThirdCategory());
        skCommodityDetailDTO.setUseTimes(skCommodity.getUseTimes());
        skCommodityDetailDTO.setOldPrice(skCommodity.getOldPrice());
        skCommodityDetailDTO.setProductionArea(skCommodity.getProductionArea());
        skCommodityDetailDTO.setOldPrice(skCommodity.getOldPrice());
        skCommodityDetailDTO.setCurrentPrice(skCommodity.getCurrentPrice());
        skCommodityDetailDTO.setDescription(skCommodity.getDescription());
        skCommodityDetailDTO.setTotalStock(skCommodity.getTotalStock());
        skCommodityDetailDTO.setMonthlySales(skCommodity.getMonthlySales());
        skCommodityDetailDTO.setAddToCarNumber(skCommodity.getAddToCarNumber());
        skCommodityDetailDTO.setCollectNumber(skCommodity.getCollectNumber());
        skCommodityDetailDTO.setGuarantee(skCommodity.getGuarantee());
        skCommodityDetailDTO.setCreateTime(skCommodity.getCreateTime());
        skCommodityDetailDTO.setStatus(skCommodity.getStatus());
        skCommodityDetailDTO.setSpecification(skCommodity.getSpecification());
        skCommodityDetailDTO.setCommentNumber(skCommodity.getCommentNumber());
        skCommodityDetailDTO.setDefaultPostCharge(skCommodity.getDefaultPostCharge());
        skCommodityDetailDTO.setSubjoinChargePerGood(skCommodity.getSubjoinChargePerGood());
        skCommodityDetailDTO.setStartTime(skCommodity.getStartTime());
        skCommodityDetailDTO.setRemainStock(skCommodity.getRemainStock());
    }
}
