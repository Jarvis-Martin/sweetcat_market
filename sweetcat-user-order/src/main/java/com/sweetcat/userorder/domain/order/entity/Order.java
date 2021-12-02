package com.sweetcat.userorder.domain.order.entity;

import lombok.Getter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:05
 * @version: 1.0
 */
@Getter
public class Order {
    /**
     * 未拆分订单
     */
    public static final Integer TYPE_UNSPLITED = 0;
    /**
     * 已拆分订单
     */
    public static final Integer TYPE_SPLITED = 1;
    public static final Integer STATUS_UNPAY = 0;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单类型；0未拆分订单；1已拆分订单
     */
    private Integer type;

    /**
     * 商品信息
     */
    private List<CommodityInfo> commodityInfoList;
    /**
     * 订单信息
     */
    private OrderInfo orderInfo;
    /**
     * 时间信息
     */
    private TimeInfo timeInfo;
    /**
     * 用户信息
     */
    private UserInfo userInfo;
    /**
     * 金额信息
     */
    private AmountInfo amountInfo;

    public Order(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setCommodityInfoList(List<CommodityInfo> commodityInfoList) {
        this.commodityInfoList = commodityInfoList;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public void setTimeInfo(TimeInfo timeInfo) {
        this.timeInfo = timeInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void setAmountInfo(AmountInfo amountInfo) {
        this.amountInfo = amountInfo;
    }
}
