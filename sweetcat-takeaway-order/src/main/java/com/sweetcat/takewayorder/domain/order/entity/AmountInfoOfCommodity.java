package com.sweetcat.takewayorder.domain.order.entity;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-19:22
 * @version: 1.0
 */
@Getter
public class AmountInfoOfCommodity implements Cloneable{
    private Long orderId;
    private Long commodityId;
    /**
     * 实际支付金额
     */
    private BigDecimal priceOfPayment;
    /**
     * 商品总价
     */
    private BigDecimal priceOfCommodity;

    public AmountInfoOfCommodity(Long orderId, Long commodityId) {
        this.orderId = orderId;
        this.commodityId = commodityId;
    }

    public void setPriceOfPayment(BigDecimal priceOfPayment) {
        this.priceOfPayment = priceOfPayment;
    }

    public void setPriceOfCommodity(BigDecimal priceOfCommodity) {
        this.priceOfCommodity = priceOfCommodity;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    public AmountInfoOfCommodity clone() throws CloneNotSupportedException {
        return ((AmountInfoOfCommodity) super.clone());
    }
}
