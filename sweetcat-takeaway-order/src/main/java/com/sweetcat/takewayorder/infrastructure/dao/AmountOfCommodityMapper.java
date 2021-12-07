package com.sweetcat.takewayorder.infrastructure.dao;

import com.sweetcat.takewayorder.domain.order.entity.AmountInfoOfCommodity;
import com.sweetcat.takewayorder.infrastructure.po.AmountOfCommodityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AmountOfCommodityMapper {
    void addOne(AmountInfoOfCommodity amountInfoOfCommodity);

    void updateOne(AmountInfoOfCommodity amountInfoOfCommodity);

    List<AmountOfCommodityPO> findAll(Long orderId);
}