package com.sweetcat.storeorder.infrastructure.dao;

import com.sweetcat.storeorder.domain.order.entity.Order;
import com.sweetcat.storeorder.infrastructure.po.OrderPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    void addOne(Order amountOfCommodity);

    OrderPO findOneByOrderId(Long orderId);

    List<OrderPO> findPageByCustomerId(@Param("customerId") Long customerId, @Param("page") Integer page, @Param("limit") Integer limit);

    void updateOne(Order amountOfCommodity);
}