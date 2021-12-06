package com.sweetcat.storeorder.infrastructure.dao;

import com.sweetcat.storeorder.domain.order.entity.TimeInfo;
import com.sweetcat.storeorder.infrastructure.po.TimeInfoPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeInfoMapper {

    void addOne(TimeInfo amountOfCommodity);

    TimeInfoPO findOneByOrderId(Long orderId);

    void updateOne(TimeInfo amountOfCommodity);
}