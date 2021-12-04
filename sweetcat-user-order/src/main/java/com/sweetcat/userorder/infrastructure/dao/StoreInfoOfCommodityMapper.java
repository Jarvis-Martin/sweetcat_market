package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.domain.order.entity.StoreInfo;
import com.sweetcat.userorder.infrastructure.po.StoreInfoOfCommodityPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreInfoOfCommodityMapper {

    void addOne(StoreInfo timeInfo);

    void updateOne(StoreInfo timeInfo);

    void deleteOne(StoreInfo timeInfo);

    StoreInfoOfCommodityPO findOneByOrderId(Long orderId);
}