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
     * 被兑换商品名
     */
    private String commodityName;
    /**
     * 商品主图 3张最佳
     */
    private List<String> picSmall;
    /**
     * 兑换商品所需积分书
     */
    private Integer costCreditNumber;

    public RedeemedCommodity(Long commodityId) {
        this.setCommodityId(commodityId);
    }

    public RedeemedCommodity(Long commodityId, String commodityName, List<String> picSmall, Integer costCreditNumber) {
        this.setCommodityId(commodityId);
        this.setCommodityName(commodityName);
        this.setPicSmall(picSmall);
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

    public void setCommodityName(String commodityName) {
        if (commodityName == null || commodityName.length() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commodityName = commodityName;
    }

    public void setPicSmall(List<String> picSmall) {
        if (picSmall == null || picSmall.size() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.picSmall = picSmall;
    }

    public void setCostCreditNumber(Integer costCreditNumber) {
        if (costCreditNumber == null || costCreditNumber < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.costCreditNumber = costCreditNumber;
    }
}
