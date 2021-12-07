package com.sweetcat.api.rpcdto.userorder;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:10
 * @version: 1.0
 */
@Getter
public class UserInfoRpcDTO implements Cloneable{
    /**
     * 下单用户id
     */
    private Long userId;
    /**
     * 下单用户选择的收货地址信息
     */
    private AddressInfoRpcDTO addressInfoRpcDTO;

    public UserInfoRpcDTO(Long userId) {
        this.userId = userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAddressInfoRpcDTO(AddressInfoRpcDTO addressInfoRpcDTO) {
        this.addressInfoRpcDTO = addressInfoRpcDTO;
    }

    @Override
    public UserInfoRpcDTO clone() throws CloneNotSupportedException {
        return ((UserInfoRpcDTO) super.clone());
    }
}
