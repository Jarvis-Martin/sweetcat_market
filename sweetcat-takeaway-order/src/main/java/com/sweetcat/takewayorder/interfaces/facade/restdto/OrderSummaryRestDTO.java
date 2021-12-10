package com.sweetcat.takewayorder.interfaces.facade.restdto;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-23:25
 * @version: 1.0
 */

import lombok.Data;

import java.util.List;

@Data
public class OrderSummaryRestDTO {

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 订单所含商品列表
     */
    private List<CommodityInfoRestDTO> commodityInfos;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 订单金额信息
     */
    private AmountInfoRestDTO amountInfo;
    /**
     * 根据当前订单状态，设置指定时间
     * 如：
     * 未支付状态 - 提交订单时间
     * 已支付状态 - 支付时间
     * 已发货    - 发货时间
     * ...
     */
    private Long time;
    /**
     * 订单所属店家信息
     */
    private StoreRestDTO storeInfo;

    public OrderSummaryRestDTO(Long orderId) {
        this.orderId = orderId;
    }
}
