package com.sweetcat.credit.application.command;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-19:43
 * @version: 1.0
 */
@Getter
@Setter
public class AddUserCommand {
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 创建时间
     */
    private Long createTime;

    public AddUserCommand(Long userId) {
        this.userId = userId;
    }
}
