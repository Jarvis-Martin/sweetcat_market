package com.sweetcat.credit.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_app_redeem_log
 * @author 
 */
@Data
public class RedeemLogPO implements Serializable {
    /**
     * 兑换记录id
     */
    private Long redeemLogId;

    /**
     * 发起兑换的用户id
     */
    private Long redeemUserId;

    /**
     * 兑换的商品的id
     */
    private Long commodityId;

    /**
     * 兑换商品所需积分数
     */
    private Long costCreditNumber;

    /**
     * 兑换时间
     */
    private Long createTime;

    private static final long serialVersionUID = 1L;
}