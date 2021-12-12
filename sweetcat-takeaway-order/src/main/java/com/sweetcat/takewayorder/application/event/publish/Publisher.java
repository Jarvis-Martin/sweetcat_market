package com.sweetcat.takewayorder.application.event.publish;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/27-13:11
 * @version: 1.0
 */
@Getter
public class Publisher {
    /**
     * 发布评论的用户id
     */
    private Long publisherId;

    public Publisher(Long publisherId) {
        this.publisherId = publisherId;
    }

    public void setPublisherId(Long publisherId) {
        if (publisherId == null || publisherId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.publisherId = publisherId;
    }
}
