package com.sweetcat.couponcenter.infrastructure.repository;

import com.sweetcat.couponcenter.domain.coupon.entity.CommodityCoupon;
import com.sweetcat.couponcenter.domain.coupon.repository.CommodityCouponRepository;
import com.sweetcat.couponcenter.infrastructure.dao.ConcreteCouponMapper;
import com.sweetcat.couponcenter.infrastructure.dao.CouponMapper;
import com.sweetcat.couponcenter.infrastructure.factory.CommodityCouponFactory;
import com.sweetcat.couponcenter.infrastructure.po.ConcreteCouponPO;
import com.sweetcat.couponcenter.infrastructure.po.CouponPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-20:46
 * @version: 1.0
 */
@Repository
public class CommodityCouponRepositoryImpl implements CommodityCouponRepository {
    private CouponMapper couponMapper;
    private ConcreteCouponMapper concreteCouponMapper;
    private CommodityCouponFactory commodityCouponFactory;

    @Autowired
    public void setCommodityCouponFactory(CommodityCouponFactory commodityCouponFactory) {
        this.commodityCouponFactory = commodityCouponFactory;
    }

    @Autowired
    public void setCouponMapper(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    @Autowired
    public void setConcreteCouponMapper(ConcreteCouponMapper concreteCouponMapper) {
        this.concreteCouponMapper = concreteCouponMapper;
    }

    /**
     * 查询分页数据
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<CommodityCoupon> findPage(Integer targetType, Integer page, Integer limit) {
        List<ConcreteCouponPO> couponPOPage = concreteCouponMapper.findPage(targetType, page, limit);
        if (couponPOPage == null || couponPOPage.size() <= 0) {
            return null;
        }
        return couponPOPage.stream().collect(
                ArrayList<CommodityCoupon>::new,
                (con, concreteCouponPO) -> {
                    CouponPO couponPO = couponMapper.findByCouponId(concreteCouponPO.getCouponId());
                    con.add(commodityCouponFactory.create(couponPO, concreteCouponPO));
                },
                ArrayList<CommodityCoupon>::addAll
        );
    }


    /**
     * add one commodity coupon
     * @param commodityCoupon
     */
    @Override
    public void addOne(CommodityCoupon commodityCoupon) {
        couponMapper.addOne(commodityCoupon);
        concreteCouponMapper.addOneCommodityCoupon(commodityCoupon);
    }

    @Override
    public CommodityCoupon findOneByCouponId(Long couponId) {
        CouponPO couponPO = couponMapper.findByCouponId(couponId);
        if (couponPO == null) {
            return null;
        }
        ConcreteCouponPO concreteCouponPO = concreteCouponMapper.findOneByCouponId(couponId);
        if (concreteCouponPO == null) {
            return null;
        }
        return commodityCouponFactory.create(couponPO, concreteCouponPO);
    }
}
