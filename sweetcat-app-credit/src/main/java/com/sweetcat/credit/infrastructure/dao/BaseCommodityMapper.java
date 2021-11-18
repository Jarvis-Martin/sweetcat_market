package com.sweetcat.credit.infrastructure.dao;

import com.sweetcat.credit.domain.commodity.entity.BaseCommodity;
import com.sweetcat.credit.infrastructure.po.BaseCommodityPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BaseCommodityMapper {

    /**
     * find commodity base info(commons info of all commodity belong credit market)
     * @param marketItemId
     * @return
     */
    BaseCommodityPO findOneByMarketItemId(Long marketItemId);

    /**
     * 在所有积分可兑换商品中查询分页数据
     * @param page
     * @param limit
     * @return
     */
    List<BaseCommodityPO> findPage(@Param("page") Integer page, @Param("limit") Integer limit);
    /**
     * 在所有积分可兑换商品中查询分页数据
     * @param commodityType
     * @param page
     * @param limit
     * @return
     */
    List<BaseCommodityPO> findPageByCommodityType(@Param("commodityType") Integer commodityType, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 添加一件商品（各种商品共有的一部分数据）
     * @param commodity
     */
    void addOne(BaseCommodity commodity);
}