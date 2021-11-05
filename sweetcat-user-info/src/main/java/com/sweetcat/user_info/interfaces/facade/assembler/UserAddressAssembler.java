package com.sweetcat.user_info.interfaces.facade.assembler;

import com.sweetcat.user_info.domain.address.entity.UserAddress;
import com.sweetcat.user_info.interfaces.facade.restdto.UserAddressDTO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-18:41
 * @Version: 1.0
 */
@Component
public class UserAddressAssembler {
    public UserAddressDTO converter2UserAddressDTO(UserAddress userAddress) {
        UserAddressDTO userAddressDTO = new UserAddressDTO();
        userAddressDTO.setAddressId(userAddress.getAddressId().toString());
        userAddressDTO.setUserId(userAddress.getUserId().toString());
        userAddressDTO.setReceiverName(userAddress.getReceiverName());
        userAddressDTO.setReceiverPhone(userAddress.getReceiverPhone());
        userAddressDTO.setProvinceName(userAddress.getProvinceName());
        userAddressDTO.setCityName(userAddress.getCityName());
        userAddressDTO.setAreaName(userAddress.getAreaName());
        userAddressDTO.setTownName(userAddress.getTownName());
        userAddressDTO.setDetailAddress(userAddress.getDetailAddress());
        userAddressDTO.setDefaultAddress(userAddress.getDefaultAddress());
        userAddressDTO.setCreateTime(userAddress.getCreateTime());
        userAddressDTO.setUpdateTime(userAddress.getUpdateTime());
        return userAddressDTO;
    }
}
