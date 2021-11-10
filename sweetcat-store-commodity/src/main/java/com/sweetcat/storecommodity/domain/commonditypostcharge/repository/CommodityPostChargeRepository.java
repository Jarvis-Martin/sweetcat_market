package com.sweetcat.storecommodity.domain.commonditypostcharge.repository;

import com.sweetcat.storecommodity.domain.commonditypostcharge.entity.CommodityPostCharge;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-13:00
 * @version: 1.0
 */
public interface CommodityPostChargeRepository {
    /**
     * 添加一个记录
     * @param charge charge
     */
    void addOne(CommodityPostCharge charge);

    /**
     * find post charge recorde by commodityId and provinceCode
     *
     * @param commodityId  commodityId
     * @param provinceCode provinceCode
     * @return
     */
    CommodityPostCharge find(Long commodityId, String provinceCode);


    /**
     * find postcharge by postChargeId
     *
     * @param postChargeId postChargeId
     * @return
     */
    CommodityPostCharge findByPostChargeId(Long postChargeId);

    /**
     * remove post charge recorde by commodity id and province code
     *
     * @param charge
     */
    void remove(CommodityPostCharge charge);
}
