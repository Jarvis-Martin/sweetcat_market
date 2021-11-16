package com.sweetcat.credit.interfaces.facade;

import com.sweetcat.credit.application.service.CreditLogApplicationService;
import com.sweetcat.credit.domain.creditlog.entity.CreditLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/16-13:43
 * @version: 1.0
 */
@Component
public class CreditLogFacade {
    private CreditLogApplicationService logApplicationService;

    @Autowired
    public void setLogApplicationService(CreditLogApplicationService logApplicationService) {
        this.logApplicationService = logApplicationService;
    }

    /**
     * 获得指定月份的分页数据
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public List<CreditLog> findPageForCurrentMonth(Long timestamp, Long userId, Integer page, Integer limit) {
        return logApplicationService.findPageForCurrentMonth(timestamp, userId, page, limit);
    }

    /**
     * 获得近3个月的积分收支记录的分页数据
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public List<CreditLog> findPageForNearlyThreeMonths(Long timestamp, Long userId, Integer page, Integer limit) {
        return logApplicationService.findPageForNearlyThreeMonths(timestamp, userId, page, limit);
    }



    /**
     * 获得本月总收入
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public Long totalIncomeForThisMonth(Long timestamp, Long userId, Integer page, Integer limit) {
        return logApplicationService.totalIncomeForThisMonth(timestamp, userId, page, limit);

    }

    /**
     * 获得本月总支出
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public Long totalOutComeForThisMonth(Long timestamp, Long userId, Integer page, Integer limit) {
        return logApplicationService.totalOutComeForThisMonth(timestamp, userId, page, limit);

    }

    /**
     * 获得近3个月总收入
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public Long totalIncomeForNearlyThreeMonths(Long timestamp, Long userId, Integer page, Integer limit) {
        return logApplicationService.totalIncomeForNearlyThreeMonths(timestamp, userId, page, limit);

    }

    /**
     * 获得近3个月总支出
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public Long totalOutComeForNearlyThreeMonths(Long timestamp, Long userId, Integer page, Integer limit) {
        return logApplicationService.totalOutComeForNearlyThreeMonths(timestamp, userId, page, limit);

    }
}
