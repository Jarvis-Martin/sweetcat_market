package com.sweetcat.userorder.domain.order.entity;

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
public class CommodityInfo {
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
     * 下单商品规格
     */
    private String specification;
    /**
     * 商品购买数量
     */
    private Integer count;

    /**
     * 商品所属商店的信息,订单拆分(按商家)时使用
     */
    private StoreInfo storeInfo;

    /**
     * 本商品相关的优惠金额信息
     */
    private AmountInfoOfCommodity amountInfo;

    public CommodityInfo(Long commodityId) {
        this.commodityId = commodityId;
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

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setStoreInfo(StoreInfo storeInfo) {
        this.storeInfo = storeInfo;
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
        return Objects.equals(commodityId, that.commodityId) && Objects.equals(commodityName, that.commodityName) && Objects.equals(commodityPicSmall, that.commodityPicSmall) && Objects.equals(priceWhenMakeOrder, that.priceWhenMakeOrder) && Objects.equals(specification, that.specification) && Objects.equals(count, that.count) && Objects.equals(storeInfo, that.storeInfo) && Objects.equals(amountInfo, that.amountInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityId, commodityName, commodityPicSmall, priceWhenMakeOrder, specification, count, storeInfo, amountInfo);
    }
}
