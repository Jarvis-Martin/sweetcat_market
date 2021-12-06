package com.sweetcat.storeorder.domain.order.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:10
 * @version: 1.0
 */
@Getter
public class TimeInfo implements Cloneable{
    private Long orderId;
    /**
     * 下单时间
     */
    private Long placeOrderTime;
    /**
     * 支付订单的时间
     */
    private Long payOrderTime;
    /**
     * 订单超时的时间
     */
    private Long timeOutTime;
    /**
     * 取消订单的时间(交易关闭)
     */
    private Long cancelOrderTime;
    /**
     * 订单(交易)完成时间
     */
    private Long finishOrderTime;
    /**
     * 发货时间
     */
    private Long deliverCommodityTime;
    /**
     * 收货时间
     */
    private Long receivedCommodityTime;

    public TimeInfo(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setPlaceOrderTime(Long placeOrderTime) {
        this.placeOrderTime = placeOrderTime;
    }

    public void setPayOrderTime(Long payOrderTime) {
        this.payOrderTime = payOrderTime;
    }

    public void setTimeOutTime(Long timeOutTime) {
        this.timeOutTime = timeOutTime;
    }

    public void setCancelOrderTime(Long cancelOrderTime) {
        this.cancelOrderTime = cancelOrderTime;
    }

    public void setFinishOrderTime(Long finishOrderTime) {
        this.finishOrderTime = finishOrderTime;
    }

    public void setDeliverCommodityTime(Long deliverCommodityTime) {
        this.deliverCommodityTime = deliverCommodityTime;
    }

    public void setReceivedCommodityTime(Long receivedCommodityTime) {
        this.receivedCommodityTime = receivedCommodityTime;
    }

    @Override
    public TimeInfo clone() throws CloneNotSupportedException {
        return ((TimeInfo) super.clone());
    }
}
