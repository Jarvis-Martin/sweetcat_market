package com.sweetcat.credit.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/15-23:12
 * @version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCreditLogCommand {
    /**
     * 积分记录用户
     */
    private Long userId;

    /**
     * 记录类型：0收入；1支付
     */
    private Integer logType;

    /**
     * 描述：如签到获得30积分；兑换支出300积分
     */
    private String description;

    /**
     * 纪录涉及的积分数量
     */
    private Integer creditNumber;

    /**
     * 记录创建时间
     */
    private Long createTime;

    public AddCreditLogCommand(Long userId) {
        this.userId = userId;
    }
}
