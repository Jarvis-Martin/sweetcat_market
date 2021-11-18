package com.sweetcat.credit.domain.commodity.repository;

import com.sweetcat.credit.domain.commodity.entity.Coupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-16:54
 * @version: 1.0
 */
public interface CouponRepository {


    /**
     * 添加一种优惠券
     *
     * @param coupon
     */
     void addOne(Coupon coupon);
    /**
     * find data of coupon by targetType of coupon
     *
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    List<Coupon> findPageByTargetType(@Param("targetType") Long targetType, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * find page data of timeType of coupon
     *
     * @param timeType
     * @param page
     * @param limit
     * @return
     */
    List<Coupon> findPageByTimeType(@Param("timeType") Long timeType, @Param("page") Integer page, @Param("limit") Integer limit);

}
