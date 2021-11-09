package com.sweetcat.storecommodity.domain.commodityinfo.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-22:14
 * @Version: 1.0
 */
@Getter
public class CommodityInfo {

    public static final Integer STATUS_AUDITING = 0;

    /**
     * 商品od
     */
    private Long commodityId;

    /**
     * 店铺id
     */
    private Long storeId;

    /**
     * 商品名
     */
    private String commodityName;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 商品正面图（small）
     */
    private String picSmall;

    /**
     * 商品主图（big）
     */
    private List<String> picBig;

    /**
     * 一级分类
     */
    private String firstCategory;

    /**
     * 二级分类
     */
    private String secondCategory;

    /**
     * 三级分类
     */
    private String thirdCategory;

    /**
     * 几手货.0：全新；1：二手货
     */
    private Integer useTimes;

    /**
     * 产地
     */
    private String productionArea;

    /**
     * 原价
     */
    private BigDecimal oldPrice;

    /**
     * 现价
     */
    private BigDecimal currentPrice;

    /**
     * 宝贝描述
     */
    private String description;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 月销量
     */
    private Integer monthlySales;

    /**
     * 加购数
     */
    private Integer addToCarNumber;

    /**
     * 收藏数
     */
    private Integer collectNumber;

    /**
     * 保障
     */
    private String guarantee;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    /**
     * 商品状态.0： 待审核; 1： 审核通过; 2： 审核未通过; 3：已下架
     */
    private Integer status;

    /**
     * 商品规格
     */
    private String specification;

    /**
     * 评论总数
     */
    private Long commentNumber;

    /**
     * 默认运费
     */
    private BigDecimal defaultPostCharge;

    /**
     * 商品多余一件时，多出的商品每件多少运费
     */
    private BigDecimal subjoinChargePerGood;

    /**
     * 金币抵扣率,取值范围：0% ~ 99%
     */
    private BigDecimal coinCounteractRate;

    /**
     * 金币可抵扣金额
     */
    private BigDecimal coinCounteractNumber;

    /**
     * 购后反馈金币数
     */
    private Long feedbackCoinNumber;

    public CommodityInfo(Long commodityId, Long storeId, String commodityName,
                         String brand, String picSmall, List<String> picBig,
                         String firstCategory, String secondCategory, String thirdCategory,
                         Integer useTimes, String productionArea, BigDecimal oldPrice,
                         BigDecimal currentPrice, String description, Integer stock,
                         Integer monthlySales, Integer addToCarNumber, Integer collectNumber,
                         String guarantee, Long createTime, Long updateTime, Integer status,
                         String specification, Long commentNumber, BigDecimal defaultPostCharge,
                         BigDecimal subjoinChargePerGood, BigDecimal coinCounteractRate) {
        this.setCommodityId(commodityId);
        this.setStoreId(storeId);
        this.setCommodityName(commodityName);
        this.setBrand(brand);
        this.setPicSmall(picSmall);
        this.setPicBig(picBig);
        this.setFirstCategory(firstCategory);
        this.setSecondCategory(secondCategory);
        this.setThirdCategory(thirdCategory);
        this.setUseTimes(useTimes);
        this.setProductionArea(productionArea);
        this.setOldPrice(oldPrice);
        this.setCurrentPrice(currentPrice);
        this.setDescription(description);
        this.setStock(stock);
        this.setMonthlySales(monthlySales);
        this.setAddToCarNumber(addToCarNumber);
        this.setCollectNumber(collectNumber);
        this.setGuarantee(guarantee);
        this.setCreateTime(createTime);
        this.setUpdateTime(updateTime);
        this.setStatus(status);
        this.setSpecification(specification);
        this.setCommentNumber(commentNumber);
        this.setDefaultPostCharge(defaultPostCharge);
        this.setSubjoinChargePerGood(subjoinChargePerGood);
        this.setCoinCounteractRate(coinCounteractRate);
        // 金币可抵扣率 * 现价
        this.coinCounteractNumber = currentPrice.multiply(coinCounteractRate);
        // 现价取整 * 0.5
        this.feedbackCoinNumber = Math.round(this.currentPrice.toBigInteger().longValue() * 0.5);
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

    public void setStoreId(Long storeId) {
        if (storeId == null || storeId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.storeId = storeId;
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

    public void setBrand(String brand) {
        this.brand = brand == null ? "" : brand;
    }

    public void setPicSmall(String picSmall) {
        if (picSmall == null || picSmall.length() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.picSmall = picSmall;
    }

    public void setPicBig(List<String> picBig) {
        if (picBig == null || picBig.size() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.picBig = picBig;
    }

    public void setFirstCategory(String firstCategory) {
        if (firstCategory == null || firstCategory.length() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.firstCategory = firstCategory;
    }

    public void setSecondCategory(String secondCategory) {
        if (secondCategory == null || secondCategory.length() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.secondCategory = secondCategory;
    }

    public void setThirdCategory(String thirdCategory) {
        if (thirdCategory == null || thirdCategory.length() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.thirdCategory = thirdCategory;
    }

    public void setUseTimes(Integer useTimes) {
        if (useTimes == null || useTimes < 0 || useTimes > 1) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.useTimes = useTimes;
    }

    public void setProductionArea(String productionArea) {
        if (productionArea == null || productionArea.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.productionArea = productionArea;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        if (oldPrice == null || oldPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.oldPrice = oldPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        if (oldPrice == null || oldPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.currentPrice = currentPrice;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public void setStock(Integer stock) {
        if (stock == null || stock < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.stock = stock;
    }

    public void setMonthlySales(Integer monthlySales) {
        if (stock == null || stock < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.monthlySales = monthlySales;
    }

    public void setAddToCarNumber(Integer addToCarNumber) {
        if (addToCarNumber == null || addToCarNumber < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.addToCarNumber = addToCarNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        if (collectNumber == null || collectNumber < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.collectNumber = collectNumber;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee == null || guarantee.length() == 0 ? "" : guarantee;
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

    public void setStatus(Integer status) {
        if (status == null || status < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.status = status;
    }

    public void setSpecification(String specification) {
        if (specification == null || specification.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.specification = specification;
    }

    public void setCommentNumber(Long commentNumber) {
        if (commentNumber == null || commentNumber < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commentNumber = commentNumber;
    }

    public void setDefaultPostCharge(BigDecimal defaultPostCharge) {
        if (defaultPostCharge == null || defaultPostCharge.compareTo(BigDecimal.ZERO) < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.defaultPostCharge = defaultPostCharge;
    }

    public void setSubjoinChargePerGood(BigDecimal subjoinChargePerGood) {
        if (subjoinChargePerGood == null || subjoinChargePerGood.compareTo(BigDecimal.ZERO) < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.subjoinChargePerGood = subjoinChargePerGood;
    }

    public void setCoinCounteractRate(BigDecimal coinCounteractRate) {
        if (coinCounteractRate == null || coinCounteractRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.coinCounteractRate = coinCounteractRate;
    }

}
