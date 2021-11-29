package com.sweetcat.trolley.interfaces.facade.restdto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-22:02
 * @version: 1.0
 */
@Data
public class TrolleyCommodityRestDTO {

    /**
     * 加购数量
     */
    private Long count;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 购物车持有人id
     */
    private Long userId;

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

    public TrolleyCommodityRestDTO(Long userId) {
        this.userId = userId;
    }
}
