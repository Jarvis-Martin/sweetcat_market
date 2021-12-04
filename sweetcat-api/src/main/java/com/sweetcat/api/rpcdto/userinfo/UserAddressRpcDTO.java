package com.sweetcat.api.rpcdto.userinfo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/1-16:02
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class UserAddressRpcDTO {
    /**
     * 收货地址 id
     */
    private Long addressId;

    /**
     * 地址所属用户的 id
     */
    private Long userId;

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

    /**
     * 默认地址？0：是默认地址；1：非默认地址,用户首次添加的地址设置为默认地址
     */
    private Integer defaultAddress;

    public UserAddressRpcDTO(Long addressId) {
        this.addressId = addressId;
    }
}
