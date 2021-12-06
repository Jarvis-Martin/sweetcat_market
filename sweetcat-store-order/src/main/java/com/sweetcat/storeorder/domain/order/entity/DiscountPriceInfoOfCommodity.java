package com.sweetcat.storeorder.domain.order.entity;

import lombok.Getter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-18:34
 * @version: 1.0
 */
@Getter
public class DiscountPriceInfoOfCommodity {
    /**
     * 商品优惠券信息对应的商品id
     */
    private Long commodityId;
    /**
     * 本订单使用的积分数
     */
    private Integer credit;
    /**
     * 该订单使用到的优惠券信息
     */
    private List<CouponOfCommodity> couponsOfCommodity;

    public DiscountPriceInfoOfCommodity(Long commodityId) {
        this.commodityId = commodityId;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public void setCouponsOfCommodity(List<CouponOfCommodity> couponsOfCommodity) {
        this.couponsOfCommodity = couponsOfCommodity;
    }
}
