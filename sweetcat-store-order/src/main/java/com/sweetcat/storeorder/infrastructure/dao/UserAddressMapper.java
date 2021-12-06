package com.sweetcat.storeorder.infrastructure.dao;

import com.sweetcat.storeorder.domain.order.entity.AddressInfo;
import com.sweetcat.storeorder.infrastructure.po.UserAddressPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.tomcat.jni.Address;

@Mapper
public interface UserAddressMapper {

    void addOne(AddressInfo amountOfCommodity);

    UserAddressPO findOneByOrderId(Long orderId);

    void updateOne(AddressInfo amountOfCommodity);
}