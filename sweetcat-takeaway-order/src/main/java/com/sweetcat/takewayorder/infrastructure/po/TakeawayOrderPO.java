package com.sweetcat.takewayorder.infrastructure.po;

import java.io.Serializable;
import lombok.Data;

/**
 * t_takeaway_order
 * @author 
 */
@Data
public class TakeawayOrderPO implements Serializable {
    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 订单状态.0：未支付；1：待发货；2：待收货；3：待评价（交易成功）；4：已取消
     */
    private Integer orderStaus;

    /**
     * 订单所属用户编号
     */
    private Long userId;

    /**
     * 商家id
     */
    private Long storeId;

    private static final long serialVersionUID = 1L;
}