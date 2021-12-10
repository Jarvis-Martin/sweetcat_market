package com.sweetcat.takewayorder.interfaces.facade.restdto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/2-20:38
 * @version: 1.0
 */
@Data
public class AmountInfoRestDTO {
    /**
     * 实付款
     */
    private BigDecimal priceOfPayment;

    /**
     * 本单总优惠金额
     */
    private BigDecimal discountPrice;
}
