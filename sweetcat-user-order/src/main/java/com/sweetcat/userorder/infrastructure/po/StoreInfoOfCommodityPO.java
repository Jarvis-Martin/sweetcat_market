package com.sweetcat.userorder.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_store_info_of_commodity
 * @author 
 */
@Data
public class StoreInfoOfCommodityPO implements Serializable {
    /**
     * 拆分前订单编号
     */
    private Long orderId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 店铺logo
     */
    private String storeLogo;

    private static final long serialVersionUID = 1L;
}