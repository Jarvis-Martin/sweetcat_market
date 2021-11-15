package com.sweetcat.commons.domainevent.userinfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-19:48
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class UserRegisteredEvent {
    /**
     * 新创建的userId
     */
    private Long userId;
    /**
     * 用户注册时间
     */
    private Long userRegisterTime;
    /**
     * 事件发生时间
     */
    private Long occurOn;

    public UserRegisteredEvent(Long userId) {
        this.userId = userId;
        this.occurOn = Instant.now().toEpochMilli();
    }

}
