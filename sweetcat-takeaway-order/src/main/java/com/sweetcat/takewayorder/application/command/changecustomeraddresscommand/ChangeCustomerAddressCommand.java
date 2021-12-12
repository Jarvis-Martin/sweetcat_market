package com.sweetcat.takewayorder.application.command.changecustomeraddresscommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/11-10:04
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ChangeCustomerAddressCommand {
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

    /**
     * 详细地址
     */
    private String detailAddress;

    public ChangeCustomerAddressCommand(Long userId, Long addressId) {
        this.userId = userId;
        this.addressId = addressId;
    }
}
