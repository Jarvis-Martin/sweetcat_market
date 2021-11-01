package com.sweetcat.user_info.infrastructure.factory;

import com.sweetcat.user_info.domain.address.entity.UserAddress;
import com.sweetcat.user_info.infrastructure.po.UserAddressPO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-15:33
 * @Version: 1.0
 */
@Component
public class UserAddressFactory {
    public UserAddress create(UserAddressPO userAddressPO) {
        return new UserAddress(
                userAddressPO.getAddressId(),
                userAddressPO.getUserId(),
                userAddressPO.getReceiverName(),
                userAddressPO.getReceiverPhone(),
                userAddressPO.getProvinceName(),
                userAddressPO.getCityName(),
                userAddressPO.getAreaName(),
                userAddressPO.getTownName(),
                userAddressPO.getDetailAddress(),
                userAddressPO.getDefaultAddress(),
                userAddressPO.getCreateTime(),
                userAddressPO.getUpdateTime()
        );
    }
}
