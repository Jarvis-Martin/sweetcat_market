package com.sweetcat.user_info.interfaces.facade.assembler;

import com.sweetcat.user_info.domain.address.entity.UserAddress;
import com.sweetcat.api.rpcdto.userinfo.UserAddressRpcDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/1-19:18
 * @version: 1.0
 */
@Component
public class UserAddressRpcAssembler {
    public UserAddressRpcDTO converter2UserAddressRpcDTO(UserAddress userAddress) {
        UserAddressRpcDTO userAddressDTO = new UserAddressRpcDTO(userAddress.getAddressId());
        userAddressDTO.setUserId(userAddress.getUserId());
        userAddressDTO.setReceiverName(userAddress.getReceiverName());
        userAddressDTO.setReceiverPhone(userAddress.getReceiverPhone());
        userAddressDTO.setProvinceName(userAddress.getProvinceName());
        userAddressDTO.setCityName(userAddress.getCityName());
        userAddressDTO.setAreaName(userAddress.getAreaName());
        userAddressDTO.setTownName(userAddress.getTownName());
        userAddressDTO.setDetailAddress(userAddress.getDetailAddress());
        userAddressDTO.setDefaultAddress(userAddress.getDefaultAddress());
        return userAddressDTO;
    }
}
