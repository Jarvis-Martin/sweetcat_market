package com.sweetcat.credit.infrastructure.factory;

import com.sweetcat.credit.domain.commodity.entity.BaseCommodity;
import com.sweetcat.credit.domain.commodity.vo.Creator;
import com.sweetcat.credit.infrastructure.po.BaseCommodityPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/16-22:49
 * @version: 1.0
 */
@Component
public class CommodityFactory {
    public BaseCommodity create(BaseCommodityPO commodityInfoPO) {
        BaseCommodity commodity = new BaseCommodity(
                commodityInfoPO.getMarketItemId(),
                new Creator(commodityInfoPO.getCreatorId()),
                commodityInfoPO.getStock(),
                commodityInfoPO.getCreateTime(),
                commodityInfoPO.getUpdateTime(),
                commodityInfoPO.getCreditNumber(),
                commodityInfoPO.getCommodityType()
        );
        return commodity;
    }
}
