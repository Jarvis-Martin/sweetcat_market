package com.sweetcat.storecommodity.domain.commodityinfo.repository;

import com.sweetcat.storecommodity.domain.commodityinfo.entity.Commodity;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-22:37
 * @Version: 1.0
 */
public interface CommodityInfoRepository {
    /**
     * 根据 commodityId 获取商品信息
     *
     * @param commodityId commodityId
     * @return CommodityInfo
     */
    Commodity findByCommodityId(Long commodityId);

    /**
     * 根据 storeId 获取商品
     *
     * @param storeId storeId
     * @return CommodityInfo
     */
    List<Commodity> findPageByStoreId(Long storeId, Integer page, Integer limit);


    /**
     * 查找新商家商品分页数据
     * @param page page
     * @param limit limit
     * @return
     */
    List<Commodity> findPageNewCommodities(Integer page, Integer limit);

    /**
     * 添加
     *
     * @param commodity commodityInfo
     */
    void addOne(Commodity commodity);

    /**
     * 查找积分可抵扣部分金额的商品
     * @param page
     * @param limit
     * @return
     */
    List<Commodity> findPageCreditCanOffsetAPart(Integer page, Integer limit);

    /**
     * 保存 commodity 的修改
     * @param commodity
     */
    void save(Commodity commodity);
}
