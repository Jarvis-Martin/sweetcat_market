package com.sweetcat.api.rpcdto.couponcenter;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-22:42
 * @version: 1.0
 */
@Getter
public class UniversalCouponRpcDTO extends CouponRpcDTO {
    /**
     * 目标优惠券id
     */
    private Long couponId;

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

    public UniversalCouponRpcDTO(Long couponId, BigDecimal thresholdPrice, BigDecimal counteractPrice, Long stock) {
        super(couponId, thresholdPrice, counteractPrice, stock);
        this.couponId = couponId;
    }

    @Override
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
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
