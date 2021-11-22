package com.sweetcat.usercoupon.interfaces.facade;

import com.sweetcat.usercoupon.application.service.UserCouponApplicationService;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-13:41
 * @version: 1.0
 */
@Component
public class UserCouponFacade {
    private UserCouponApplicationService userCouponApplicationService;

    @Autowired
    public void setUserCouponApplicationService(UserCouponApplicationService userCouponApplicationService) {
        this.userCouponApplicationService = userCouponApplicationService;
    }

    /**
     * find userCoupon page by userId
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public List<UserCoupon> findPageByUserId(Long userId, Integer page, Integer limit) {
        return userCouponApplicationService.findPageByUserId(userId, page, limit);
    }
    /**
     * 移除
     * @param userId
     * @param couponId
     */
    public void remove(Long userId, Long couponId) {
        userCouponApplicationService.remove(userId, couponId);
    }
}
