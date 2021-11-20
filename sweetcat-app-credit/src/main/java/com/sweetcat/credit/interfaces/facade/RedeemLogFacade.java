package com.sweetcat.credit.interfaces.facade;

import com.sweetcat.credit.application.service.RedeemLogApplicationService;
import com.sweetcat.credit.domain.redeemlog.entity.RedeemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-22:15
 * @version: 1.0
 */
@Component
public class RedeemLogFacade {
    private RedeemLogApplicationService redeemLogApplicationService;

    @Autowired
    public void setRedeemLogApplicationService(RedeemLogApplicationService redeemLogApplicationService) {
        this.redeemLogApplicationService = redeemLogApplicationService;
    }

    /**
     * 查找分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    public List<RedeemLog> findPage(Integer page, Integer limit) {
        return redeemLogApplicationService.findPage(page, limit);
    }

    /**
     * 移除一条兑换记录
     *
     * @param userId
     * @param redeemLogId
     */
    public void remove(Long userId, Long redeemLogId) {
        redeemLogApplicationService.remove(userId, redeemLogId);
    }

}
