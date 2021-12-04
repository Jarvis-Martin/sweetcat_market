package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.domain.order.entity.AmountInfoOfCommodity;
import com.sweetcat.userorder.infrastructure.po.AmountOfCommodityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AmountOfCommodityMapper {
    void addOne(AmountInfoOfCommodity timeInfo);

    void updateOne(AmountInfoOfCommodity timeInfo);

    void deleteOne(AmountInfoOfCommodity timeInfo);

    List<AmountOfCommodityPO> findAllByOrderId(Long orderId);
}