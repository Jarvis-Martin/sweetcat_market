package com.sweetcat.userinformation.interfaces.facade.restdto;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-16:30
 * @version: 1.0
 */
@Getter
public class PublisherRestDTO {
    /**
     * 发布人id
     */
    private Long publisherId;
    /**
     * 发布人昵称
     */
    private String publisherName;
    /**
     * 发布人头像
     */
    private String publisherAvatar;

    public PublisherRestDTO(Long publisherId) {
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

    public void setPublisherName(String publisherName) {
        if (publisherName == null || publisherName.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.publisherName = publisherName;
    }

    public void setPublisherAvatar(String publisherAvatar) {
        if (publisherAvatar == null || publisherAvatar.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.publisherAvatar = publisherAvatar;
    }
}
