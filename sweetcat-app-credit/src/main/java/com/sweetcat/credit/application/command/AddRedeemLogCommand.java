package com.sweetcat.credit.application.command;

import lombok.*;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-21:44
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddRedeemLogCommand {
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

    public AddRedeemLogCommand(Long redeemUserId) {
        this.redeemUserId = redeemUserId;
    }
}
