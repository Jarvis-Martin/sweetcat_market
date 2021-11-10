package com.sweetcat.storecommodity.infrastructure.factory;

import com.sweetcat.storecommodity.domain.commonditypostcharge.entity.CommodityPostCharge;
import com.sweetcat.storecommodity.infrastructure.po.CommodityPostChargePO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-13:22
 * @version: 1.0
 */
@Component
public class CommodityPostChargeFactory {
    public CommodityPostCharge create(CommodityPostChargePO postChargePO) {
        CommodityPostCharge postCharge = new CommodityPostCharge(
                postChargePO.getChargeId(), postChargePO.getCommodityId(),
                postChargePO.getStoreId(), postChargePO.getProvinceCode());
        postCharge.setPostCharge(postChargePO.getPostCharge());
        postCharge.setCreateTime(postChargePO.getCreateTime());
        postCharge.setUpdateTime(postChargePO.getUpdateTime());
        return postCharge;
    }
}
