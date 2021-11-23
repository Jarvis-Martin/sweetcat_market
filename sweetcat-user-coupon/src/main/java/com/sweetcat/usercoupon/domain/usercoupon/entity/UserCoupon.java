package com.sweetcat.usercoupon.domain.usercoupon.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/21-20:57
 * @version: 1.0
 */
@Getter
public class UserCoupon<T extends Coupon> {
    /**
     * 记录id
     */
    private Long recordId;

    /**
     * 用户id
     */
    private User user;

    /**
     * 优惠券
     */
    private T coupon;

    /**
     * 获得时间
     */
    public Long obtainTime;

    /**
     * 优惠券类型
     */
    private Integer targetType;

    public UserCoupon(Long recordId) {
        this.recordId = recordId;
    }

    public void setRecordId(Long recordId) {
        if (recordId == null || recordId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.recordId = recordId;
    }

    public void setUser(User user) {
        if (user == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.user = user;
    }

    public void setCoupon(T coupon) {
        if (coupon == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.coupon = coupon;
    }

    public void setObtainTime(Long obtainTime) {
        if (obtainTime == null || obtainTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.obtainTime = obtainTime;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }
}
