package com.sweetcat.credit.interfaces.facade;

import com.sweetcat.credit.application.command.AddCouponCommand;
import com.sweetcat.credit.application.service.CouponApplicationService;
import com.sweetcat.credit.domain.commodity.entity.Coupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-17:25
 * @version: 1.0
 */
@Component
public class CouponFacade {
    private CouponApplicationService couponApplicationService;

    @Autowired
    public void setCouponApplicationService(CouponApplicationService couponApplicationService) {
        this.couponApplicationService = couponApplicationService;
    }

    /**
     * 添加一种优惠券
     *
     * @param command
     */
    public void addOne(AddCouponCommand command) {
        couponApplicationService.addOne(command);
    }

    /**
     * find data of coupon by targetType of coupon
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    public List<Coupon> findPageByTargetType(@Param("targetType") Long targetType, @Param("page") Integer page, @Param("limit") Integer limit) {
        return couponApplicationService.findPageByTargetType(targetType, page, limit);
    }

    /**
     * find page data of timeType of coupon
     * @param timeType
     * @param page
     * @param limit
     * @return
     */
    public List<Coupon> findPageByTimeType(@Param("timeType") Long timeType, @Param("page") Integer page, @Param("limit") Integer limit) {
        return couponApplicationService.findPageByTimeType(timeType, page, limit);
    }

}
