package com.sweetcat.user_info.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_user_address
 * @author 
 */
@Data
public class UserAddressPO implements Serializable {
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

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    private static final long serialVersionUID = 1L;
}