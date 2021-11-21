package com.sweetcat.couponcenter.application.command;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/21-14:24
 * @version: 1.0
 */
@Data
public class AddUniversalCouponCommand {

    /**
     * 最低可使用价格
     */
    private BigDecimal thresholdPrice;

    /**
     * 抵扣价格
     */
    private BigDecimal counteractPrice;

    /**
     * 创建人id
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 优惠券使用对象类别表示；0通用券，1商品券
     */
    private Integer targetType;

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

    /**
     * 优惠券库存
     */
    private Long stock;
}
