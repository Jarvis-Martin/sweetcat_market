package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.infrastructure.po.AmountOfCommodityPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AmountOfCommodityMapper {
    void addOne(AmountOfCommodityPO timeInfo);

    void updateOne(AmountOfCommodityPO timeInfo);

    void deleteOne(AmountOfCommodityPO timeInfo);

    AmountOfCommodityPO findOneByOrderId(Long orderId);
}