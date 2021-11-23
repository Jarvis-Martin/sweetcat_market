package com.sweetcat.usercoupon.infrastructure.repository;

import com.sweetcat.usercoupon.domain.usercoupon.entity.Coupon;
import com.sweetcat.usercoupon.domain.usercoupon.repository.CouponInfoRepository;
import com.sweetcat.usercoupon.infrastructure.dao.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-13:22
 * @version: 1.0
 */
@Repository
public class CouponInfoRepositoryImpl implements CouponInfoRepository {
    private CouponMapper couponMapper;

    @Autowired
    public void setCouponMapper(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    /**
     * 添加一条用户所获得的优惠券的基本信息
     * @param coupon
     */
    @Override
    public void addOne(Coupon coupon) {
        couponMapper.addOne(coupon);
    }

    /**
     * 用户删除其优惠券时，相应删除对应的优惠券信息
     * @param coupon
     */
    @Override
    public void remove(Coupon coupon) {
        couponMapper.deleteOne(coupon);
    }
}
