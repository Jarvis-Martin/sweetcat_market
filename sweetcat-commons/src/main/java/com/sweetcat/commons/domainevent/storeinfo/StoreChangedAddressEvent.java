package com.sweetcat.commons.domainevent.storeinfo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/11-9:54
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class StoreChangedAddressEvent {
    private Long domainEventId;

    private Long occurOne;

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

    /**
     * 修改时间
     */
    private Long updateTime;

    public StoreChangedAddressEvent(Long domainEventId) {
        this.domainEventId = domainEventId;
    }
}
