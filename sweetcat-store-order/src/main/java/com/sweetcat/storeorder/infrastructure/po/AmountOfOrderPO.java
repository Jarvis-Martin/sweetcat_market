package com.sweetcat.storeorder.infrastructure.po;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * t_amount_of_order
 * @author 
 */
@Data
public class AmountOfOrderPO implements Serializable {
    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 实际待支付金额
     */
    private BigDecimal priceOfPayment;

    /**
     * 商品实际金额
     */
    private BigDecimal priceOfCommodity;

    /**
     * 整个订单消耗的积分数量
     */
    private Integer credit;

    private static final long serialVersionUID = 1L;
}