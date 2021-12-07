package com.sweetcat.takewayorder.domain.order.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-21:18
 * @version: 1.0
 */
@Getter
public class AddressInfoOfStore {
    private Long orderId;
    private Long storeId;
    /**
     * 收货地址 id
     */
    private Long addressId;

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

    public AddressInfoOfStore(Long orderId, Long storeId, Long addressId) {
        this.orderId = orderId;
        this.storeId = storeId;
        this.addressId = addressId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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
}
