package com.sweetcat.api.rpcdto.storeinfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/7-16:40
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class StoreAddressInfoRpcDTO {
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

    public StoreAddressInfoRpcDTO(Long storeId) {
        this.storeId = storeId;
    }
}
