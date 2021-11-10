package com.sweetcat.storecommodity.domain.commonditypostcharge.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_store_commodity_post_charge
 *
 * @author
 */
@Getter
public class CommodityPostCharge implements Serializable {
    /**
     * 记录id
     */
    private Long chargeId;

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 商家id
     */
    private Long storeId;

    /**
     * 省名
     */
    private String provinceCode;

    /**
     * 运费
     */
    private BigDecimal postCharge;
    
    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    public CommodityPostCharge(Long chargeId, Long commodityId, Long storeId, String provinceCode) {
        this.setChargeId(chargeId);
        this.setCommodityId(commodityId);
        this.setStoreId(storeId);
        this.setProvinceCode(provinceCode);
    }

    public CommodityPostCharge(Long chargeId, Long commodityId, Long storeId, String provinceCode, BigDecimal postCharge, Long createTime, Long updateTime) {
        this.setChargeId(chargeId);
        this.setCommodityId(commodityId);
        this.setStoreId(storeId);
        this.setProvinceCode(provinceCode);
        this.setPostCharge(postCharge);
        this.setCreateTime(createTime);
        this.setUpdateTime(updateTime);
    }

    public void setChargeId(Long chargeId) {
        if (chargeId == null || chargeId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.chargeId = chargeId;
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

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setPostCharge(BigDecimal postCharge) {
        if (postCharge == null || postCharge.compareTo(BigDecimal.ZERO) < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.postCharge = postCharge;
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

    private static final long serialVersionUID = 1L;
}