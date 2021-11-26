package com.sweetcat.userinformation.domain.information.vo;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-17:15
 * @version: 1.0
 */
@Getter
public class Receiver {
    /**
     * 接收人id
     */
    private Long receiverId;

    public Receiver(Long receiverId) {
        this.receiverId = receiverId;
    }

    public void setReceiverId(Long receiverId) {
        if (receiverId == null || receiverId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.receiverId = receiverId;
    }
}
