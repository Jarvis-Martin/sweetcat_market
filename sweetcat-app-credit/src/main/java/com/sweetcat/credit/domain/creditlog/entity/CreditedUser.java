package com.sweetcat.credit.domain.creditlog.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:26
 * @version: 1.0
 */
@Getter
public class CreditedUser {
    /**
     * 积分记录所属用户id
     */
    private Long creditedUserId;

    public CreditedUser(Long creditedUserId) {
        this.creditedUserId = creditedUserId;
    }

    public void setCreditedUserId(Long creditedUserId) {
        this.creditedUserId = creditedUserId;
    }
}
