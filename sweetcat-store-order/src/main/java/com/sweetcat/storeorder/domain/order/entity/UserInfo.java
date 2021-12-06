package com.sweetcat.storeorder.domain.order.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:10
 * @version: 1.0
 */
@Getter
public class UserInfo implements Cloneable{
    /**
     * 下单用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userAvatar;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    @Override
    public UserInfo clone() throws CloneNotSupportedException {
        return ((UserInfo) super.clone());
    }
}
