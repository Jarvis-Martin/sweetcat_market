package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.infrastructure.po.AmountOfOrderPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AmountOfOrderMapper {
    void addOne(AmountOfOrderPO timeInfo);

    void updateOne(AmountOfOrderPO timeInfo);

    void deleteOne(AmountOfOrderPO timeInfo);

    AmountOfOrderPO findOneByOrderId(Long orderId);
}