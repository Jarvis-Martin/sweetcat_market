package com.sweetcat.api.rpcdto.userorder;

import lombok.Data;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/7-13:44
 * @version: 1.0
 */
@Data
public class StoreAddressRpcDTO {
    private Long storeId;

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

    public StoreAddressRpcDTO(Long storeId, Long addressId) {
        this.storeId = storeId;
        this.addressId = addressId;
    }
}
