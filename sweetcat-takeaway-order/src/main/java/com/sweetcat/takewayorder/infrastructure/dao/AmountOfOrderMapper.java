package com.sweetcat.takewayorder.infrastructure.dao;

import com.sweetcat.takewayorder.domain.order.entity.AmountInfoOfOrder;
import com.sweetcat.takewayorder.infrastructure.po.AmountOfOrderPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AmountOfOrderMapper {
    void addOne(AmountInfoOfOrder amountInfoOfOrder);

    void updateOne(AmountInfoOfOrder amountInfoOfOrder);

    AmountOfOrderPO findOneByOrderId(Long orderId);
}