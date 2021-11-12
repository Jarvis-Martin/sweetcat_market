package com.sweetcat.footprint.infrastructure.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_user_footprint
 * @author 
 */
@Data
public class UserFootprintPO implements Serializable {
    /**
     * 记录id
     */
    private Long footprintId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long commodityId;

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

    private static final long serialVersionUID = 1L;
}