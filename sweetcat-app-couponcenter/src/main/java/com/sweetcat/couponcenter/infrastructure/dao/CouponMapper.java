package com.sweetcat.couponcenter.infrastructure.dao;

import com.sweetcat.couponcenter.domain.coupon.entity.Coupon;
import com.sweetcat.couponcenter.infrastructure.po.CouponPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86152
 */
@Mapper
public interface CouponMapper {

    /**
     * find coupon by couponId
     *
     * @param couponId
     * @return
     */
    CouponPO findByCouponId(Long couponId);

    /**
     * add one commodity coupon
     * @param coupon
     */
    <T extends Coupon> void addOne(T coupon);

    void updateOne(Coupon coupon);
}