package com.sweetcat.storecommodity.infrastructure.dao;

import com.sweetcat.storecommodity.domain.commodityinfo.entity.CommodityInfo;
import com.sweetcat.storecommodity.infrastructure.po.CommodityInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommodityInfoMapper {
    /**
     * 根据 commodityId 获取商品信息
     *
     * @param commodityId commodityId
     * @return CommodityInfo
     */
    CommodityInfoPO findByCommodityId(Long commodityId);

    /**
     * 根据 storeId 获取商品
     *
     * @param storeId storeId
     * @return CommodityInfo
     */
    List<CommodityInfoPO> findPageByStoreId(@Param("storeId") Long storeId, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 添加
     *
     * @param commodityInfo commodityInfo
     */
    void addOne(CommodityInfo commodityInfo);

    /**
     * 查找新商家商品分页数据
     *
     * @param page  page
     * @param limit limit
     * @return
     */
    List<CommodityInfoPO> findPageNewCommodities(@Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 查找积分可抵扣部分金额的商品
     * @param page
     * @param limit
     * @return
     */
    List<CommodityInfoPO> findPageCreditCanOffsetAPart(Integer page, Integer limit);
}