package com.sweetcat.favorite.domain.favorite.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_user_favorate
 *
 * @author
 */
@Getter
public class UserFavorate implements Serializable {
    private Long favoriteId;

    private Long userId;

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 商品正面图（small）
     */
    private String picSmall;

    /**
     * 商品名
     */
    private String commodityName;

    /**
     * 现价
     */
    private BigDecimal currentPrice;

    /**
     * 收藏时价格
     */
    private BigDecimal priceWhenFavorite;

    /**
     * 收藏时间
     */
    private Long createTime;

    /**
     * 加购数
     */
    private Integer addToCarNumber;

    /**
     * 金币可抵扣金额
     */
    private BigDecimal coinCounteractNumber;

    /**
     * 购后反馈金币数
     */
    private Long feedbackCoinNumber;

    private static final long serialVersionUID = 1L;

    public UserFavorate(Long favoriteId, Long userId, Long commodityId) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.commodityId = commodityId;
    }

    public UserFavorate(Long favoriteId, Long userId, Long commodityId, String picSmall, String commodityName, BigDecimal currentPrice, BigDecimal priceWhenFavorite, Long createTime, Integer addToCarNumber, BigDecimal coinCounteractNumber, Long feedbackCoinNumber) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.commodityId = commodityId;
        this.picSmall = picSmall;
        this.commodityName = commodityName;
        this.currentPrice = currentPrice;
        this.priceWhenFavorite = priceWhenFavorite;
        this.createTime = createTime;
        this.addToCarNumber = addToCarNumber;
        this.coinCounteractNumber = coinCounteractNumber;
        this.feedbackCoinNumber = feedbackCoinNumber;
    }

    public void setUserId(Long userId) {
        if (userId == null || userId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.userId = userId;
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

    public void setPriceWhenFavorite(BigDecimal priceWhenFavorite) {
        if (priceWhenFavorite == null || BigDecimal.ZERO.compareTo(priceWhenFavorite) > 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.priceWhenFavorite = priceWhenFavorite;
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

    public void setFavoriteId(Long favoriteId) {
        if (favoriteId == null || favoriteId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.favoriteId = favoriteId;
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

    public void setCommodityName(String commodityName) {
        if (commodityName == null || commodityName.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commodityName = commodityName;
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

    public void setAddToCarNumber(Integer addToCarNumber) {
        if (addToCarNumber == null || 0 > addToCarNumber) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.addToCarNumber = addToCarNumber;
    }

    public void setCoinCounteractNumber(BigDecimal coinCounteractNumber) {
        if (coinCounteractNumber == null || BigDecimal.ZERO.compareTo(coinCounteractNumber) > 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.coinCounteractNumber = coinCounteractNumber;
    }

    public void setFeedbackCoinNumber(Long feedbackCoinNumber) {
        if (feedbackCoinNumber == null || 0 > feedbackCoinNumber) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.feedbackCoinNumber = feedbackCoinNumber;
    }
}