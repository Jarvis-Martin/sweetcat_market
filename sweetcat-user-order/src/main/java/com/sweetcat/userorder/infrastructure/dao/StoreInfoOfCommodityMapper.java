package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.infrastructure.po.StoreInfoOfCommodityPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreInfoOfCommodityMapper {

    void addOne(StoreInfoOfCommodityPO timeInfo);

    void updateOne(StoreInfoOfCommodityPO timeInfo);

    void deleteOne(StoreInfoOfCommodityPO timeInfo);

    StoreInfoOfCommodityPO findOneByOrderId(Long orderId);
}