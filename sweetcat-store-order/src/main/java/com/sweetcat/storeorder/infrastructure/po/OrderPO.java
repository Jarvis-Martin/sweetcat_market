package com.sweetcat.storeorder.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_store_order
 * @author 
 */
@Data
public class OrderPO implements Serializable {
    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 订单状态.0：未支付；1：待发货；2：待收货；3：待评价（交易成功）；4：已取消
     */
    private Integer orderStatus;

    /**
     * 订单所属用户编号
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}