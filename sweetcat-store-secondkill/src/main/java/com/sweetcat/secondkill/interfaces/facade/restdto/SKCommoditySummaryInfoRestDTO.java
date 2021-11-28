package com.sweetcat.secondkill.interfaces.facade.restdto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-17:25
 * @version: 1.0
 */
@Data
public class SKCommoditySummaryInfoRestDTO {
    /**
     * 商品od
     */
    private String commodityId;

    /**
     * 商品名
     */
    private String commodityName;

    /**
     * 商品正面图（small）
     */
    private String picSmall;

    /**
     * 原价
     */
    private BigDecimal oldPrice;

    /**
     * 现价
     */
    private BigDecimal currentPrice;

    /**
     * 月销量
     */
    private Integer monthlySales;

    /**
     * 加购数
     */
    private Integer addToCarNumber;

    /**
     * 收藏数
     */
    private Integer collectNumber;

    /**
     * 保障
     */
    private String guarantee;

    /**
     * 商品状态.0： 待审核; 1： 审核通过; 2： 审核未通过; 3：已下架
     */
    private Integer status;

    /**
     * 该商品秒杀开始时间
     */
    private Long startTime;

    /**
     * 商品剩余库存
     */
    private Long remainStock;

    public SKCommoditySummaryInfoRestDTO(String commodityId) {
        this.commodityId = commodityId;
    }
}
