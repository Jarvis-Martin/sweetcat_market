package com.sweetcat.api.rpcdto.userorder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:10
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class TimeInfoRpcDTO implements Cloneable{
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

    public TimeInfoRpcDTO(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public TimeInfoRpcDTO clone() throws CloneNotSupportedException {
        return ((TimeInfoRpcDTO) super.clone());
    }
}
