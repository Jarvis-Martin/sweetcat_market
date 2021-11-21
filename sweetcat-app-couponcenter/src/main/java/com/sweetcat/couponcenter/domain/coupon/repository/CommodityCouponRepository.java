package com.sweetcat.couponcenter.domain.coupon.repository;

import com.sweetcat.couponcenter.domain.coupon.entity.CommodityCoupon;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-20:41
 * @version: 1.0
 */
public interface CommodityCouponRepository {
    /**
     * 查询分页数据
     *
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    List<CommodityCoupon> findPage(Integer targetType, Integer page, Integer limit);

    /**
     * add one commodity coupon
     * @param commodityCoupon
     */
    void addOne(CommodityCoupon commodityCoupon);
}
