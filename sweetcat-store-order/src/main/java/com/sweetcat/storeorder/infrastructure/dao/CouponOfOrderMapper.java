package com.sweetcat.storeorder.infrastructure.dao;

import com.sweetcat.storeorder.domain.order.entity.CouponOfOrder;
import com.sweetcat.storeorder.infrastructure.po.CouponOfOrderPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponOfOrderMapper {

    void addOne(CouponOfOrder amountOfCommodity);

    List<CouponOfOrderPO> findAllByOrderId(Long orderId);

    CouponOfOrderPO findOneByOrderId(Long orderId);

    void updateOne(CouponOfOrder amountOfCommodity);
}