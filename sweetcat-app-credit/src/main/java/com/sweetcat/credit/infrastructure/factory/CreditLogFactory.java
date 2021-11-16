package com.sweetcat.credit.infrastructure.factory;

import com.sweetcat.credit.domain.creditlog.entity.CreditLog;
import com.sweetcat.credit.domain.creditlog.entity.CreditLogUser;
import com.sweetcat.credit.infrastructure.po.CreditLogPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/15-22:30
 * @version: 1.0
 */
@Component
public class CreditLogFactory {
    public CreditLog create(CreditLogPO logPO) {
        CreditLog creditLog = new CreditLog(logPO.getCreditLogId());
        CreditLogUser creditLogUser = new CreditLogUser(logPO.getUserId());
        creditLog.setCreditLogUser(creditLogUser);
        creditLog.setLogType(logPO.getLogType());
        creditLog.setDescription(logPO.getDescription());
        creditLog.setCreditNumber(logPO.getCreditNumber());
        creditLog.setCreateTime(logPO.getCreateTime());
        return creditLog;
    }
}
