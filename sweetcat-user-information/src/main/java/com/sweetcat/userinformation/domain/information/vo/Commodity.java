package com.sweetcat.userinformation.domain.information.vo;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-16:38
 * @version: 1.0
 */
@Getter
public class Commodity {
    /**
     * 商品id
     */
    private Long commodityId;
    /**
     * 商品名
     */
    private String commodityName;
    /**
     * 商品主图
     */
    private String picSmall;

    public Commodity(Long commodityId) {
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
}
