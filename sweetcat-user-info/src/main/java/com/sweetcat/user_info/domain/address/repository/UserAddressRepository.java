package com.sweetcat.user_info.domain.address.repository;

import com.sweetcat.user_info.domain.address.entity.UserAddress;
import com.sweetcat.user_info.infrastructure.po.UserAddressPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-14:59
 * @Version: 1.0
 */
public interface UserAddressRepository {
    /**
     * 根据 addressId 查找地址信息
     * @param addressId addressId
     * @return 地址信息 with addressId
     */
    UserAddress find(Long addressId);

    /**
     * 查找指定 userid 的默认地址
     * @param userId userId
     * @return 指定 userid 的默认地址
     */
    UserAddress findDefaultAddress(Long userId);

    /**
     * 查询分页 UserAddress 数据
     * @param page page
     * @param limit limit
     * @return 分页 UserAddress 数据
     */
    List<UserAddress> find(@Param("userId") Long userId, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 创建新 user address
     * @param address address
     */
    void add(UserAddress address);

    /**
     * 删除只当 userAddress
     * @param userAddress
     */
    void remove(UserAddress userAddress);

    /**
     * 保存 userAddress
     * @param userAddress
     * @return 保存后的 userAddress
     */
    UserAddress save(UserAddress userAddress);

}
