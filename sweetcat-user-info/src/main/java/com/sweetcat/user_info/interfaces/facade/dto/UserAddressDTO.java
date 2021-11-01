package com.sweetcat.user_info.interfaces.facade.dto;

import lombok.Data;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-18:41
 * @Version: 1.0
 */
@Data
public class UserAddressDTO {
    /**
     * 收货地址 id
     */
    private String addressId;

    /**
     * 地址所属用户的 id
     */
    private String userId;

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

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}
