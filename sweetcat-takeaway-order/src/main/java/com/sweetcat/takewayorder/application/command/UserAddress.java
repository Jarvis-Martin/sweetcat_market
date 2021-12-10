package com.sweetcat.takewayorder.application.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:26
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class UserAddress implements Cloneable{
    private Long orderId;
    private Long userId;
    /**
     * 收货地址 id
     */
    private Long addressId;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人手机号
     */
    private String receiverPhone;

    /**
     * 省名
     */
    private String provinceName;

    /**
     * 市名
     */
    private String cityName;

    /**
     * 区名
     */
    private String areaName;

    /**
     * 街道名
     */
    private String townName;

    /**
     * 详细地址
     */
    private String detailAddress;

    public UserAddress(Long orderId, Long userId, Long addressId) {
        this.orderId = orderId;
        this.userId = userId;
        this.addressId = addressId;
    }


    @Override
    public UserAddress clone() throws CloneNotSupportedException {
        return ((UserAddress) super.clone());
    }
}
