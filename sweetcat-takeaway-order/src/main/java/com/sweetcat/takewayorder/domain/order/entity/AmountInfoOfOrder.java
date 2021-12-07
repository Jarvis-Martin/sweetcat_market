package com.sweetcat.takewayorder.domain.order.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @date: 2021-11-2021/11/30-17:11
 * @version: 1.0
 * @description: 记录整个订单的金额信息，
 * {@link CommodityInfo} 中的 {@link AmountInfoOfCommodity} 包含对应单类商品涉及的金额信息
 */
@Data
public class AmountInfoOfOrder {
    private Long orderId;
    /**
     * 实际支付金额,订单拆分前操作前，(父)订单的总应付金额
     */
    private BigDecimal priceOfPayment;
    /**
     * 实际支付金额,订单拆分前操作前，(父)订单所有商品总金额(不含任何优惠金额扣减)
     */
    private BigDecimal priceOfCommodity;

    public AmountInfoOfOrder(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setPriceOfPayment(BigDecimal priceOfPayment) {
        this.priceOfPayment = priceOfPayment;
    }

    public void setPriceOfCommodity(BigDecimal priceOfCommodity) {
        this.priceOfCommodity = priceOfCommodity;
    }

}
