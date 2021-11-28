package com.sweetcat.secondkill.domain.commonditypostcharge.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.secondkill.domain.commodity.vo.Store;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_store_commodity_post_charge
 *
 * @author
 */
@Getter
public class SKCommodityPostCharge implements Serializable {
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
    private Store store;

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

    public SKCommodityPostCharge(Long chargeId, Long commodityId) {
        this.chargeId = chargeId;
        this.commodityId = commodityId;
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

    public void setStore(Store store) {
        if (store == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.store = store;
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