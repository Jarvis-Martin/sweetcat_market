package com.sweetcat.credit.domain.redeemlog.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:12
 * @version: 1.0
 */
@Getter
public class RedeemUser {
    /**
     * 发起兑换操作的用户id
     */
    private Long redeemUserId;

    public void setRedeemUserId(Long redeemUserId) {
        this.redeemUserId = redeemUserId;
    }

    public RedeemUser(Long redeemUserId) {
        if (redeemUserId == null || redeemUserId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.redeemUserId = redeemUserId;
    }
}
