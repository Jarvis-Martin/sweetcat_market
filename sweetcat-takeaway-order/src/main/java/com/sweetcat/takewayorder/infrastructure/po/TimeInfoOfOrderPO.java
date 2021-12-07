package com.sweetcat.takewayorder.infrastructure.po;

import java.io.Serializable;
import lombok.Data;

/**
 * t_time_info_of_order
 * @author 
 */
@Data
public class TimeInfoOfOrderPO implements Serializable {
    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 下单时间
     */
    private Long placeOrderTime;

    /**
     * 支付时间
     */
    private Long payOrderTime;

    /**
     * 超时时间
     */
    private Long timeOutTime;

    /**
     * 取消订单时间
     */
    private Long cancelOrderTime;

    /**
     * 交易完成时间
     */
    private Long finishOrderTime;

    /**
     * 收货时间
     */
    private Long deliverCommodityTime;

    /**
     * 收货时间
     */
    private Long receivedCommodityTime;

    private static final long serialVersionUID = 1L;
}