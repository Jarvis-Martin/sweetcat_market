package com.sweetcat.couponcenter.domain.coupon.repository;

import com.sweetcat.couponcenter.domain.coupon.entity.UniversalCoupon;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-20:43
 * @version: 1.0
 */
public interface UniversalCouponRepository {
    /**
     * 查询分页数据
     *
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    List<UniversalCoupon> findPage(Integer targetType, Integer page, Integer limit);

    /**
     * add a universal coupon
     *
     * @param universalCoupon
     */
    void addOne(UniversalCoupon universalCoupon);

    UniversalCoupon findOneByCouponId(Long couponId);
}
