package com.sweetcat.couponcenter.domain.coupon.vo;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-20:15
 * @version: 1.0
 */
@Getter
public class Creator {
    private Long creatorId;

    public Creator(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreatorId(Long creatorId) {
        if (creatorId == null || creatorId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.creatorId = creatorId;
    }
}
