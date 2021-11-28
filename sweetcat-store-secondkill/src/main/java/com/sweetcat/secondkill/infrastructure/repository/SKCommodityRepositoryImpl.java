package com.sweetcat.secondkill.infrastructure.repository;

import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;
import com.sweetcat.secondkill.domain.commodity.repository.SKCommodityRepository;
import com.sweetcat.secondkill.domain.commodity.vo.SecondKillTimeZone;
import com.sweetcat.secondkill.infrastructure.dao.SKCommodityMapper;
import com.sweetcat.secondkill.infrastructure.factory.SKCommodityFactory;
import com.sweetcat.secondkill.infrastructure.po.SKCommodityPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/27-13:29
 * @version: 1.0
 */
@Repository
public class SKCommodityRepositoryImpl implements SKCommodityRepository {
    private SKCommodityMapper commodityMapper;
    private SKCommodityFactory commodityFactory;

    @Autowired
    public void setCommodityFactory(SKCommodityFactory commodityFactory) {
        this.commodityFactory = commodityFactory;
    }

    @Autowired
    public void setCommodityMapper(SKCommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    /**
     * 增加一件商品记录
     * @param commodity
     */
    @Override
    public void addOne(SKCommodity commodity) {
        commodityMapper.addOne(commodity);
    }

    /**
     * 移除秒杀商品
     * @param commodity
     */
    @Override
    public void removeOne(SKCommodity commodity) {
        commodityMapper.removeOne(commodity);
    }

    /**
     * 保持对秒杀商品的修改
     * @param commodity
     */
    @Override
    public void save(SKCommodity commodity) {
        commodityMapper.updateOne(commodity);
    }

    /**
     * 查找给定时间所在区间内的秒杀商品分页数据
     * @param currentTimeStamp
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<SKCommodity> findPageByTime(Long currentTimeStamp, Integer page, Integer limit) {
        LocalDateTime nowLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTimeStamp), ZoneId.systemDefault());
        int year = nowLocalDateTime.getYear();
        int monthValue = nowLocalDateTime.getMonthValue();
        int dayOfMonth = nowLocalDateTime.getDayOfMonth();
        SecondKillTimeZone.Tuple<Integer, Integer> zone = SecondKillTimeZone.getInstance().zoneOf(Instant.ofEpochMilli(currentTimeStamp));
        Instant startTime = LocalDateTime.of(year, monthValue, dayOfMonth, zone.startTime(), 0).atZone(ZoneId.systemDefault()).toInstant();
        Instant deadline = LocalDateTime.of(year, monthValue, dayOfMonth, zone.deadline(), 59).atZone(ZoneId.systemDefault()).toInstant();
        List<SKCommodityPO> skCommodityPOPage = commodityMapper.findPageByTime(startTime.toEpochMilli(), deadline.toEpochMilli(), page, limit);
        if (skCommodityPOPage == null || skCommodityPOPage.size() <= 0) {
            return null;
        }
        return skCommodityPOPage.stream().collect(
                ArrayList<SKCommodity>::new,
                (con, skCommodityPO) -> con.add(commodityFactory.create(skCommodityPO)),
                ArrayList<SKCommodity>::addAll
        );
    }

    /**
     * 查找秒杀商品的详细信息
     * @param commodityId
     * @return
     */
    @Override
    public SKCommodity findOneByCommodityId(Long commodityId) {
        SKCommodityPO skCommodityPO = commodityMapper.findOneByCommodityId(commodityId);
        if (skCommodityPO == null) {
            return null;
        }
        return commodityFactory.create(skCommodityPO);
    }
}
