package com.sweetcat.takewayorder.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_store_info
 * @author 
 */
@Data
public class StoreInfoPO implements Serializable {
    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 商家id
     */
    private Long storeId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 店铺
     */
    private String storeLogo;

    private static final long serialVersionUID = 1L;
}