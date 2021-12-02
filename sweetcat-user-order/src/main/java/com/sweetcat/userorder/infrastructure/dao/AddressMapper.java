package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.infrastructure.po.AddressPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
    void addOne(AddressPO timeInfo);

    void updateOne(AddressPO timeInfo);

    void deleteOne(AddressPO timeInfo);

    AddressPO findOneByOrderId(Long orderId);
}