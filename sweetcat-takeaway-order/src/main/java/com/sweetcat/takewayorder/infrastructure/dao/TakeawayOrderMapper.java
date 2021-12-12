package com.sweetcat.takewayorder.infrastructure.dao;

import com.sweetcat.takewayorder.domain.order.entity.Order;
import com.sweetcat.takewayorder.infrastructure.po.TakeawayOrderPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TakeawayOrderMapper {
    void addOne(Order order);

    void updateOne(Order order);

    TakeawayOrderPO findOne(Long orderId);

    List<TakeawayOrderPO> findPage(@Param("page") Integer page, @Param("limit") Integer limit);

    List<TakeawayOrderPO> findAllByUserIdAndAddressId(@Param("userId") Long userId);
}