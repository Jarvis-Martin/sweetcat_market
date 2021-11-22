package com.sweetcat.usercoupon.infrastructure.factory;

import com.sweetcat.usercoupon.domain.usercoupon.entity.CommodityCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.Coupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UniversalCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.vo.Commodity;
import com.sweetcat.usercoupon.domain.usercoupon.vo.CouponTargetType;
import com.sweetcat.usercoupon.domain.usercoupon.vo.Store;
import com.sweetcat.usercoupon.infrastructure.po.CouponPO;
import com.sweetcat.usercoupon.infrastructure.po.UserCouponPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-21:58
 * @version: 1.0
*/
@Component
public class CouponFactory {
    public Coupon findOneByCouponId(UserCouponPO userCouponPO, CouponPO couponPO) {
        if (CouponTargetType.TARGETTYPE_COMMODITY.equals(userCouponPO.getTargetType())) {
            CommodityCoupon commodityCoupon = concreateCommodityCoupon(userCouponPO, couponPO);
            return commodityCoupon;
        }
        if (CouponTargetType.TARGETTYPE_UNIVERSAL.equals(userCouponPO.getTargetType())) {
            UniversalCoupon universalCoupon = concreateUniversalCoupon(userCouponPO, couponPO);
            return universalCoupon;
        }
        return null;
    }

    private CommodityCoupon concreateCommodityCoupon(UserCouponPO userCouponPO, CouponPO couponPO) {
        CommodityCoupon commodityCoupon = new CommodityCoupon(
                couponPO.getCouponId(),
                userCouponPO.getThresholdPrice(),
                userCouponPO.getCounteractPrice()
        );
        commodityCoupon.setObtainTime(userCouponPO.getObtainTime());
        Store store = new Store(couponPO.getStoreId());
        store.setStoreName(couponPO.getStoreName());
        commodityCoupon.setStore(store);
        Commodity commodity = new Commodity(couponPO.getCommodityId());
        commodity.setCommodityName(couponPO.getCommodityName());
        commodity.setCommodityPicSmall(commodity.getCommodityPicSmall());
        commodityCoupon.setCommodity(commodity);
        commodityCoupon.setTimeType(couponPO.getTimeType());
        commodityCoupon.setTargetType(userCouponPO.getTargetType());
        commodityCoupon.setValidDuration(couponPO.getValidDuration());
        commodityCoupon.setStartTime(couponPO.getStartTime());
        commodityCoupon.setDeadline(couponPO.getDeadline());
        return commodityCoupon;
    }

    private UniversalCoupon concreateUniversalCoupon(UserCouponPO userCouponPO, CouponPO couponPO) {
        UniversalCoupon universalCoupon = new UniversalCoupon(
                couponPO.getCouponId(),
                userCouponPO.getThresholdPrice(),
                userCouponPO.getCounteractPrice()
        );
        universalCoupon.setObtainTime(userCouponPO.getObtainTime());
        universalCoupon.setTimeType(couponPO.getTimeType());
        universalCoupon.setTargetType(userCouponPO.getTargetType());
        universalCoupon.setValidDuration(couponPO.getValidDuration());
        universalCoupon.setStartTime(couponPO.getStartTime());
        universalCoupon.setDeadline(couponPO.getDeadline());
        return universalCoupon;
    }
}
