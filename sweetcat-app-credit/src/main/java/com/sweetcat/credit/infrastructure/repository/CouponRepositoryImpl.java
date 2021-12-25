package com.sweetcat.credit.infrastructure.repository;

import com.sweetcat.credit.domain.commodity.entity.Coupon;
import com.sweetcat.credit.domain.commodity.repository.CouponRepository;
import com.sweetcat.credit.infrastructure.dao.BaseCommodityMapper;
import com.sweetcat.credit.infrastructure.dao.CouponMapper;
import com.sweetcat.credit.infrastructure.factory.CouponFactory;
import com.sweetcat.credit.infrastructure.po.BaseCommodityPO;
import com.sweetcat.credit.infrastructure.po.CouponPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-16:55
 * @version: 1.0
 */
@Repository
public class CouponRepositoryImpl implements CouponRepository {
    private BaseCommodityMapper baseCommodityMapper;
    private CouponMapper couponMapper;
    private CouponFactory couponFactory;

    @Autowired
    public void setBaseCommodityMapper(BaseCommodityMapper baseCommodityMapper) {
        this.baseCommodityMapper = baseCommodityMapper;
    }

    @Autowired
    public void setCouponFactory(CouponFactory couponFactory) {
        this.couponFactory = couponFactory;
    }

    @Autowired
    public void setCouponMapper(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    /**
     * 添加一种优惠券
     * @param coupon
     */
    @Override
    public void addOne(Coupon coupon) {
        couponMapper.addOne(coupon);
    }

    /**
     * find data of coupon by targetType of coupon
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Coupon> findPageByTargetType(@Param("targetType") Long targetType, @Param("page") Integer page, @Param("limit") Integer limit) {
        // 直接查 积分商品优惠券表
        List<CouponPO> couponPOPage = couponMapper.findPageByTargetType(targetType, page, limit);
        // 查询结果为空
        if (couponPOPage == null || couponPOPage.isEmpty()) {
            return Collections.emptyList();
        }
        // coupon PO -> coupon DO
        return couponPOPage.stream().collect(
                ArrayList<Coupon>::new,
                (con, couponPO) -> {
                    // 根据 t_market_commodity_coupon # market_item_id
                    // 找其父表 t_app_credit_market 中和被查询coupon 对应的公共数据记录
                    BaseCommodityPO couponBaseCommodityInfo = baseCommodityMapper.findOneByMarketItemId(couponPO.getMarketItemId());
                    // coupon PO -> coupon DO by couponFactory
                    con.add(couponFactory.create(couponBaseCommodityInfo, couponPO));
                },
                ArrayList::addAll
        );
    }

    /**
     * find page data of timeType of coupon
     * @param timeType
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Coupon> findPageByTimeType(@Param("timeType") Long timeType, @Param("page") Integer page, @Param("limit") Integer limit) {
        List<CouponPO> couponPOPage = couponMapper.findPageByTimeType(timeType, page, limit);
        if (couponPOPage == null) {
            return Collections.emptyList();
        }
        // coupon PO -> coupon DO
        return couponPOPage.stream().collect(
                ArrayList<Coupon>::new,
                (con, couponPO) -> {
                    // 根据 t_market_commodity_coupon # market_item_id
                    // 找其父表 t_app_credit_market 中和被查询coupon 对应的公共数据记录
                    BaseCommodityPO couponBaseCommodityInfo = baseCommodityMapper.findOneByMarketItemId(couponPO.getMarketItemId());
                    // coupon PO -> coupon DO by couponFactory
                    con.add(couponFactory.create(couponBaseCommodityInfo, couponPO));
                },
                ArrayList<Coupon>::addAll
        );
    }

    @Override
    public Coupon findOneByMarketItemId(Long marketItemId) {
        BaseCommodityPO baseCommodityPO = baseCommodityMapper.findOneByMarketItemId(marketItemId);
        if (baseCommodityPO == null) {
            return null;
        }
        CouponPO couponPO = couponMapper.findOneByMarketItemId(marketItemId);
        if (couponPO == null) {
            return null;
        }
        return couponFactory.create(baseCommodityPO, couponPO);
    }
}
