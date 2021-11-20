package com.sweetcat.credit.domain.redeemlog.repository;

import com.sweetcat.credit.domain.redeemlog.entity.RedeemLog;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-21:19
 * @version: 1.0
 */
public interface RedeemLogRepository {

    /**
     * 查找分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    public List<RedeemLog> findPage(Integer page, Integer limit);

    /**
     * 添加一条兑换记录，一般由领域事件 RedeemedCommodityEvent
     *
     * @param log
     */
    void addOne(RedeemLog log);

    /**
     * 移除一条兑换记录
     *
     * @param log
     */
    void remove(RedeemLog log);

    /**
     * find redeeemLog by userId and redeemLogId
     * @param userId
     * @param redeemLogId
     * @return
     */
    RedeemLog findOne(Long userId, Long redeemLogId);
}
