package com.sweetcat.usercoupon.domain.usercoupon.repository;

import com.sweetcat.usercoupon.domain.usercoupon.entity.UserCoupon;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/21-21:42
 * @version: 1.0
 */
public interface UserCouponRepository {
    /**
     * find userCoupon log by userId and couponId
     * @param userId
     * @param couponId
     * @return
     */
    UserCoupon findOneByUserIdAndCouponId(Long userId, Long couponId);

    /**
     * find userCoupon page by userId
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    List<UserCoupon> findPageByUserId(Long userId, Integer page, Integer limit);

    /**
     * 移除
     * @param userCoupon
     */
    void remove(UserCoupon userCoupon);

    /**
     * 添加
     * @param userCoupon
     */
    void addOne(UserCoupon userCoupon);

}
