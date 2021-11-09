package com.sweetcat.storecommodity.infrastructure.dao;

import com.sweetcat.storecommodity.infrastructure.po.CommodityPostChargePO;

public interface CommodityPostChargeMapper {
    int deleteByPrimaryKey(Long chargeId);

    int insert(CommodityPostChargePO record);

    int insertSelective(CommodityPostChargePO record);

    CommodityPostChargePO selectByPrimaryKey(Long chargeId);

    int updateByPrimaryKeySelective(CommodityPostChargePO record);

    int updateByPrimaryKey(CommodityPostChargePO record);
}