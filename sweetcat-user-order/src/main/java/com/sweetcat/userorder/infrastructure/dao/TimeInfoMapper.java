package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.domain.order.entity.TimeInfo;
import com.sweetcat.userorder.infrastructure.po.TimeInfoPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeInfoMapper {

    void addOne(TimeInfo timeInfo);

    void updateOne(TimeInfo timeInfo);

    void deleteOne(TimeInfo timeInfo);

    TimeInfoPO findOneByOrderId(Long orderId);
}