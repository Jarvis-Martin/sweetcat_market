package com.sweetcat.couponcenter.domain.coupon.repository;

import com.sweetcat.couponcenter.domain.coupon.entity.Coupon;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-20:40
 * @version: 1.0
 */
public interface CouponRepository {
    /**
     * find coupon by couponId
     * @param couponId
     * @return
     */
    Coupon findByCouponId(Long couponId);
}
