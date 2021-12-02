package com.sweetcat.userorder.domain.order.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:10
 * @version: 1.0
 */
@Getter
public class UserInfo {
    /**
     * 下单用户id
     */
    private Long userId;
    /**
     * 下单用户选择的收货地址信息
     */
    private AddressInfo addressInfo;

    public UserInfo(Long userId) {
        this.userId = userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }
}
