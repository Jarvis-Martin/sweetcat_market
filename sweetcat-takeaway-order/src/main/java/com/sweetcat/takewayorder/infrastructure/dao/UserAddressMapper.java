package com.sweetcat.takewayorder.infrastructure.dao;

import com.sweetcat.takewayorder.domain.order.entity.AddressInfoOfUser;
import com.sweetcat.takewayorder.infrastructure.po.StoreInfoPO;
import com.sweetcat.takewayorder.infrastructure.po.UserAddressPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAddressMapper {
    void addOne(AddressInfoOfUser amountInfoOfOrder);

    void updateOne(AddressInfoOfUser amountInfoOfOrder);

    UserAddressPO findOneByOrderId(Long orderId);
}