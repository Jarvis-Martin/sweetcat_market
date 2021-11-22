package com.sweetcat.usercoupon.infrastructure.dao;

import com.sweetcat.usercoupon.domain.usercoupon.entity.UserCoupon;
import com.sweetcat.usercoupon.infrastructure.po.UserCouponPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCouponMapper {
    /**
     * find userCoupon page by userId
     *
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    List<UserCouponPO> findPageByUserId(@Param("userId") Long userId, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 移除
     *
     * @param userCoupon
     */
    void deleteOne(UserCoupon userCoupon);

    /**
     * 添加
     *
     * @param userCoupon
     */
    void addOne(UserCoupon userCoupon);


    /**
     * find userCoupon log by userId and couponId
     * @param userId
     * @param couponId
     * @return
     */
    UserCouponPO findOneByUserIdAndCouponId(@Param("userId") Long userId, @Param("couponId") Long couponId);

}