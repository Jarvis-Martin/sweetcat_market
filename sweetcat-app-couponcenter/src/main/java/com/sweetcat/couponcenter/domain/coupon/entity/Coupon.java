package com.sweetcat.couponcenter.domain.coupon.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.couponcenter.domain.coupon.vo.Creator;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-20:07
 * @version: 1.0
 */
@Getter
public class Coupon {
    /**
     * 优惠券id
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
     * 创建人id
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
     * 优惠券库存
     */
    private Long stock;

    /**
     * 优惠券使用对象类别表示；0通用券，1商品券
     */
    private Integer targetType;

    public Coupon(Long couponId, BigDecimal thresholdPrice, BigDecimal counteractPrice, Creator creator, Long createTime, Long updateTime, Long stock, Integer targetType) {
        this.couponId = couponId;
        this.thresholdPrice = thresholdPrice;
        this.counteractPrice = counteractPrice;
        this.creator = creator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.stock = stock;
        this.targetType = targetType;
    }

    public void setCouponId(Long couponId) {
        if (couponId == null || couponId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.couponId = couponId;
    }

    public void setThresholdPrice(BigDecimal thresholdPrice) {
        if (thresholdPrice == null || thresholdPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.thresholdPrice = thresholdPrice;
    }

    public void setCounteractPrice(BigDecimal counteractPrice) {
        if (counteractPrice == null || counteractPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.counteractPrice = counteractPrice;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }


    public void setCreateTime(Long createTime) {
        if (createTime == null || createTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.createTime = createTime;
    }

    public void setUpdateTime(Long updateTime) {
        if (updateTime == null || updateTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.updateTime = updateTime;
    }

    public void setStock(Long stock) {
        if (stock == null || stock < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.stock = stock;
    }

    public void stock(Long stock) {
        this.setStock(stock);
    }
}
