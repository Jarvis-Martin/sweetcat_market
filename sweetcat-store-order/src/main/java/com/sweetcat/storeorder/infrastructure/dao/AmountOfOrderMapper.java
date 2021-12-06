package com.sweetcat.storeorder.infrastructure.dao;

import com.sweetcat.storeorder.domain.order.entity.AmountInfoOfOrder;
import com.sweetcat.storeorder.infrastructure.po.AmountOfOrderPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AmountOfOrderMapper {
    void addOne(AmountInfoOfOrder amountOfCommodity);

    AmountOfOrderPO findOneByOrderId(Long orderId);

    void updateOne(AmountInfoOfOrder amountOfCommodity);
}