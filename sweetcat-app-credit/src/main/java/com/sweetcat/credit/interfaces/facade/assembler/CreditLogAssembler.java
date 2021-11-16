package com.sweetcat.credit.interfaces.facade.assembler;

import com.sweetcat.credit.domain.creditlog.entity.CreditLog;
import com.sweetcat.credit.interfaces.facade.restdto.CreditLogRestDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/16-14:10
 * @version: 1.0
 */
@Component
public class CreditLogAssembler {
    public CreditLogRestDTO converterToCreditLogRestDTO(CreditLog log) {
        CreditLogRestDTO creditLogRestDTO = new CreditLogRestDTO(log.getCreditLogId());
        // CreditLog 的 creditLogUser 放 DTO，为避免，用户接口层越过facade直接操作领域层对象。故此处使用 HashMap 来模拟creditLogUser
        HashMap<String, Object> creditLogUser = new HashMap<>(2);
        creditLogUser.put("creditLogUser", log.getCreditLogUser());
        creditLogRestDTO.setCreditLogUser(creditLogUser);
        creditLogRestDTO.setLogType(log.getLogType());
        creditLogRestDTO.setDescription(log.getDescription());
        creditLogRestDTO.setCreditNumber(log.getCreditNumber());
        creditLogRestDTO.setCreateTime(log.getCreateTime());
        return creditLogRestDTO;
    }
}
