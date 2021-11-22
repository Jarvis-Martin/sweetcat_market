package com.sweetcat.usercoupon.interfaces.facade.assembler;

import com.sweetcat.usercoupon.domain.usercoupon.entity.CommodityCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.Coupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UniversalCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UserCoupon;
import com.sweetcat.usercoupon.interfaces.facade.restdto.CommodityCouponRestDTO;
import com.sweetcat.usercoupon.interfaces.facade.restdto.CouponRestDTO;
import com.sweetcat.usercoupon.interfaces.facade.restdto.UniversalCouponRestDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-22:04
 * @version: 1.0
 */
@Component
public class CouponRestAssembler {
    public CouponRestDTO converterToCouponRestDTO(UserCoupon userCoupon) {
        Coupon coupon = userCoupon.getCoupon();
        if (coupon instanceof CommodityCoupon) {
            CommodityCoupon commodityCoupon = (CommodityCoupon) coupon;
            CommodityCouponRestDTO commodityCouponRestDTO = new CommodityCouponRestDTO(
                    coupon.getCouponId(),
                    coupon.getThresholdPrice(),
                    coupon.getCounteractPrice(),
                    userCoupon.getObtainTime()
            );
            commodityCouponRestDTO.setTargetType(commodityCoupon.getTargetType());
            commodityCouponRestDTO.setStoreId(commodityCoupon.getStore().getStoreId());
            commodityCouponRestDTO.setStoreName(commodityCoupon.getStore().getStoreName());
            commodityCouponRestDTO.setStoreId(commodityCoupon.getStore().getStoreId());
            commodityCouponRestDTO.setStoreName(commodityCoupon.getStore().getStoreName());
            commodityCouponRestDTO.setCommodityId(commodityCoupon.getCommodity().getCommodityId());
            commodityCouponRestDTO.setCommodityPicSmall(commodityCoupon.getCommodity().getCommodityPicSmall());
            commodityCouponRestDTO.setCommodityName(commodityCoupon.getCommodity().getCommodityName());
            commodityCouponRestDTO.setTimeType(commodityCoupon.getTimeType());
            commodityCouponRestDTO.setValidDuration(commodityCoupon.getValidDuration());
            commodityCouponRestDTO.setStartTime(commodityCoupon.getStartTime());
            commodityCouponRestDTO.setDeadline(commodityCoupon.getDeadline());
            return commodityCouponRestDTO;
        }
        if (coupon instanceof UniversalCoupon) {
            UniversalCoupon universalCoupon = (UniversalCoupon) coupon;
            UniversalCouponRestDTO universalCouponRestDTO = new UniversalCouponRestDTO(
                    coupon.getCouponId(),
                    coupon.getThresholdPrice(),
                    coupon.getCounteractPrice(),
                    userCoupon.getObtainTime()
            );
            universalCouponRestDTO.setTargetType(universalCoupon.getTargetType());
            universalCouponRestDTO.setTimeType(universalCoupon.getTimeType());
            universalCouponRestDTO.setValidDuration(universalCoupon.getValidDuration());
            universalCouponRestDTO.setStartTime(universalCoupon.getStartTime());
            universalCouponRestDTO.setDeadline(universalCoupon.getDeadline());
            return universalCouponRestDTO;
        }
        return null;
    }
}
