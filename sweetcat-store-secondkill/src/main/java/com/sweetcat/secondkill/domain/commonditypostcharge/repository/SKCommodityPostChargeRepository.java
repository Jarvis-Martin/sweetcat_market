package com.sweetcat.secondkill.domain.commonditypostcharge.repository;

import com.sweetcat.secondkill.domain.commonditypostcharge.entity.SKCommodityPostCharge;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-13:00
 * @version: 1.0
 */
public interface SKCommodityPostChargeRepository {
    /**
     * 添加一个记录
     * @param charge charge
     */
    void addOne(SKCommodityPostCharge charge);

    /**
     * find post charge recorde by commodityId and provinceCode
     *
     * @param commodityId  commodityId
     * @param provinceCode provinceCode
     * @return
     */
    SKCommodityPostCharge find(Long commodityId, String provinceCode);


    /**
     * find postcharge by postChargeId
     *
     * @param postChargeId postChargeId
     * @return
     */
    SKCommodityPostCharge findByPostChargeId(Long postChargeId);

    /**
     * remove post charge recorde by commodity id and province code
     *
     * @param charge
     */
    void remove(SKCommodityPostCharge charge);
}
