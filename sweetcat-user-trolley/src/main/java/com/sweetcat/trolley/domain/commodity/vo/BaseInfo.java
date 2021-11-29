package com.sweetcat.trolley.domain.commodity.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-19:07
 * @version: 1.0
 */
@Getter
@Setter
public class BaseInfo {
    /**
     * 商品的id
     */
    private Long commodityId;

    /**
     * 商品名
     */
    private String commodityName;

    /**
     * 商品规格
     */
    private String specification;

    /**
     * 加购时价格
     */
    private BigDecimal oldPrice;

    /**
     * 查询时价格
     */
    private BigDecimal currentPrice;

    /**
     * 商品正面图
     */
    private String picSmall;

    /**
     * 加购时间
     */
    private Long createTime;
}
