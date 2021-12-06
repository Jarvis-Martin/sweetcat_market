package com.sweetcat.api.rpcdto.storeorder;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/2-20:34
 * @version: 1.0
 */
@Data
public class CommodityInfoRpcDTO {
    /**
     * 商品id
     */
    private Long commodityId;
    /**
     * 商品名
     */
    private String commodityName;
    /**
     * 商品主图
     */
    private String commodityPicSmall;
    /**
     * 购买的商品规格
     */
    private String specification;
    /**
     * 商品购买时价格
     */
    private BigDecimal currentPrice;
    /**
     * 购买数量
     */
    private Integer count;

    public CommodityInfoRpcDTO(Long commodityId) {
        this.commodityId = commodityId;
    }
}
