package com.sweetcat.secondkill.infrastructure.factory;

import com.sweetcat.secondkill.domain.commodity.vo.Store;
import com.sweetcat.secondkill.domain.commonditypostcharge.entity.SKCommodityPostCharge;
import com.sweetcat.secondkill.infrastructure.po.SKCommodityPostChargePO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-13:22
 * @version: 1.0
 */
@Component
public class SKCommodityPostChargeFactory {
    public SKCommodityPostCharge create(SKCommodityPostChargePO postChargePO) {
        SKCommodityPostCharge postCharge = new SKCommodityPostCharge(postChargePO.getChargeId(), postChargePO.getCommodityId());
        Store store = new Store(postChargePO.getStoreId());
        postCharge.setStore(store);
        postCharge.setProvinceCode(postChargePO.getProvinceCode());
        postCharge.setPostCharge(postChargePO.getPostCharge());
        postCharge.setCreateTime(postChargePO.getCreateTime());
        postCharge.setUpdateTime(postChargePO.getUpdateTime());
        return postCharge;
    }
}
