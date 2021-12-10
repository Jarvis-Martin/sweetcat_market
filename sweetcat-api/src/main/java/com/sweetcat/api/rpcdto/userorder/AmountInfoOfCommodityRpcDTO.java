package com.sweetcat.api.rpcdto.userorder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-19:22
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class AmountInfoOfCommodityRpcDTO implements Cloneable{
    private Long orderId;
    private Long commodityId;
    /**
     * 实际支付金额
     */
    private BigDecimal priceOfPayment;
    /**
     * 商品总价
     */
    private BigDecimal priceOfCommodity;
    /**
     * 优惠金额
     */
    private DiscountPriceInfoRpcDTO discountPriceInfo;

    public AmountInfoOfCommodityRpcDTO(Long orderId, Long commodityId) {
        this.orderId = orderId;
        this.commodityId = commodityId;
    }

    @Override
    public AmountInfoOfCommodityRpcDTO clone() throws CloneNotSupportedException {
        return ((AmountInfoOfCommodityRpcDTO) super.clone());
    }
}
