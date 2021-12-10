package com.sweetcat.api.rpcdto.userorder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-18:34
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class DiscountPriceInfoRpcDTO {
    /**
     * 本订单使用的积分数
     */
    private Integer credit;
    /**
     * 该订单使用到的优惠券信息
     */
    private List<CouponRpcDTO> couponRpcDTOS;

}
