package com.sweetcat.takewayorder.infrastructure.po;

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
     * 订单编号
     */
    private Long orderId;

    /**
     * 商品id
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

    private static final long serialVersionUID = 1L;
}