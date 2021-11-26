package com.sweetcat.userinformation.domain.information.vo;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-17:11
 * @version: 1.0
 */
@Getter
public class Store {
    /**
     * 商家id
     */
    private Long storeId;
    /**
     * 商家名
     */
    private String storeName;

    public Store(Long storeId) {
        this.storeId = storeId;
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

    public void setStoreName(String storeName) {
        if (storeName == null || storeName.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.storeName = storeName;
    }
}
