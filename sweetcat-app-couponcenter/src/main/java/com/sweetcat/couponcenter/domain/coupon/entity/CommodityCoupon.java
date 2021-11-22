package com.sweetcat.couponcenter.domain.coupon.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.couponcenter.domain.coupon.vo.Commodity;
import com.sweetcat.couponcenter.domain.coupon.vo.Creator;
import com.sweetcat.couponcenter.domain.coupon.vo.Store;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-20:16
 * @version: 1.0
 */
@Getter
public class CommodityCoupon extends Coupon {
    /**
     * 目标优惠券id
     */
    private Long couponId;

    /**
     * 优惠券使用对象类别表示；0通用券，1商品券
     */
    private Integer targetType;

    private Store store;

    private Commodity commodity;

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

    public CommodityCoupon(Long couponId, BigDecimal thresholdPrice, BigDecimal counteractPrice, Creator creator, Long createTime, Long updateTime, Long stock, Integer targetType) {
        super(couponId, thresholdPrice, counteractPrice, creator, createTime, updateTime, stock, targetType);
        this.setCouponId(couponId);
    }


    @Override
    public void setCouponId(Long couponId) {
        if (couponId == null || couponId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.couponId = couponId;
    }

    public void setStore(Store store) {
        if (store == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.store = store;
    }

    public void setCommodity(Commodity commodity) {
        if (commodity == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commodity = commodity;
    }

    public void setTimeType(Integer timeType) {
        if (timeType == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.timeType = timeType;
    }

    public void setTargetType(Integer targetType) {
        if (targetType == null || targetType < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.targetType = targetType;
    }

    public void setValidDuration(Long validDuration) {
        if (validDuration == null || validDuration < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.validDuration = validDuration;
    }

    public void setStartTime(Long startTime) {
        if (startTime == null || startTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.startTime = startTime;
    }

    public void setDeadline(Long deadline) {
        if (deadline == null || deadline < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.deadline = deadline;
    }
}
