package com.sweetcat.storecommodity.infrastructure.factory;

import com.sweetcat.commons.util.JSONUtils;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.CommodityInfo;
import com.sweetcat.storecommodity.infrastructure.po.CommodityInfoPO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-15:40
 * @Version: 1.0
 */
@Component
public class CommodityInfoFactory {

    public CommodityInfo create(CommodityInfoPO commodityInfoPO) {
        CommodityInfo commodityInfo = new CommodityInfo(
                commodityInfoPO.getCommodityId(),
                commodityInfoPO.getStoreId(),
                commodityInfoPO.getCommodityName(),
                commodityInfoPO.getBrand(),
                commodityInfoPO.getPicSmall(),
                commodityInfoPO.getPicBig(),
                commodityInfoPO.getFirstCategory(),
                commodityInfoPO.getSecondCategory(),
                commodityInfoPO.getThirdCategory(),
                commodityInfoPO.getUseTimes(),
                commodityInfoPO.getProductionArea(),
                commodityInfoPO.getOldPrice(),
                commodityInfoPO.getCurrentPrice(),
                commodityInfoPO.getDescription(),
                commodityInfoPO.getStock(),
                commodityInfoPO.getMonthlySales(),
                commodityInfoPO.getAddToCarNumber(),
                commodityInfoPO.getCollectNumber(),
                commodityInfoPO.getGuarantee(),
                commodityInfoPO.getCreateTime(),
                commodityInfoPO.getUpdateTime(),
                commodityInfoPO.getStatus(),
                commodityInfoPO.getSpecification(),
                commodityInfoPO.getCommentNumber(),
                commodityInfoPO.getDefaultPostCharge(),
                commodityInfoPO.getSubjoinChargePerGood(),
                commodityInfoPO.getCoinCounteractRate());
        return commodityInfo;
    }
}
