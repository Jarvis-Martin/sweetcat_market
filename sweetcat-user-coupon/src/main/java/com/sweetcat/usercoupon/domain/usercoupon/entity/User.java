package com.sweetcat.usercoupon.domain.usercoupon.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/21-20:57
 * @version: 1.0
 */
@Getter
public class User {
    private Long userId;

    public User(Long userId) {
        this.userId = userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
