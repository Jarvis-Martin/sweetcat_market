package com.sweetcat.credit.infrastructure.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * t_market_commodity_coupon
 *
 * @author
 */
@Getter
@Setter
@NoArgsConstructor
public class CouponPO extends BaseCommodityPO implements Serializable {
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

    private static final long serialVersionUID = 1L;


    public CouponPO(Long marketItemId, Long creatorId, String creatorName, Long stock, Long createTime, Long updateTime, Integer creditNumber, Integer commodityType) {
        super(marketItemId, creatorId, creatorName, stock, createTime, updateTime, creditNumber, commodityType);
    }
    public CouponPO(BaseCommodityPO baseCommodityPO) {
        super(
                baseCommodityPO.getMarketItemId(),
                baseCommodityPO.getCreatorId(),
                baseCommodityPO.getCreatorName(),
                baseCommodityPO.getStock(),
                baseCommodityPO.getCreateTime(),
                baseCommodityPO.getUpdateTime(),
                baseCommodityPO.getCreditNumber(),
                baseCommodityPO.getCommodityType()
        );
    }
}