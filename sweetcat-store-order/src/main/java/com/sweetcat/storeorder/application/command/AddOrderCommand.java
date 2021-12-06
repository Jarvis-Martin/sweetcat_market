package com.sweetcat.storeorder.application.command;

import lombok.Data;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/4-23:00
 * @version: 1.0
 */
@Data
public class AddOrderCommand {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单所属用户编号
     */
    private Long userId;

    /**
     * 下单时间
     */
    private Long placeOrderTime;

    /**
     * 收货地址id，用于本订单，拆单后，各子订单默认使用该地址
     */
    private Long addressId;

    /**
     * 用于订单的优惠券id
     */
    private List<Long> couponIdsForOrder;

    /**
     * 商品信息（商品id，以及购买时用于该商品的各种优惠券信息、积分信息）
     */
    private List<CommodityCouponMap> commodities;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPlaceOrderTime(Long placeOrderTime) {
        this.placeOrderTime = placeOrderTime;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public void setCouponIdsForOrder(List<Long> couponIdsForOrder) {
        this.couponIdsForOrder = couponIdsForOrder;
    }

    public void setCommodities(List<CommodityCouponMap> commodities) {
        this.commodities = commodities;
    }
}
