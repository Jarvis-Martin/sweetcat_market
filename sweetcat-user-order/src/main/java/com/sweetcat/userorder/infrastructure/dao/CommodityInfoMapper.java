package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.infrastructure.po.CommodityInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommodityInfoMapper {
    void addOne(CommodityInfoPO timeInfo);

    void updateOne(CommodityInfoPO timeInfo);

    void deleteOne(CommodityInfoPO timeInfo);

    List<CommodityInfoPO> findPageByOrderId(@Param("orderId") Long orderId, @Param("page") Integer page, @Param("limit") Integer limit);

    List<CommodityInfoPO> findAllByOrderId(@Param("orderId") Long orderId);
}