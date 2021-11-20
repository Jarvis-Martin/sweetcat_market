package com.sweetcat.credit.domain.commodity.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.credit.domain.commodity.vo.Creator;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:40
 * @version: 1.0
 */
@Getter
public class BaseCommodity {
    public static final Integer COMMODITYTYPE_COUPON = 0;
    public static final Integer COMMODITYTYPE_GOOD = 1;
    /**
     * 积分商城像编号
     */
    protected Long marketItemId;
    /**
     * 商品创建人
     */
    protected Creator creator;
    /**
     * 商品库存
     */
    protected Long stock;
    /**
     * 创建时间
     */
    protected Long createTime;
    /**
     * 更新时间
     */
    protected Long updateTime;
    /**
     * 兑换商品所需积分数
     */
    protected Long creditNumber;

    /**
     * 商品类别：0优惠券；1实物商品
     */
    protected Integer commodityType;

    public BaseCommodity(Long marketItemId, Creator creator, Long stock, Long createTime, Long updateTime, Long creditNumber, Integer commodityType) {
        this.marketItemId = marketItemId;
        this.creator = creator;
        this.stock = stock;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.creditNumber = creditNumber;
        this.commodityType = commodityType;
    }

    public void setMarketItemId(Long marketItemId) {
        if (marketItemId == null || marketItemId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.marketItemId = marketItemId;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
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
    public void setUpdateTime(Long updateTime) {
        if (updateTime == null || updateTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.updateTime = updateTime;
    }

    public void setCreditNumber(Long creditNumber) {
        if (creditNumber == null || creditNumber < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.creditNumber = creditNumber;
    }

    public void setStock(Long stock) {
        if (stock == null || stock < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.stock = stock;
    }

    public void setCommodityType(Integer commodityType) {
        if (commodityType == null || commodityType < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commodityType = commodityType;
    }

    /**
     * 增加(减少) 商品库存
     * @param increment 库存增量
     */
    public void changeStock(Long increment) {
        this.setStock(stock + increment);
    }
}
