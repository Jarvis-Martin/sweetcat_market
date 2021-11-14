package com.sweetcat.customerservice.domain.feedback.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-12:59
 * @version: 1.0
 */
public class Informer {
    /**
     * 处理该反馈的客服id
     */
    private Long staffId;

    public Informer(Long staffId) {
        this.staffId = staffId;
    }

    public void setStaffId(Long staffId) {
        if (staffId == null || staffId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.staffId = staffId;
    }
}
