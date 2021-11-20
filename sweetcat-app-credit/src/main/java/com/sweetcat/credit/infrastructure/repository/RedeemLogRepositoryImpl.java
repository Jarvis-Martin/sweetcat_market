package com.sweetcat.credit.infrastructure.repository;

import com.sweetcat.credit.domain.redeemlog.entity.RedeemLog;
import com.sweetcat.credit.domain.redeemlog.repository.RedeemLogRepository;
import com.sweetcat.credit.infrastructure.dao.RedeemLogMapper;
import com.sweetcat.credit.infrastructure.factory.RedeemLogFactory;
import com.sweetcat.credit.infrastructure.po.RedeemLogPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-21:22
 * @version: 1.0
 */
@Repository
public class RedeemLogRepositoryImpl implements RedeemLogRepository {
    private RedeemLogMapper redeemLogMapper;
    private RedeemLogFactory redeemLogFactory;

    @Autowired
    public void setRedeemLogFactory(RedeemLogFactory redeemLogFactory) {
        this.redeemLogFactory = redeemLogFactory;
    }

    @Autowired
    public void setRedeemLogMapper(RedeemLogMapper redeemLogMapper) {
        this.redeemLogMapper = redeemLogMapper;
    }

    /**
     * find redeeemLog by userId and redeemLogId
     * @param userId
     * @param redeemLogId
     * @return
     */
    @Override
    public RedeemLog findOne(Long userId, Long redeemLogId) {
        RedeemLogPO redeemLogPO = redeemLogMapper.findOne(userId, redeemLogId);
        if (redeemLogPO == null) {
            return null;
        }
        return redeemLogFactory.create(redeemLogPO);
    }

    /**
     * 查找分页数据
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<RedeemLog> findPage(Integer page, Integer limit) {
        List<RedeemLogPO> redeemLogPOPage = redeemLogMapper.findPage(page, limit);
        if (redeemLogPOPage == null || redeemLogPOPage.size() < 0) {
            return null;
        }
        ArrayList<RedeemLog> redeemLogPage = redeemLogPOPage.stream().collect(
                ArrayList<RedeemLog>::new,
                (con, redeemLogPO) -> con.add(redeemLogFactory.create(redeemLogPO)),
                ArrayList::addAll
        );
        return redeemLogPage;
    }

    /**
     * 添加一条兑换记录，一般由领域事件 RedeemedCommodityEvent
     * @param log
     */
    @Override
    public void addOne(RedeemLog log) {
        redeemLogMapper.addOne(log);
    }

    /**
     * 移除一条兑换记录
     * @param log
     */
    @Override
    public void remove(RedeemLog log) {
        redeemLogMapper.delete(log);
    }

}
