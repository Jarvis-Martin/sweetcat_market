package com.sweetcat.storeorder.application.command;

import lombok.Data;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/1-13:04
 * @version: 1.0
 */
@Data
public class CommodityCouponMap {
    /**
     * 商品id
     */
    private Long commodityId;
    /**
     * 购买该商品使用的积分数
     */
    private Integer credits;
    /**
     * 规格信息
     */
    private String specification;
    /**
     * 购买该商品使用的优惠券数量
     */
    private List<Long> couponIds;
    /**
     * 该商品购买的数量
     */
    private Integer count;
}
