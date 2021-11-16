package com.sweetcat.credit.domain.creditlog.repository;

import com.sweetcat.credit.domain.creditlog.entity.CreditLog;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/15-22:14
 * @version: 1.0
 */
public interface CreditLogRepository {

    /**
     * 添加一项记录
     * @param log
     */
    void addOne(CreditLog log);

    /**
     * 获得指定月份的分页数据
     * @param startDate
     * @param deadline
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    List<CreditLog> findPageBetween(Long startDate, Long deadline,  Long userId, Integer page, Integer limit);

}
