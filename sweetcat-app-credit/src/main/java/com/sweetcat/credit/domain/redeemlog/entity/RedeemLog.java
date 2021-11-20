package com.sweetcat.credit.domain.redeemlog.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:04
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public class RedeemLog {
    /**
     * 兑换记录id
     */
    private Long redeemLogId;
    /**
     * 发起兑换操作的userid
     */
    private RedeemUser redeemUser;
    /**
     * 被兑换商品信息
     */
    private RedeemedCommodity redeemedCommodity;
    /**
     * 兑换时间
     */
    private Long createTime;

    public RedeemLog(Long redeemLogId) {
        this.redeemLogId = redeemLogId;
    }

    public void setRedeemLogId(Long redeemLogId) {
        if (redeemLogId == null || redeemLogId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.redeemLogId = redeemLogId;
    }

    public void setRedeemUser(RedeemUser redeemUser) {
        if (redeemUser == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.redeemUser = redeemUser;
    }

    public void setRedeemedCommodity(RedeemedCommodity redeemedCommodity) {
        if (redeemedCommodity == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.redeemedCommodity = redeemedCommodity;
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
}
