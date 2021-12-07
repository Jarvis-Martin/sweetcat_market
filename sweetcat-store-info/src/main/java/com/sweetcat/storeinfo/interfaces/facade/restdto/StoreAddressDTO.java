package com.sweetcat.storeinfo.interfaces.facade.restdto;

import lombok.Data;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-20:03
 * @Version: 1.0
 */
@Data
public class StoreAddressDTO {
    /**
     * 商家编号
     */
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

    /**
     * 创建时间
     */
    private Long createTime;

    public StoreAddressDTO(Long storeId, String provinceName, String cityName, String areaName, String townName, String detailAddress, Long createTime) {
        this.storeId = storeId;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.areaName = areaName;
        this.townName = townName;
        this.detailAddress = detailAddress;
        this.createTime = createTime;
    }

    public StoreAddressDTO(Long storeId, Long addressId) {
        this.storeId = storeId;
        this.addressId = addressId;
    }
}
