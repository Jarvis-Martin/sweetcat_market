package com.sweetcat.api.rpcdto.customerservice;

import lombok.Data;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/26-17:04
 * @version: 1.0
 */
@Data
public class CustomerServiceStaffInfoRpcDTO {
    /**
     * 客服id
     */
    private Long staffId;

    /**
     * 客服昵称
     */
    private String staffNickname;

    /**
     * 客服人员头像
     */
    private String staffAvatar;
}
