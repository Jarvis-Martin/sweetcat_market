package com.sweetcat.credit.domain.creditlog.entity;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:25
 * @version: 1.0
 */
public class CreditLog {
    /**
     * 签到记录id
     */
    private Long creditLogId;
    /**
     * 积分记录用户
     */
    private CreditedUser creditedUser;
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
}
