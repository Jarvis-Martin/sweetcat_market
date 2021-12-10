package com.sweetcat.api.rpcdto.userorder;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @date: 2021-11-2021/11/30-17:11
 * @version: 1.0
 * @description: 记录整个订单的金额信息，
 * {@link CommodityInfoRpcDTO} 中的 {@link AmountInfoOfCommodityRpcDTO} 包含对应单类商品涉及的金额信息
 */
@Data
@NoArgsConstructor
public class AmountInfoRpcDTO {
    private Long orderId;
    /**
     * 实际支付金额,订单拆分前操作前，(父)订单的总应付金额
     */
    private BigDecimal priceOfPayment;
    /**
     * 实际支付金额,订单拆分前操作前，(父)订单所有商品总金额(不含任何优惠金额扣减)
     */
    private BigDecimal priceOfCommodity;
    /**
     * 本订单消耗的总积分数
     */
    private Integer credit;
    /**
     * 对订单拆分前整个订单使用的各种优惠券的记录
     */
    private List<CouponRpcDTO> couponRpcDTOS;

    public AmountInfoRpcDTO(Long orderId) {
        this.orderId = orderId;
    }

}
