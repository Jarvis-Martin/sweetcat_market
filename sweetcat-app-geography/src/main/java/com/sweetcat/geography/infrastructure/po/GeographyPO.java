package com.sweetcat.geography.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_app_geography
 * @author 
 */
@Data
public class GeographyPO implements Serializable {
    /**
     * 地区码
     */
    private String addressCode;

    /**
     * 地区名
     */
    private String addressName;

    /**
     * 该地址对应的省码
     */
    private String provinceCode;

    /**
     * 该地址对应的市码
     */
    private String cityCode;

    /**
     * 该地址对应的区码
     */
    private String areaCode;

    /**
     * 该地址对应的县码
     */
    private String townCode;

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