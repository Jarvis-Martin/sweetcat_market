package com.sweetcat.secondkill.domain.commodity.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.secondkill.domain.commodity.vo.Store;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/27-12:53
 * @version: 1.0
 */
@Getter
public class SKCommodity {
    /**
     * 审核中
     */
    public static Integer STATUS_AUDITING = 0;
    /**
     * 审核通过
     */
    public static Integer STATUS_AUDITED = 1;
    /**
     * 审核失败
     */
    public static Integer STATUS_AUDIT_FAIL = 2;
    /**
     * 已下架
     */
    public static Integer STATUS_SOLD_OUT = 3;

    /**
     * 商品od
     */
    private Long commodityId;

    /**
     * 店铺id
     */
    private Store store;

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
    private Long totalStock;

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
     * 该商品秒杀开始时间
     */
    private Long startTime;

    /**
     * 商品剩余库存
     */
    private Long remainStock;

    public SKCommodity(Long commodityId) {
        this.commodityId = commodityId;
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

    public void setStore(Store store) {
        if (commodityId == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.store = store;
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

    public void setTotalStock(Long totalStock) {
        if (totalStock == null || totalStock < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.totalStock = totalStock;
    }

    public void setMonthlySales(Integer monthlySales) {
        if (monthlySales == null || monthlySales < 0) {
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

    public void setStartTime(Long startTime) {
        if (startTime == null || startTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.startTime = startTime;
    }

    public void setRemainStock(Long remainStock) {
        // remainStock 必须 <= totalStock
        if (remainStock == null || remainStock < 0 || remainStock > this.totalStock) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.remainStock = remainStock;
    }

    public void increaseCommentNumber() {
        this.setCommentNumber(commentNumber + 1);
    }
}
