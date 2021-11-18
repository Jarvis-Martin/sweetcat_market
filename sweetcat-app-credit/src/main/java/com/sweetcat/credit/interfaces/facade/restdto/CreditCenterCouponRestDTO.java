package com.sweetcat.credit.interfaces.facade.restdto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/17-20:56
 * @version: 1.0
 */
@Data
public class CreditCenterCouponRestDTO extends CreditCenterCommodityRestDTO{

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
    private Byte targetType;

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
    private String commodityPicSmall;

    /**
     * 商品名
     */
    private String commodityName;

    /**
     * 券的时间类型：0限时券；1区间券
     */
    private Byte timeType;

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
}
