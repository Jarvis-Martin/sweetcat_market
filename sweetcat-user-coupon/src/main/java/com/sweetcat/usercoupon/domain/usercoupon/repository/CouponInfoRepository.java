package com.sweetcat.usercoupon.domain.usercoupon.repository;

import com.sweetcat.usercoupon.domain.usercoupon.entity.Coupon;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-13:20
 * @version: 1.0
 */
public interface CouponInfoRepository {
    /**
     * 添加一条用户所获得的优惠券的基本信息
     * @param coupon
     */
    void addOne(Coupon coupon);

    /**
     * 用户删除其优惠券时，相应删除对应的优惠券信息
     * @param coupon
     */
    void remove(Coupon coupon);
}
