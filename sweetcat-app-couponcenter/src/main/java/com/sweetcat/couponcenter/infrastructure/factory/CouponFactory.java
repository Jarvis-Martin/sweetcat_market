package com.sweetcat.couponcenter.infrastructure.factory;

import com.sweetcat.couponcenter.domain.coupon.entity.Coupon;
import com.sweetcat.couponcenter.domain.coupon.vo.Creator;
import com.sweetcat.couponcenter.infrastructure.po.CouponPO;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-20:57
 * @version: 1.0
 */
@Component
public class CouponFactory {
    public Coupon create(CouponPO couponPO) {
        Creator creator = new Creator(couponPO.getCreatorId());
        return new Coupon(
                couponPO.getCouponId(),
                couponPO.getThresholdPrice(),
                couponPO.getCounteractPrice(),
                creator,
                couponPO.getCreateTime(),
                Instant.now().toEpochMilli(),
                couponPO.getStock()
        );
    }
}
