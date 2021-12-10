package com.sweetcat.takewayorder.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_store_address
 * @author 
 */
@Data
public class StoreAddressPO implements Serializable {
    /**
     * 拆分前订单编号
     */
    private Long orderId;
    /**
     * 拆分前订单编号
     */
    private Long storeId;

    /**
     * 收货地址 id
     */
    private Long addressId;

    /**
     * 地址所属用户的 id
     */
    private Long userId;

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

    private static final long serialVersionUID = 1L;
}