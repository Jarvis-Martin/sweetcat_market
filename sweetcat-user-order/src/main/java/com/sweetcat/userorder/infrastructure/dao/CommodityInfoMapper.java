package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.domain.order.entity.CommodityInfo;
import com.sweetcat.userorder.infrastructure.po.CommodityInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommodityInfoMapper {
    void addOne(CommodityInfo timeInfo);

    void updateOne(CommodityInfo timeInfo);

    void deleteOne(CommodityInfo timeInfo);

    List<CommodityInfoPO> findPageByOrderId(@Param("orderId") Long orderId, @Param("page") Integer page, @Param("limit") Integer limit);

    List<CommodityInfoPO> findAllByOrderId(@Param("orderId") Long orderId);
}