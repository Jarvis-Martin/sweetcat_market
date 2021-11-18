package com.sweetcat.credit.domain.commodity.entity;

import com.sweetcat.credit.domain.commodity.vo.Creator;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-18:07
 * @version: 1.0
 */
@Getter
public class Coupon extends BaseCommodity {
    /**
     * 和位于 t_app_credit_market 中的 market_item_id 相对应
     */
    private Long marketItemId;


    /**
     * 目标优惠券id
     */
    private Long couponId;

    /**
     * 最低可使用价格
     */
    private BigDecimal thresholdPrice;

    /**
     * 抵扣价格
     */
    private BigDecimal counteractPrice;

    /**
     * 优惠券使用对象类别表示；0通用券，1商品券
     */
    private Integer targetType;

    /**
     * 商品对应的店家id
     */
    private Long storeId;

    /**
     * 商品对应的店家名称
     */
    private String storeName;

    /**
     * 商品编号
     */
    private Long commodityId;

    /**
     * 商品正面图（small） 3张最佳
     */
    private List<String> commodityPicSmall;

    /**
     * 商品名
     */
    private String commodityName;

    /**
     * 券的时间类型：0限时券；1区间券
     */
    private Integer timeType;

    /**
     * 显示券的有效时长，自领取时开始计算
     */
    private Long validDuration;

    /**
     * 区间券的可使用时间
     */
    private Long startTime;

    /**
     * 区间券的最后可使用时间
     */
    private Long deadline;

    public Coupon(Long marketItemId, Creator creator, Long stock, Long createTime, Long updateTime, Integer creditNumber, Integer commodityType) {
        super(marketItemId, creator, stock, createTime, updateTime, creditNumber, commodityType);
        this.marketItemId = marketItemId;
    }

    public Coupon(BaseCommodity baseCommodity) {
        super(baseCommodity.getMarketItemId(),
                baseCommodity.getCreator(),
                baseCommodity.getStock(),
                baseCommodity.getCreateTime(),
                baseCommodity.getUpdateTime(),
                baseCommodity.getCreditNumber(),
                baseCommodity.getCommodityType()
        );
        this.marketItemId = baseCommodity.getMarketItemId();
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public void setThresholdPrice(BigDecimal thresholdPrice) {
        this.thresholdPrice = thresholdPrice;
    }

    public void setCounteractPrice(BigDecimal counteractPrice) {
        this.counteractPrice = counteractPrice;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public void setCommodityPicSmall(List<String> commodityPicSmall) {
        this.commodityPicSmall = commodityPicSmall;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public void setValidDuration(Long validDuration) {
        this.validDuration = validDuration;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }
}
