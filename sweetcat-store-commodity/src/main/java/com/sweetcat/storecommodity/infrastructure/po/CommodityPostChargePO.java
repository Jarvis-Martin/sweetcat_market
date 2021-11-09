package com.sweetcat.storecommodity.infrastructure.po;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * t_store_commodity_post_charge
 * @author 
 */
@Data
public class CommodityPostChargePO implements Serializable {
    /**
     * 记录id
     */
    private Long chargeId;

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 商家id
     */
    private Long storeId;

    /**
     * 省名
     */
    private String provinceCode;

    /**
     * 运费
     */
    private BigDecimal postCharge;

    private static final long serialVersionUID = 1L;
}