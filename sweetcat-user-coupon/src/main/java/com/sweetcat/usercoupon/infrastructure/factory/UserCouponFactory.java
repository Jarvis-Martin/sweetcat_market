package com.sweetcat.usercoupon.infrastructure.factory;

import com.sweetcat.usercoupon.domain.usercoupon.entity.*;
import com.sweetcat.usercoupon.domain.usercoupon.vo.Commodity;
import com.sweetcat.usercoupon.domain.usercoupon.vo.CouponTargetType;
import com.sweetcat.usercoupon.domain.usercoupon.vo.Store;
import com.sweetcat.usercoupon.infrastructure.dao.CouponMapper;
import com.sweetcat.usercoupon.infrastructure.po.CouponPO;
import com.sweetcat.usercoupon.infrastructure.po.UserCouponPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/21-21:47
 * @version: 1.0
 */
@Component
public class UserCouponFactory {
    private CouponMapper couponMapper;

    @Autowired
    public void setCouponMapper(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    public UserCoupon create(UserCouponPO userCouponPO, CouponPO couponPO) {
        UserCoupon userCoupon = new UserCoupon(userCouponPO.getCouponId());
        User user = new User(userCouponPO.getUserId());
        userCoupon.setUser(user);
        Coupon coupon = new Coupon(
                userCouponPO.getCouponId(),
                userCouponPO.getThresholdPrice(),
                userCouponPO.getCounteractPrice()
        );
        if (CouponTargetType.TARGETTYPE_COMMODITY.equals(userCouponPO.getTargetType())) {
            concreateCommodityCoupon(userCouponPO, couponPO);
        }
        if (CouponTargetType.TARGETTYPE_UNIVERSAL.equals(userCouponPO.getTargetType())) {
            concreateUniversalCoupon(userCouponPO, couponPO);
        }
        userCoupon.setCoupon(coupon);
        return userCoupon;
    }

    private void concreateCommodityCoupon(UserCouponPO userCouponPO, CouponPO couponPO) {
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
    }

    private void concreateUniversalCoupon(UserCouponPO userCouponPO, CouponPO couponPO) {
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
    }
}
