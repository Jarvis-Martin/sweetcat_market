package com.sweetcat.takewayorder.infrastructure.dao;

import com.sweetcat.takewayorder.domain.order.entity.TimeInfo;
import com.sweetcat.takewayorder.infrastructure.po.TimeInfoOfOrderPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeInfoMapper {
    void addOne(TimeInfo amountInfoOfOrder);

    void updateOne(TimeInfo amountInfoOfOrder);

    TimeInfoOfOrderPO findOneByOrderId(Long orderId);
}