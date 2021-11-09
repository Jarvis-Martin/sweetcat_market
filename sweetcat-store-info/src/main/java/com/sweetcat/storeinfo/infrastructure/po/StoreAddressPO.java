package com.sweetcat.storeinfo.infrastructure.po;

import java.io.Serializable;
import lombok.Data;

/**
 * t_store_address
 * @author 
 */
@Data
public class StoreAddressPO implements Serializable {
    /**
     * 商家编号
     */
    private Long storeId;

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

    /**
     * 修改时间
     */
    private Long updateTime;

    private static final long serialVersionUID = 1L;
}