package com.sweetcat.couponcenter.infrastructure.factory;

import com.sweetcat.couponcenter.domain.coupon.entity.CommodityCoupon;
import com.sweetcat.couponcenter.domain.coupon.vo.Commodity;
import com.sweetcat.couponcenter.domain.coupon.vo.Creator;
import com.sweetcat.couponcenter.domain.coupon.vo.Store;
import com.sweetcat.couponcenter.infrastructure.po.ConcreteCouponPO;
import com.sweetcat.couponcenter.infrastructure.po.CouponPO;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-21:01
 * @version: 1.0
 */
@Component
public class CommodityCouponFactory {
    public CommodityCoupon create(CouponPO couponPO, ConcreteCouponPO concreteCouponPO) {
        Creator creator = new Creator(couponPO.getCreatorId());
        CommodityCoupon commodityCoupon = new CommodityCoupon(
                couponPO.getCouponId(),
                couponPO.getThresholdPrice(),
                couponPO.getCounteractPrice(),
                creator,
                couponPO.getCreateTime(),
                Instant.now().toEpochMilli(),
                couponPO.getStock(),
                couponPO.getTargetType()
        );
        commodityCoupon.setTargetType(concreteCouponPO.getTargetType());
        Store store = new Store(concreteCouponPO.getStoreId());
        store.setStoreName(concreteCouponPO.getStoreName());
        commodityCoupon.setStore(store);

        Commodity commodity = new Commodity(concreteCouponPO.getCommodityId());
        commodity.setCommodityName(concreteCouponPO.getCommodityName());
        commodity.setCommodityPicSmall(concreteCouponPO.getCommodityPicSmall());
        commodityCoupon.setCommodity(commodity);

        commodityCoupon.setTimeType(concreteCouponPO.getTimeType());
        commodityCoupon.setValidDuration(concreteCouponPO.getValidDuration());
        commodityCoupon.setStartTime(concreteCouponPO.getStartTime());
        commodityCoupon.setDeadline(concreteCouponPO.getDeadline());
        return commodityCoupon;
    }
}
