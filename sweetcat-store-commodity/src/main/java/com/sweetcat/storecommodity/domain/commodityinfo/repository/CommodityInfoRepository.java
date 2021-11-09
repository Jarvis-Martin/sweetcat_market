package com.sweetcat.storecommodity.domain.commodityinfo.repository;

import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.CommodityInfo;

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
    CommodityInfo findByCommodityId(Long commodityId);

    /**
     * 根据 storeId 获取商品
     *
     * @param storeId storeId
     * @return CommodityInfo
     */
    List<CommodityInfo> findPageByStoreId(Long storeId, Integer page, Integer limit);


    /**
     * 查找新商家商品分页数据
     * @param page page
     * @param limit limit
     * @return
     */
    List<CommodityInfo> findPageNewCommodities(Integer page, Integer limit);

    /**
     * 添加
     *
     * @param commodityInfo commodityInfo
     */
    void addOne(CommodityInfo commodityInfo);
}
