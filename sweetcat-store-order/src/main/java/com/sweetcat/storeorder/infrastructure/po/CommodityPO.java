package com.sweetcat.storeorder.infrastructure.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_commodity_of_order
 * @author 
 */
@Data
public class CommodityPO implements Serializable {
    /**
     * 订单编号
     */
    private Long orderId;

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
     * 下单时价格
     */
    private BigDecimal priceWhenMakeOrder;

    /**
     * 购买的商品规格
     */
    private String specification;

    /**
     * 购买的数量
     */
    private Integer count;

    private static final long serialVersionUID = 1L;
}