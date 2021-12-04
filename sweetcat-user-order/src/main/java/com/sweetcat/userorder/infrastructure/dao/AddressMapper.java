package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.domain.order.entity.AddressInfo;
import com.sweetcat.userorder.infrastructure.po.AddressPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
    void addOne(AddressInfo timeInfo);

    void updateOne(AddressInfo timeInfo);

    void deleteOne(AddressInfo timeInfo);

    AddressPO findOneByOrderId(Long orderId);
}