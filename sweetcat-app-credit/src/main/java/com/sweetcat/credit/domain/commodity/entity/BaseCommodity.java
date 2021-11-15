package com.sweetcat.credit.domain.commodity.entity;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:40
 * @version: 1.0
 */
public class BaseCommodity {
    /**
     * 积分商城像编号
     */
    private Long marketItemId;
    /**
     * 商品创建人
     */
    private Creator creator;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * 兑换商品所需积分数
     */
    private Integer creditNumber;

    public BaseCommodity(Long marketItemId) {
        this.marketItemId = marketItemId;
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
}
