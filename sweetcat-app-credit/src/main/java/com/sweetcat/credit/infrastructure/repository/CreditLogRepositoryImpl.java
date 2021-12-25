package com.sweetcat.credit.infrastructure.repository;

import com.sweetcat.credit.domain.creditlog.entity.CreditLog;
import com.sweetcat.credit.domain.creditlog.repository.CreditLogRepository;
import com.sweetcat.credit.infrastructure.dao.CreditLogMapper;
import com.sweetcat.credit.infrastructure.factory.CreditLogFactory;
import com.sweetcat.credit.infrastructure.po.CreditLogPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/15-22:29
 * @version: 1.0
 */
@Repository
public class CreditLogRepositoryImpl implements CreditLogRepository {
    private CreditLogFactory logFactory;
    private CreditLogMapper logMapper;

    @Autowired
    public void setLogMapper(CreditLogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Autowired
    public void setLogFactory(CreditLogFactory logFactory) {
        this.logFactory = logFactory;
    }

    /**
     * 添加一项记录
     * @param log
     */
    @Override
    public void addOne(CreditLog log) {
        logMapper.addOne(log);
    }

    /**
     * 获得指定月份的分页数据
     * @param startDate
     * @param deadline
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<CreditLog> findPageBetween(Long startDate, Long deadline,  Long userId, Integer page, Integer limit) {
        List<CreditLogPO> logPOPage = logMapper.findPageBetween(startDate, deadline, userId, page, limit);
        if (logPOPage == null || logPOPage.isEmpty()) {
            return Collections.emptyList();
        }
        return logPOPage.stream().collect(
                ArrayList<CreditLog>::new,
                (con, creditLogPO) -> con.add(logFactory.create(creditLogPO)),
                ArrayList<CreditLog>::addAll
        );
    }
}
