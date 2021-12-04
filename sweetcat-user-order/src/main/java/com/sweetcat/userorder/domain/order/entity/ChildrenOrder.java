package com.sweetcat.userorder.domain.order.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-18:45
 * @version: 1.0
 */
@Getter
public class ChildrenOrder extends Order{
    /**
     * 订单拆分后，各个子订单对应的拆分前父订单的id
     */
    private Long parentOrderId;
    /**
     * 拆分后子订单的id
     */
    private Long childrenOrderId;
    private Integer orderStatus;
    private Long userId;

    public ChildrenOrder(Long orderId) {
        super(orderId);
        this.setParentOrderId(orderId);
    }

    public void setParentOrderId(Long parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    public void setChildrenOrderId(Long childrenOrderId) {
        this.childrenOrderId = childrenOrderId;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void cancelOrder(Long cancelTime) {
        this.orderStatus = Order.STATUS_CANCELED;
        // 记录订单取消时间
        TimeInfo timeInfo = super.getTimeInfo();
        timeInfo.setCancelOrderTime(cancelTime);
    }
}
