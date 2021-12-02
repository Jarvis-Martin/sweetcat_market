package com.sweetcat.userorder.domain.order.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:09
 * @version: 1.0
 */
@Getter
public class OrderInfo {
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 订单状态
     */
    private Integer orderStatus;

    public OrderInfo(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
