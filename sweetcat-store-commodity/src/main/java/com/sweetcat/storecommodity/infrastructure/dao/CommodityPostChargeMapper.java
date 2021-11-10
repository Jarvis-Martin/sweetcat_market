package com.sweetcat.storecommodity.infrastructure.dao;

import com.sweetcat.storecommodity.domain.commonditypostcharge.entity.CommodityPostCharge;
import com.sweetcat.storecommodity.infrastructure.po.CommodityPostChargePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommodityPostChargeMapper {
    /**
     * find post charge recorde by commodityId and provinceCode
     *
     * @param commodityId  commodityId
     * @param provinceCode provinceCode
     * @return
     */
    CommodityPostChargePO find(@Param("commodityId") Long commodityId, @Param("provinceCode") String provinceCode);

    /**
     * remove post charge recorde by commodity id and province code
     *
     * @param charge
     */
    void delete(CommodityPostCharge charge);

    /**
     * find post charge recorde by chargeId
     *
     * @param chargeId chargeId
     * @return
     */
    CommodityPostChargePO findByPostChargeId(Long chargeId);

    /**
     * 添加一个记录
     *
     * @param charge charge
     */
    void addOne(CommodityPostCharge charge);
}