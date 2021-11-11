package com.sweetcat.footprint.interfaces.facade.restdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-18:12
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFootprintRestDTO {
    /**
     * 记录id
     */
    private String footprintId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品id
     */
    private String commodityId;

    /**
     * 商品正面图（small）
     */
    private String picSmall;

    /**
     * 浏览时的价格
     */
    private BigDecimal priceWhenBrowser;

    /**
     * 浏览开始时间
     */
    private Long startTime;

    /**
     * 浏览时长, 单位: ms
     */
    private Integer browserDuration;
}
