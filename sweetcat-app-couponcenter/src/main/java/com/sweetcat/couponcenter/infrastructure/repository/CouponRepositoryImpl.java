package com.sweetcat.couponcenter.infrastructure.repository;

import com.sweetcat.couponcenter.domain.coupon.entity.Coupon;
import com.sweetcat.couponcenter.domain.coupon.repository.CouponRepository;
import com.sweetcat.couponcenter.infrastructure.dao.CouponMapper;
import com.sweetcat.couponcenter.infrastructure.factory.CouponFactory;
import com.sweetcat.couponcenter.infrastructure.po.CouponPO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-20:44
 * @version: 1.0
 */
public class CouponRepositoryImpl implements CouponRepository {
    private CouponMapper couponMapper;
    private CouponFactory couponFactory;

    @Autowired
    public void setCouponFactory(CouponFactory couponFactory) {
        this.couponFactory = couponFactory;
    }

    @Autowired
    public void setCouponMapper(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    /**
     * find coupon by couponId
     * @param couponId
     * @return
     */
    @Override
    public Coupon findByCouponId(Long couponId) {
        CouponPO couponPO = couponMapper.findByCouponId(couponId);
        if (couponPO == null) {
            return null;
        }
        return couponFactory.create(couponPO);
    }
}
