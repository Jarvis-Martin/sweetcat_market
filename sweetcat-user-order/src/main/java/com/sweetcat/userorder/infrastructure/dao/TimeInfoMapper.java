package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.infrastructure.po.TimeInfoPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeInfoMapper {

    void addOne(TimeInfoPO timeInfo);

    void updateOne(TimeInfoPO timeInfo);

    void deleteOne(TimeInfoPO timeInfo);

    TimeInfoPO findOneByOrderId(Long orderId);
}