package com.sweetcat.takewayorder.application.command;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/1-13:04
 * @version: 1.0
 */
@Data
public class Commodity {
    /**
     * 商品id
     */
    private Long commodityId;
    /**
     * 规格信息
     */
    private String specification;
    /**
     * 该商品购买的数量
     */
    private Integer count;

    /**
     * 对于当前商品，用户实际实际支付金额
     */
    private BigDecimal priceOfPayment;
    /**
     * 商品总价: 注意，这里是商品单价
     */
    private BigDecimal priceOfCommodity;
}
