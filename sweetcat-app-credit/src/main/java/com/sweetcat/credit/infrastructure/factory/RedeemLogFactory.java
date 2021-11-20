package com.sweetcat.credit.infrastructure.factory;

import com.sweetcat.credit.domain.redeemlog.entity.RedeemLog;
import com.sweetcat.credit.domain.redeemlog.entity.RedeemUser;
import com.sweetcat.credit.domain.redeemlog.entity.RedeemedCommodity;
import com.sweetcat.credit.infrastructure.po.RedeemLogPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-21:29
 * @version: 1.0
 */
@Component
public class RedeemLogFactory {
    public RedeemLog create(RedeemLogPO logPO) {
        RedeemLog redeemLog = new RedeemLog(logPO.getRedeemLogId());
        RedeemUser redeemUser = new RedeemUser(logPO.getRedeemUserId());
        RedeemedCommodity redeemedCommodity = new RedeemedCommodity(logPO.getCommodityId());
        redeemedCommodity.setCostCreditNumber(logPO.getCostCreditNumber());
        redeemLog.setRedeemUser(redeemUser);
        redeemLog.setRedeemedCommodity(redeemedCommodity);
        redeemLog.setCreateTime(logPO.getCreateTime());
        return redeemLog;
    }
}
