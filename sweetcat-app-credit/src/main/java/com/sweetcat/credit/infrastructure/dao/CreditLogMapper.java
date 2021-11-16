package com.sweetcat.credit.infrastructure.dao;

import com.sweetcat.credit.domain.creditlog.entity.CreditLog;
import com.sweetcat.credit.infrastructure.po.CreditLogPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CreditLogMapper {
    /**
     * 添加一项记录
     *
     * @param log
     */
    void addOne(CreditLog log);

    /**
     * 获得位于 startDate 和 deadline 之间的分页数据
     *
     * @param startDate
     * @param deadline
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    List<CreditLogPO> findPageBetween(
            @Param("startDate") Long startDate, @Param("deadline") Long deadline,
            @Param("userId") Long userId, @Param("page") Integer page, @Param("limit") Integer limit);

}