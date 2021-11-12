package com.sweetcat.recommend.domain.recommendform.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-16:53
 * @version: 1.0
 */
@Getter
public class Commodity {
    /**
     * 商品id
     */
    private Long commodityId;
    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 商品主图 小
     */
    private String picSmall;
    /**
     * 商品现价
     */
    private BigDecimal currentPrice;
    /**
     * 商品好评数量
     */
    private Long goodReputationNumber;
    /**
     * 推荐商品的规格
     */
    private String commoditySpecification;

    public Commodity(Long commodityId) {
        this.setCommodityId(commodityId);
    }

    public Commodity(Long commodityId, String commodityName, String picSmall, BigDecimal currentPrice, Long goodReputationNumber, String commoditySpecification) {
        this.setCommodityId(commodityId);
        this.setCommodityName(commodityName);
        this.setPicSmall(picSmall);
        this.setCurrentPrice(currentPrice);
        this.setGoodReputationNumber(goodReputationNumber);
        this.setCommoditySpecification(commoditySpecification);
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
        if (commodityName == null || commodityName.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commodityName = commodityName;
    }

    public void setPicSmall(String picSmall) {
        if (picSmall == null || picSmall.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.picSmall = picSmall;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        if (currentPrice == null || BigDecimal.ZERO.compareTo(currentPrice) > 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.currentPrice = currentPrice;
    }

    public void setGoodReputationNumber(Long goodReputationNumber) {
        if (goodReputationNumber == null || 0 > goodReputationNumber) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.goodReputationNumber = goodReputationNumber;
    }

    public void setCommoditySpecification(String commoditySpecification) {
        if (commoditySpecification == null || commoditySpecification.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commoditySpecification = commoditySpecification;
    }
}
