package com.sweetcat.secondkill.infrastructure.dao;

import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;
import com.sweetcat.secondkill.infrastructure.po.SKCommodityPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SKCommodityMapper {
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
    void updateOne(SKCommodity commodity);

    /**
     * 查找给定时间所在区间内的秒杀商品分页数据
     * @param startTime
     * @param endTime
     * @param page
     * @param limit
     * @return
     */
    List<SKCommodityPO> findPageByTime(@Param("startTime") Long startTime, @Param("endTime") Long endTime, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 查找秒杀商品的详细信息
     * @param commodityId
     * @return
     */
    SKCommodityPO findOneByCommodityId(Long commodityId);

}