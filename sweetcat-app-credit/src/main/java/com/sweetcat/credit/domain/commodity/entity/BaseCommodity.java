package com.sweetcat.credit.domain.commodity.entity;

import com.sweetcat.credit.domain.commodity.vo.Creator;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:40
 * @version: 1.0
 */
@Getter
public class BaseCommodity {
    public static final Integer COMMODITYTYPE_COUPON = 0;
    public static final Integer COMMODITYTYPE_GOOD = 1;
    /**
     * 积分商城像编号
     */
    protected Long marketItemId;
    /**
     * 商品创建人
     */
    protected Creator creator;
    /**
     * 商品库存
     */
    protected Long stock;
    /**
     * 创建时间
     */
    protected Long createTime;
    /**
     * 更新时间
     */
    protected Long updateTime;
    /**
     * 兑换商品所需积分数
     */
    protected Integer creditNumber;

    /**
     * 商品类别：0优惠券；1实物商品
     */
    protected Integer commodityType;

    public BaseCommodity(Long marketItemId, Creator creator, Long stock, Long createTime, Long updateTime, Integer creditNumber, Integer commodityType) {
        this.marketItemId = marketItemId;
        this.creator = creator;
        this.stock = stock;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.creditNumber = creditNumber;
        this.commodityType = commodityType;
    }

    public void setMarketItemId(Long marketItemId) {
        this.marketItemId = marketItemId;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreditNumber(Integer creditNumber) {
        this.creditNumber = creditNumber;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public void setCommodityType(Integer commodityType) {
        this.commodityType = commodityType;
    }
}
