package com.sweetcat.credit.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_app_credit_log
 * @author 
 */
@Data
public class CreditLogPO implements Serializable {
    /**
     * 签到记录id
     */
    private Long creditLogId;

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
    private Long creditNumber;

    /**
     * 记录创建时间
     */
    private Long createTime;

    private static final long serialVersionUID = 1L;
}