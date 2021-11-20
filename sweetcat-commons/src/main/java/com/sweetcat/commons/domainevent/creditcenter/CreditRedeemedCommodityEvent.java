package com.sweetcat.commons.domainevent.creditcenter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-21:21
 * @version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditRedeemedCommodityEvent {
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

    /**
     * 事件发生事件
     */
    private Long occurOn;
}
