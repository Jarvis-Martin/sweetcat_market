package com.sweetcat.credit.domain.commodity.repository;

import com.sweetcat.credit.domain.commodity.entity.BaseCommodity;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/16-22:39
 * @version: 1.0
 */
public interface CommodityRepository {
    /**
     * 根据 marketItemId 查找 BaseCommodity
     * @param marketItemId
     * @return
     */
    BaseCommodity findOneMarketItemId(Long marketItemId);

    /**
     * 添加一件商品（各种商品共有的一部分数据）
     * @param commodity
     */
    void addOne(BaseCommodity commodity);

    /**
     * 查询所有商品的分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    List<BaseCommodity> findPage(Integer page, Integer limit);

    /**
     * 根据 商品分类查找商品分页数据
     *
     * @param commodityType
     * @param page
     * @param limit
     * @return
     */
    List<BaseCommodity> findPageByCommodityType(Integer commodityType, Integer page, Integer limit);

    /**
     * 保存 commodity 的变化
     * @param commodity
     */
    void save(BaseCommodity commodity);
}
