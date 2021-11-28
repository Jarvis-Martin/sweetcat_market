package com.sweetcat.secondkill.infrastructure.dao;

import com.sweetcat.secondkill.domain.commonditypostcharge.entity.SKCommodityPostCharge;
import com.sweetcat.secondkill.infrastructure.po.SKCommodityPostChargePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SKCommodityPostChargeMapper {
    SKCommodityPostChargePO findOneByPostChargeId(Long postChargeId);

    void addOne(SKCommodityPostCharge charge);

    SKCommodityPostChargePO findOne(Long commodityId, String provinceCode);

    void deleteOne(SKCommodityPostCharge charge);
}