package com.sweetcat.credit.domain.creditlog.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:26
 * @version: 1.0
 */
@Getter
public class CreditLogUser {
    /**
     * 积分记录所属用户id
     */
    private Long userId;

    public CreditLogUser(Long userId) {
        this.userId = userId;
    }

    public void setCreditedUserId(Long userId) {
        this.userId = userId;
    }
}
