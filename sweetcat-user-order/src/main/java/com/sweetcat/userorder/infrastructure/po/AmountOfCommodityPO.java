package com.sweetcat.userorder.infrastructure.po;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * t_amount_of_commodity
 * @author 
 */
@Data
public class AmountOfCommodityPO implements Serializable {
    /**
     * 拆分前订单编号
     */
    private Long orderId;

    /**
     * 商品编号
     */
    private Long commodityId;

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