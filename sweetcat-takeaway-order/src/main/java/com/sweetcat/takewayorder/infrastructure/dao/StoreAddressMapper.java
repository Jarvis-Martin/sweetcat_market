package com.sweetcat.takewayorder.infrastructure.dao;

import com.sweetcat.takewayorder.domain.order.entity.AddressInfoOfStore;
import com.sweetcat.takewayorder.infrastructure.po.StoreAddressPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreAddressMapper {
    void addOne(AddressInfoOfStore addressInfoOfStore);

    void updateOne(AddressInfoOfStore addressInfoOfStore);

    StoreAddressPO findOneByOrderId(Long orderId);
}