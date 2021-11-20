package com.sweetcat.credit.domain.redeemlog.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:15
 * @version: 1.0
 */
@Getter
public class RedeemedCommodity {
    /**
     * 兑换的商品的id
     */
    private Long commodityId;
    /**
     * 兑换商品所需积分书
     */
    private Long costCreditNumber;

    public RedeemedCommodity(Long commodityId) {
        this.setCommodityId(commodityId);
    }

    public RedeemedCommodity(Long commodityId, String commodityName, List<String> picSmall, Long costCreditNumber) {
        this.setCommodityId(commodityId);
        this.setCostCreditNumber(costCreditNumber);
    }

    public void setCommodityId(Long commodityId) {
        if (commodityId == null || commodityId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commodityId = commodityId;
    }

    public void setCostCreditNumber(Long costCreditNumber) {
        if (costCreditNumber == null || costCreditNumber < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.costCreditNumber = costCreditNumber;
    }
}
