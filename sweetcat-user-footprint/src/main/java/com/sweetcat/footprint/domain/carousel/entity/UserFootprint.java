package com.sweetcat.footprint.domain.carousel.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_user_footprint
 *
 * @author
 */
@Getter
public class UserFootprint implements Serializable {
    /**
     * 记录id
     */
    private Long footprintId;

    /**
     * 用户id
     */
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
     * 浏览时的价格
     */
    private BigDecimal priceWhenBrowser;

    /**
     * 当前价格
     */
    private BigDecimal currentPrice;

    /**
     * 浏览开始时间
     */
    private Long startTime;

    /**
     * 浏览时长, 单位: ms
     */
    private Integer browserDuration;

    public UserFootprint(Long footprintId, Long userId, Long commodityId) {
        this.setFootprintId(footprintId);
        this.setUserId(userId);
        this.setCommodityId(commodityId);
    }

    public UserFootprint(Long footprintId, Long userId, Long commodityId, String picSmall, BigDecimal priceWhenBrowser, BigDecimal currentPrice, Long startTime, Integer browserDuration) {
        this.setFootprintId(footprintId);
        this.setUserId(userId);
        this.setCommodityId(commodityId);
        this.setPicSmall(picSmall);
        this.setPriceWhenBrowser(priceWhenBrowser);
        this.setCurrentPrice(currentPrice);
        this.setStartTime(startTime);
        this.setBrowserDuration(browserDuration);
    }

    public UserFootprint(Long footprintId, Long userId, Long commodityId, String picSmall, BigDecimal priceWhenBrowser, Long startTime, Integer browserDuration) {
        this.footprintId = footprintId;
        this.userId = userId;
        this.commodityId = commodityId;
        this.picSmall = picSmall;
        this.priceWhenBrowser = priceWhenBrowser;
        this.startTime = startTime;
        this.browserDuration = browserDuration;
    }

    public void setFootprintId(Long footprintId) {
        if (footprintId == null || footprintId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.footprintId = footprintId;
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

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public void setPriceWhenBrowser(BigDecimal priceWhenBrowser) {
        if (priceWhenBrowser == null || BigDecimal.ZERO.compareTo(priceWhenBrowser) > 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.priceWhenBrowser = priceWhenBrowser;
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

    public void setStartTime(Long startTime) {
        if (startTime == null || startTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.startTime = startTime;
    }

    public void setBrowserDuration(Integer browserDuration) {
        if (browserDuration == null || browserDuration < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.browserDuration = browserDuration;
    }

    private static final long serialVersionUID = 1L;
}