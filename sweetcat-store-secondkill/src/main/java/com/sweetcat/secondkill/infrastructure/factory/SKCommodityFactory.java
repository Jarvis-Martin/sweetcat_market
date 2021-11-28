package com.sweetcat.secondkill.infrastructure.factory;

import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;
import com.sweetcat.secondkill.domain.commodity.vo.Store;
import com.sweetcat.secondkill.infrastructure.po.SKCommodityPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/27-16:11
 * @version: 1.0
 */
@Component
public class SKCommodityFactory {
    public SKCommodity create(SKCommodityPO commodityPO) {
        SKCommodity skCommodity = new SKCommodity(commodityPO.getCommodityId());
        Store store = new Store(commodityPO.getCommodityId());
        inflateSKCommodity(commodityPO, skCommodity, store);
        return skCommodity;
    }

    private void inflateSKCommodity(SKCommodityPO commodityPO, SKCommodity skCommodity, Store store) {
        skCommodity.setStore(store);
        skCommodity.setCommodityName(commodityPO.getCommodityName());
        skCommodity.setBrand(commodityPO.getBrand());
        skCommodity.setPicSmall(commodityPO.getPicSmall());
        skCommodity.setPicBig(commodityPO.getPicBig());
        skCommodity.setFirstCategory(commodityPO.getFirstCategory());
        skCommodity.setSecondCategory(commodityPO.getSecondCategory());
        skCommodity.setThirdCategory(commodityPO.getThirdCategory());
        skCommodity.setUseTimes(commodityPO.getUseTimes());
        skCommodity.setProductionArea(commodityPO.getProductionArea());
        skCommodity.setOldPrice(commodityPO.getOldPrice());
        skCommodity.setCurrentPrice(commodityPO.getCurrentPrice());
        skCommodity.setDescription(commodityPO.getDescription());
        skCommodity.setTotalStock(commodityPO.getTotalStock());
        skCommodity.setMonthlySales(commodityPO.getMonthlySales());
        skCommodity.setAddToCarNumber(commodityPO.getAddToCarNumber());
        skCommodity.setCollectNumber(commodityPO.getCollectNumber());
        skCommodity.setGuarantee(commodityPO.getGuarantee());
        skCommodity.setCreateTime(commodityPO.getCreateTime());
        skCommodity.setUpdateTime(commodityPO.getUpdateTime());
        skCommodity.setStatus(commodityPO.getStatus());
        skCommodity.setSpecification(commodityPO.getSpecification());
        skCommodity.setCommentNumber(commodityPO.getCommentNumber());
        skCommodity.setDefaultPostCharge(commodityPO.getDefaultPostCharge());
        skCommodity.setSubjoinChargePerGood(commodityPO.getSubjoinChargePerGood());
        skCommodity.setStartTime(commodityPO.getStartTime());
        skCommodity.setRemainStock(commodityPO.getRemainStock());
    }
}
