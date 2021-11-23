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
        UserCoupon userCoupon = null;
        if (CouponTargetType.TARGETTYPE_COMMODITY.equals(userCouponPO.getTargetType())) {
            userCoupon = new UserCoupon<CommodityCoupon>(userCouponPO.getCouponId());
            CommodityCoupon commodityCoupon = new CommodityCoupon(
                    userCouponPO.getCouponId(),
                    userCouponPO.getThresholdPrice(),
                    userCouponPO.getCounteractPrice()
            );
            concreateCommodityCoupon(commodityCoupon, userCouponPO, couponPO);
            userCoupon.setCoupon(commodityCoupon);
        }
        if (CouponTargetType.TARGETTYPE_UNIVERSAL.equals(userCouponPO.getTargetType())) {
            userCoupon = new UserCoupon<UniversalCoupon>(userCouponPO.getCouponId());
            UniversalCoupon universalCoupon = new UniversalCoupon(
                    userCouponPO.getCouponId(),
                    userCouponPO.getThresholdPrice(),
                    userCouponPO.getCounteractPrice()
            );
            concreateUniversalCoupon(universalCoupon, userCouponPO, couponPO);
            userCoupon.setCoupon(universalCoupon);
        }
        User user = new User(userCouponPO.getUserId());
        userCoupon.setUser(user);
        userCoupon.setObtainTime(userCouponPO.getObtainTime());
        userCoupon.setTargetType(userCouponPO.getTargetType());
        return userCoupon;
    }

    private void concreateCommodityCoupon(CommodityCoupon commodityCoupon, UserCouponPO userCouponPO, CouponPO couponPO) {
        commodityCoupon.setObtainTime(userCouponPO.getObtainTime());
        Store store = new Store(couponPO.getStoreId());
        store.setStoreName(couponPO.getStoreName());
        commodityCoupon.setStore(store);
        Commodity commodity = new Commodity(couponPO.getCommodityId());
        commodity.setCommodityName(couponPO.getCommodityName());
        commodity.setCommodityPicSmall(couponPO.getCommodityPicSmall());
        commodityCoupon.setCommodity(commodity);
        commodityCoupon.setTimeType(couponPO.getTimeType());
        commodityCoupon.setTargetType(userCouponPO.getTargetType());
        commodityCoupon.setValidDuration(couponPO.getValidDuration());
        commodityCoupon.setStartTime(couponPO.getStartTime());
        commodityCoupon.setDeadline(couponPO.getDeadline());
    }

    private void concreateUniversalCoupon(UniversalCoupon universalCoupon, UserCouponPO userCouponPO, CouponPO couponPO) {
        universalCoupon.setObtainTime(userCouponPO.getObtainTime());
        universalCoupon.setTimeType(couponPO.getTimeType());
        universalCoupon.setTargetType(userCouponPO.getTargetType());
        universalCoupon.setValidDuration(couponPO.getValidDuration());
        universalCoupon.setStartTime(couponPO.getStartTime());
        universalCoupon.setDeadline(couponPO.getDeadline());
    }
}
