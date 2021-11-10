package com.sweetcat.storecommodity.infrastructure.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_store_commodity_post_charge
 *
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

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    private static final long serialVersionUID = 1L;
}