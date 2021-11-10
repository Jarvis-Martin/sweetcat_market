package com.sweetcat.geography.interfaces.facade.restdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-15:57
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeographyRestDTO {
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
}
