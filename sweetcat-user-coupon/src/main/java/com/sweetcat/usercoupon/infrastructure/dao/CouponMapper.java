package com.sweetcat.usercoupon.infrastructure.dao;

import com.sweetcat.usercoupon.domain.usercoupon.entity.Coupon;
import com.sweetcat.usercoupon.infrastructure.po.CouponPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {

    CouponPO findOneByCouponId(Long couponId);

    void addOne(Coupon coupon);

    void deleteOne(Coupon coupon);
}