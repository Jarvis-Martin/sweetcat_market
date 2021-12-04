package com.sweetcat.userorder.domain.order.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:26
 * @version: 1.0
 */
@Getter
public class AddressInfo implements Cloneable{
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

    public AddressInfo(Long orderId, Long addressId) {
        this.setOrderId(orderId);
        this.addressId = addressId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public AddressInfo clone() throws CloneNotSupportedException {
        return ((AddressInfo) super.clone());
    }
}
