package com.sweetcat.storeinfo.application.commmand;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/13-11:44
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class AddStoreAddressCommand {
    private Long storeId;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String townName;
    private String detailAddress;
    private Long createTime;
}
