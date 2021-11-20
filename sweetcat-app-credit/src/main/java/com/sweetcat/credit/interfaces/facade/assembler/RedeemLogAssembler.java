package com.sweetcat.credit.interfaces.facade.assembler;

import com.sweetcat.credit.domain.redeemlog.entity.RedeemLog;
import com.sweetcat.credit.interfaces.facade.restdto.RedeemLogRestDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-22:33
 * @version: 1.0
 */
@Component
public class RedeemLogAssembler {
    public RedeemLogRestDTO converterToRedeemLogRestDTO(RedeemLog redeemLog) {
        RedeemLogRestDTO redeemLogRestDTO = new RedeemLogRestDTO(redeemLog.getRedeemLogId());
        redeemLogRestDTO.setRedeemLogId(redeemLog.getRedeemUser().getRedeemUserId());
        redeemLogRestDTO.setCommodityId(redeemLog.getRedeemedCommodity().getCommodityId());
        redeemLogRestDTO.setCostCreditNumber(redeemLog.getRedeemedCommodity().getCostCreditNumber());
        redeemLogRestDTO.setCreateTime(redeemLog.getCreateTime());
        return redeemLogRestDTO;
    }
}
