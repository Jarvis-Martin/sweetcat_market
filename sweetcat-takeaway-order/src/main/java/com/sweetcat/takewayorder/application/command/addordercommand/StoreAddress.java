package com.sweetcat.takewayorder.application.command.addordercommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-21:18
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class StoreAddress {
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

    public StoreAddress(Long orderId, Long storeId, Long addressId) {
        this.orderId = orderId;
        this.storeId = storeId;
        this.addressId = addressId;
    }
}
