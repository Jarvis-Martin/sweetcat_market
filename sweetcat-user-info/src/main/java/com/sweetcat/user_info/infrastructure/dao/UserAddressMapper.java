package com.sweetcat.user_info.infrastructure.dao;

import com.sweetcat.user_info.domain.address.entity.UserAddress;
import com.sweetcat.user_info.infrastructure.po.UserAddressPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAddressMapper {
    /**
     * 根据 addressId 获得地址信息
     * @param  addressId
     * @return 地址信息 with addressId
     */
    UserAddressPO findOne(Long addressId);

    /**
     * 查找指定 userid 的默认地址
     * @param userId userId
     * @return 指定 userid 的默认地址
     */
    UserAddressPO findDefaultAddress(Long userId);

    /**
     * 查找分页数据
     * @param userId userId
     * @param page page
     * @param limit limit
     * @return 分页数据
     */
    List<UserAddressPO> getPage(@Param("userId") Long userId, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 新添加一条 address 记录
     * @param address address
     */
    void addOne(UserAddress address);

    /**
     * 移除指定的 userAddress
     * @param userAddress userAddress
     */
    void remove(UserAddress userAddress);

    /**
     * 更新 userAddress
     * @param userAddress
     */
    void update(UserAddress userAddress);
}