package com.sweetcat.usercomment.interfaces.facade.restdto;

import lombok.Data;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-16:11
 * @version: 1.0
 */
@Data
public class PublisherRestDTO {
    private Long publisherId;

    public PublisherRestDTO(Long publisherId) {
        this.publisherId = publisherId;
    }
}
