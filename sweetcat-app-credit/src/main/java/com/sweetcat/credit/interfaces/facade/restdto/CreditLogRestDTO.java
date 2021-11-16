package com.sweetcat.credit.interfaces.facade.restdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/16-14:11
 * @version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class CreditLogRestDTO implements Serializable {
    /**
     * 签到记录id
     */
    private Long creditLogId;
    /**
     * 积分记录用户
     */
    private HashMap<String, Object> creditLogUser;
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

    public CreditLogRestDTO(Long creditLogId) {
        this.creditLogId = creditLogId;
    }
}
