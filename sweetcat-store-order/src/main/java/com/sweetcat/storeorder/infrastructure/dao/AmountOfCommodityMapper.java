package com.sweetcat.storeorder.infrastructure.dao;

import com.sweetcat.storeorder.domain.order.entity.AmountInfoOfCommodity;
import com.sweetcat.storeorder.infrastructure.po.AmountOfCommodityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AmountOfCommodityMapper {
    void addOne(AmountInfoOfCommodity amountOfCommodity);

    AmountOfCommodityPO findOneByOrderId(Long orderId);

    void updateOne(AmountInfoOfCommodity amountOfCommodity);

    List<AmountOfCommodityPO> findAllByOrderId(Long orderId);
}