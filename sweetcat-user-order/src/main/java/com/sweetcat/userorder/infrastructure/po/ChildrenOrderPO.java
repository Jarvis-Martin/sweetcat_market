package com.sweetcat.userorder.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_children_order
 * @author 
 */
@Data
public class ChildrenOrderPO implements Serializable  {
    /**
     * 拆分前父订单编号
     */
    private Long parentOrderId;

    /**
     * 拆分后子订单编号
     */
    private Long childrenOrderId;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 下单用户id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}