package com.sweetcat.userinformation.domain.information.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-16:48
 * @version: 1.0
 */
@Getter
public class SystemInformation extends Information {
    /**
     * 通知id
     */
    private Long informationId;
    /**
     * 处理时间
     */
    private Long processTime;
    /**
     * 响应标题
     */
    private String responseTitle;

    public SystemInformation(Long informationId) {
        super(informationId);
        this.setInformationId(informationId);
    }

    @Override
    public void setInformationId(Long informationId) {
        if (informationId == null || informationId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.informationId = informationId;
    }

    public void setProcessTime(Long processTime) {
        if (processTime == null || processTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.processTime = processTime;
    }

    public void setResponseTitle(String responseTitle) {
        if (responseTitle == null || responseTitle.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.responseTitle = responseTitle;
    }
}
