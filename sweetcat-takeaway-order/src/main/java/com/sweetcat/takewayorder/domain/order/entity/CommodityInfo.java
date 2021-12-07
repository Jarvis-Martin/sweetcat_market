package com.sweetcat.takewayorder.domain.order.entity;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:09
 * @version: 1.0
 */
@Getter
public class CommodityInfo implements Cloneable{
    private Long orderId;
    /**
     * 商品编号
     */
    private Long commodityId;
    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 商品主图
     */
    private String commodityPicSmall;
    /**
     * 商品下单时价格
     */
    private BigDecimal priceWhenMakeOrder;
    /**
     * 商品购买数量
     */
    private Integer count;

    /**
     * 本商品相关的优惠金额信息
     */
    private AmountInfoOfCommodity amountInfo;

    public CommodityInfo(Long orderId, Long commodityId) {
        this.orderId = orderId;
        this.setCommodityId(commodityId);
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public void setCommodityPicSmall(String commodityPicSmall) {
        this.commodityPicSmall = commodityPicSmall;
    }

    public void setPriceWhenMakeOrder(BigDecimal priceWhenMakeOrder) {
        this.priceWhenMakeOrder = priceWhenMakeOrder;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setAmountInfo(AmountInfoOfCommodity amountInfo) {
        this.amountInfo = amountInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        };
        if (o == null || getClass() != o.getClass()) {
            return false;
        };
        CommodityInfo that = (CommodityInfo) o;
        return Objects.equals(commodityId, that.commodityId) && Objects.equals(commodityName, that.commodityName) && Objects.equals(commodityPicSmall, that.commodityPicSmall) && Objects.equals(priceWhenMakeOrder, that.priceWhenMakeOrder) && Objects.equals(count, that.count) && Objects.equals(amountInfo, that.amountInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityId, commodityName, commodityPicSmall, priceWhenMakeOrder, count);
    }

    @Override
    public CommodityInfo clone() throws CloneNotSupportedException {
        return ((CommodityInfo) super.clone());
    }
}
