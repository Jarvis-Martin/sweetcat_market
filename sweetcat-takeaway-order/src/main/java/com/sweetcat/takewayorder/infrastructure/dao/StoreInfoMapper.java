package com.sweetcat.takewayorder.infrastructure.dao;

import com.sweetcat.takewayorder.domain.order.entity.StoreInfo;
import com.sweetcat.takewayorder.infrastructure.po.StoreInfoPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreInfoMapper {
    void addOne(StoreInfo storeInfo);

    void updateOne(StoreInfo storeInfo);

    StoreInfoPO findOneByOrderId(Long orderId);
}