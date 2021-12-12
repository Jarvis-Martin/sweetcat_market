package com.sweetcat.commons.domainevent.userinfo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/11-9:51
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class UserChangedAddressEvent {
    private Long domainEventId;
    private Long occurOn;
    private Long userId;

    /**
     * 收货地址 id
     */
    private Long addressId;

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

    private String detailAddress;

    /**
     * 详细地址
     */

    public UserChangedAddressEvent(Long domainEventId) {
        this.domainEventId = domainEventId;
    }
}
