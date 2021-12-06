package com.sweetcat.storeorder.infrastructure.dao;

import com.sweetcat.storeorder.domain.order.entity.CommodityInfo;
import com.sweetcat.storeorder.infrastructure.po.CommodityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityMapper {

    void addOne(CommodityInfo amountOfCommodityPO);

    List<CommodityPO> findAllByOrderId(Long orderId);

    void updateOne(CommodityInfo amountOfCommodityPO);
}