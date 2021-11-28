package com.sweetcat.secondkill.interfaces.facade.restdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-20:00
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SKCommodityPostChargeDTO {
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
}
