package com.sweetcat.user_info.interfaces;

import com.sweetcat.user_info.application.command.address.AddAddressCommand;
import com.sweetcat.user_info.application.command.address.EditAddressCommand;
import com.sweetcat.user_info.application.service.UserAddressApplicationService;
import com.sweetcat.user_info.domain.address.entity.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/31-20:55
 * @Version: 1.0
 */
@Component
public class UserAddressFacade {
    private UserAddressApplicationService userAddressApplicationService;

    @Autowired
    public void setUserAddressApplicationService(UserAddressApplicationService userAddressApplicationService) {
        this.userAddressApplicationService = userAddressApplicationService;
    }

    /**
     * 获得 收货地址的分页数据
     *
     * @param userId userId
     * @param page   page
     * @param limit  limit
     * @return 收货地址的分页数据
     */
    public List<UserAddress> getPage(Long userId, Integer page, Integer limit) {
        return this.userAddressApplicationService.getPage(userId, page, limit);
    }

    /**
     * 添加新收货地
     *
     * @param addAddressCommand addAddressCommand
     * @return 新收货地
     */
    public UserAddress addAddress(AddAddressCommand addAddressCommand) {
        return this.userAddressApplicationService.addAddress(addAddressCommand);
    }

    /**
     * 根据 addressId 查找地址信息
     *
     * @param addressId addressId
     * @return 地址信息 with addressId
     */
    public UserAddress findAddressById(Long addressId) {
        return userAddressApplicationService.findAddressById(addressId);
    }

    /**
     * 移除 user_id=userId and address_id=addressId 的 收货地址
     *
     * @param userId    userId
     * @param addressId addressId
     */
    public void deleteAddress(Long userId, Long addressId) {
        userAddressApplicationService.deleteAddressById(userId, addressId);
    }


    /**
     * 设置为默认地址
     *
     * @param userId    userId
     * @param addressId addressId
     */
    public void change2DefaultAddress(Long userId, Long addressId) {
        this.userAddressApplicationService.change2DefaultAddress(userId, addressId);
    }

    /**
     * 设置为默认地址
     *
     * @param userId    userId
     * @param addressId addressId
     */
    public void change2NotDefaultAddress(Long userId, Long addressId) {
        this.userAddressApplicationService.change2NotDefaultAddress(userId, addressId);
    }

    /**
     * 编辑地址信息
     *
     * @param editAddressCommand editAddressCommand
     */
    public void editAddress(EditAddressCommand editAddressCommand) {
        this.userAddressApplicationService.editAddress(editAddressCommand);
    }

}
