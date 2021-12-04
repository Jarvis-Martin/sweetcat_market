package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.domain.order.entity.AmountInfo;
import com.sweetcat.userorder.infrastructure.po.AmountOfOrderPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AmountOfOrderMapper {
    void addOne(AmountInfo timeInfo);

    void updateOne(AmountInfo timeInfo);

    void deleteOne(AmountInfo timeInfo);

    AmountOfOrderPO findOneByOrderId(Long orderId);
}