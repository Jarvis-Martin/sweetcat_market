package com.sweetcat.takewayorder.application.command;

import lombok.Data;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/7-20:26
 * @version: 1.0
 */
@Data
public class TimeInfoOfAddOrderCommand {
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
}
