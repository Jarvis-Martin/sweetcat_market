package com.sweetcat.storeorder.infrastructure.dao;

import com.sweetcat.storeorder.domain.order.entity.CouponOfCommodity;
import com.sweetcat.storeorder.infrastructure.po.CouponOfCommodityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponOfCommodityMapper {

    void addOne(CouponOfCommodity amountOfCommodity);

    List<CouponOfCommodityPO> findAllByOrderId(Long orderId);

    CouponOfCommodityPO findOneByOrderId(Long orderId);

    void updateOne(CouponOfCommodity amountOfCommodity);
}