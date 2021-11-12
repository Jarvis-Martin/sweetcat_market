package com.sweetcat.recommend.domain.recommendform.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-16:53
 * @version: 1.0
 */
@Getter
public class Referrer {
    // 推荐人id
    private Long referrerId;

    public Referrer(Long referrerId) {
        this.setReferrerId(referrerId);
    }

    public void setReferrerId(Long referrerId) {
        if (referrerId == null || referrerId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.referrerId = referrerId;
    }
}
