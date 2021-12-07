package com.sweetcat.takewayorder.infrastructure.dao;

import com.sweetcat.takewayorder.domain.order.entity.CommodityInfo;
import com.sweetcat.takewayorder.infrastructure.po.CommodityOfOrderPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityOfOrderMapper {
    void addOne(CommodityInfo amountInfoOfOrder);

    void updateOne(CommodityInfo amountInfoOfOrder);

    List<CommodityOfOrderPO> findAllByOrderId(Long orderId);
}