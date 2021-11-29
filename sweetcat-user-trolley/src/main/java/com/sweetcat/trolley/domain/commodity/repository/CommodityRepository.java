package com.sweetcat.trolley.domain.commodity.repository;

import com.sweetcat.trolley.domain.commodity.entity.Commodity;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-19:26
 * @version: 1.0
 */
public interface CommodityRepository {
    /**
     * 加入一条记录
     * @param commodity
     */
    void addOne(Commodity commodity);
    /**
     * 根据 key 找到商品信息
     * @param userId
     * @param commodityId
     */
    Commodity findOneByUserIdAndCommodityId(String userId, String commodityId);

    /**
     * 根据 key 找到商品信息
     * @param key
     */
    Commodity findOneBKey(String key);

    /**
     * 保持对 commodity 的修改
     * @param commodity
     */
    void save(Commodity commodity);
    /**
     * 移除一件商品
     * @param commodity
     */
    void removeOne(Commodity commodity);
}
