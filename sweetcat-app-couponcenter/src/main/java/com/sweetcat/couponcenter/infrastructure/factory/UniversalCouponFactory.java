package com.sweetcat.couponcenter.infrastructure.factory;

import com.sweetcat.couponcenter.domain.coupon.entity.UniversalCoupon;
import com.sweetcat.couponcenter.domain.coupon.vo.Creator;
import com.sweetcat.couponcenter.infrastructure.po.ConcreteCouponPO;
import com.sweetcat.couponcenter.infrastructure.po.CouponPO;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-21:14
 * @version: 1.0
 */
@Component
public class UniversalCouponFactory {
    public UniversalCoupon create(CouponPO couponPO, ConcreteCouponPO concreteCouponPO) {
        Creator creator = new Creator(couponPO.getCreatorId());
        return new UniversalCoupon(
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
