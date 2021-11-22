package com.sweetcat.couponcenter.interfaces.facade;

import com.sweetcat.couponcenter.application.service.CouponApplicationService;
import com.sweetcat.couponcenter.domain.coupon.entity.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-11:14
 * @version: 1.0
 */
@Component
public class CouponFacade {
    private CouponApplicationService couponApplicationService;

    @Autowired
    public void setCouponApplicationService(CouponApplicationService couponApplicationService) {
        this.couponApplicationService = couponApplicationService;
    }

    public Coupon findByCouponId(Long couponId) {
        return couponApplicationService.findByCouponId(couponId);
    }

    /**
     * 领取优惠券
     * @param userId
     * @param couponId
     * @return
     */
    public void getOneCoupon(Long userId, Long couponId) {
        couponApplicationService.getOneCoupon(userId, couponId);
    }
}
