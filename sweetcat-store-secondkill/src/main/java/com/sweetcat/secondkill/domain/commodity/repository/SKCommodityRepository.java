package com.sweetcat.secondkill.domain.commodity.repository;

import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/27-13:28
 * @version: 1.0
 */
public interface SKCommodityRepository {
    /**
     * 增加一件商品记录
     * @param commodity
     */
    void addOne(SKCommodity commodity);

    /**
     * 移除秒杀商品
     * @param commodity
     */
    void removeOne(SKCommodity commodity);

    /**
     * 保持对秒杀商品的修改
     * @param commodity
     */
    void save(SKCommodity commodity);

    /**
     * 查找给定时间所在区间内的秒杀商品分页数据
     * @param currentTimeStamp
     * @param page
     * @param limit
     * @return
     */
    List<SKCommodity> findPageByTime(Long currentTimeStamp, Integer page, Integer limit);

    /**
     * 查找秒杀商品的详细信息
     * @param commodityId
     * @return
     */
    SKCommodity findOneByCommodityId(Long commodityId);

}
