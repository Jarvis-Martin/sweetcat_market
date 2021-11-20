package com.sweetcat.credit.infrastructure.dao;

import com.sweetcat.credit.domain.redeemlog.entity.RedeemLog;
import com.sweetcat.credit.infrastructure.po.RedeemLogPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RedeemLogMapper {

    /**
     * 添加一条兑换记录，一般由领域事件 RedeemedCommodityEvent
     * @param log
     */
    void addOne(RedeemLog log);

    /**
     * 移除一条兑换记录
     * @param log
     */
    void delete(RedeemLog log);

    /**
     * 查找分页数据
     * @param page
     * @param limit
     * @return
     */
    List<RedeemLogPO> findPage(@Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * find redeeemLog by userId and redeemLogId
     * @param userId
     * @param redeemLogId
     * @return
     */
    RedeemLogPO findOne(Long userId, Long redeemLogId);
}