package com.sweetcat.user_info.application.service;

import com.sweetcat.user_info.application.command.address.AddAddressCommand;
import com.sweetcat.user_info.application.command.address.EditAddressCommand;
import com.sweetcat.user_info.domain.address.entity.UserAddress;
import com.sweetcat.user_info.domain.address.repository.UserAddressRepository;
import com.sweetcat.user_info.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.user_info.infrastructure.service.phone_format_verfiy_service.VerifyPhoneFormatService;
import com.sweetcat.user_info.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-19:06
 * @Version: 1.0
 */
@Service
public class UserAddressApplicationService {
    @Value("${default-address-limit}")
    private Integer defaultAddressLimit;

    private UserAddressRepository userAddressRepository;
    private SnowFlakeService snowFlakeService;
    private VerifyPhoneFormatService verifyPhoneFormatService;
    private VerifyIdFormatService verifyIdFormatService;

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setVerifyPhoneFormatService(VerifyPhoneFormatService verifyPhoneFormatService) {
        this.verifyPhoneFormatService = verifyPhoneFormatService;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setUserAddressRepository(UserAddressRepository userAddressRepository) {
        this.userAddressRepository = userAddressRepository;
    }

    /**
     * 获得收货地址分页数据
     *
     * @param userId userId
     * @param page   page
     * @param limit  limit
     * @return 收货地址分页数据
     */
    @Transactional
    public List<UserAddress> getPage(Long userId, Integer page, Integer limit) {
        // userId 参数格式错误
        this.verifyIdFormatService.verifyId(userId);
        // page 参数调整
        page = page == null || page < 0 ? 0 : page * limit;
        // limit 参数调整
        limit = limit == null || limit <= 0 ? defaultAddressLimit : limit;
        return this.userAddressRepository.find(userId, page, limit);
    }

    /**
     * 添加一个新 userAddress
     *
     * @param addAddressCommand addAddressCommand
     * @return 新建的 userAddress
     */
    @Transactional
    public UserAddress addAddress(AddAddressCommand addAddressCommand) {
        // 检查新添加的收货地址 收货人手机号是否合法
        this.verifyPhoneFormatService.verifyPhoneFormat(addAddressCommand.getReceiverPhone());
        // 生成新地址的 addressId
        long addressId = this.snowFlakeService.snowflakeId();
        // 雪花算法生成 id，并新建 userAddress
        UserAddress userAddress = new UserAddress(
                addressId,
                addAddressCommand.getUserId(),
                addAddressCommand.getReceiverName(),
                addAddressCommand.getReceiverPhone(),
                addAddressCommand.getProvinceName(),
                addAddressCommand.getCityName(),
                addAddressCommand.getAreaName(),
                addAddressCommand.getTownName(),
                addAddressCommand.getDetailAddress(),
                addAddressCommand.getDefaultAddress(),
                addAddressCommand.getCreateTime(),
                addAddressCommand.getCreateTime()
        );
        // 如果新添加地址为默认地址
        if (userAddress.isDefault()) {
            // 找到原默认地址
            UserAddress defaultAddress = userAddressRepository.findDefaultAddress(userAddress.getUserId());
            // 如果存在原默认地址
            if (defaultAddress != null) {
                // 修改原默认地址为非默认地址
                defaultAddress.changeToNotDefaultAddress();
                // 保存非默认地址的修改
                userAddressRepository.save(defaultAddress);
            }
        }
        // insert 到 db 中
        userAddressRepository.add(userAddress);
        return userAddress;
    }

    /**
     * 根据 addressId 查找地址信息
     *
     * @param addressId addressId
     * @return 地址信息 with addressId
     */
    @Transactional
    public UserAddress findAddressById(Long addressId) {
        // 参数检查 addressId，不合法时通知用户参数格式错误
        this.verifyIdFormatService.verifyId(addressId);
        return userAddressRepository.find(addressId);
    }

    /**
     * 移除 user_id=userId and address_id=addressId 的 收货地址
     *
     * @param userId    userId
     * @param addressId addressId
     */
    @Transactional
    public void deleteAddressById(Long userId, Long addressId) {
        // 参数检查 addressId，不合法时通知用户参数格式错误
        this.verifyIdFormatService.verifyId(userId, addressId);
        // 找到待删除的地址
        UserAddress userAddress = userAddressRepository.find(addressId);
        // 如果 userAddress 不存在，则不予以理会
        if (userAddress == null) {
            return;
        }
        // 移除
        userAddressRepository.remove(userAddress);
    }

    /**
     * 设置为默认地址
     *
     * @param userId    userId
     * @param addressId addressId
     */
    @Transactional
    public void change2DefaultAddress(Long userId, Long addressId) {
        // userId,addressId 检查
        this.verifyIdFormatService.verifyId(userId, addressId);
        // 找到原默认地址
        UserAddress defaultAddress = userAddressRepository.findDefaultAddress(userId);
        if (defaultAddress != null) {
            // 修改原默认地址为非默认地址
            defaultAddress.changeToNotDefaultAddress();
            // 保存非默认地址的修改
            userAddressRepository.save(defaultAddress);
        }
        // 根据 addressId 找到待修改的 address 信息
        UserAddress userAddress = userAddressRepository.find(addressId);
        // 设置为默认地址
        userAddress.changeToDefaultAddress();
        // 存回 repository
        userAddressRepository.save(userAddress);
    }

    /**
     * 设置为默认地址
     *
     * @param userId    userId
     * @param addressId addressId
     */
    @Transactional
    public void change2NotDefaultAddress(Long userId, Long addressId) {
        // userId,addressId 检查
        this.verifyIdFormatService.verifyId(userId, addressId);
        // 根据 addressId 找到对应的 address 信息
        UserAddress userAddress = userAddressRepository.find(addressId);
        // 设置为非默认地址
        userAddress.changeToNotDefaultAddress();
        // 存回 repository
        userAddressRepository.save(userAddress);
    }

    /**
     * 编辑地址信息
     *
     * @param editAddressCommand editAddressCommand
     */
    @Transactional
    public void editAddress(EditAddressCommand editAddressCommand) {
        Long addressId = editAddressCommand.getAddressId();
        Long userId = editAddressCommand.getUserId();
        // userId,addressId 检查
        this.verifyIdFormatService.verifyId(userId, addressId);
        // 验证手机号
        this.verifyPhoneFormatService.verifyPhoneFormat(editAddressCommand.getReceiverPhone());
        // command 转 DO（UserAddress）
        UserAddress newUserAddress = new UserAddress(
                addressId,
                userId,
                editAddressCommand.getReceiverName(),
                editAddressCommand.getReceiverPhone(),
                editAddressCommand.getProvinceName(),
                editAddressCommand.getCityName(),
                editAddressCommand.getAreaName(),
                editAddressCommand.getTownName(),
                editAddressCommand.getDetailAddress(),
                editAddressCommand.getDefaultAddress(),
                null,
                editAddressCommand.getUpdateTime()
        );
        // 找到待修改address
        UserAddress userAddress = userAddressRepository.find(newUserAddress.getAddressId());
        // 更新
        userAddress.update(newUserAddress);
        // 如果新地址为 默认地址，找到原默认地址并修改为非默认地址
        if (newUserAddress.isDefault()) {
            // 找到默认地址
            UserAddress defaultAddress = userAddressRepository.findDefaultAddress(userId);
            // 存在默认地址时
            if (defaultAddress != null) {
                // 修改原默认地址为非默认地址
                defaultAddress.changeToNotDefaultAddress();
                // 保存非默认地址的修改
                userAddressRepository.save(defaultAddress);
            }
        }
        // 保存
        userAddressRepository.save(userAddress);
    }


}
