package com.sweetcat.credit.domain.redeemlog.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:04
 * @version: 1.0
 */
@Getter
public class RedeemLog {
    /**
     * 兑换记录id
     */
    private Long redeemLogId;
    /**
     * 发起兑换操作的userid
     */
    private RedeemUser redeemUser;
    /**
     * 被兑换商品信息
     */
    private RedeemedCommodity redeemedCommodity;
    /**
     * 兑换时间
     */
    private Long createTime;
}
