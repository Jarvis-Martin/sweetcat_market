package com.sweetcat.usercoupon.interfaces.facade.assembler;

import com.sweetcat.api.rpcdto.usercoupon.CommodityCouponRpcDTO;
import com.sweetcat.api.rpcdto.usercoupon.CouponInfoRpcDTO;
import com.sweetcat.api.rpcdto.usercoupon.UniversalCouponRpcDTO;
import com.sweetcat.usercoupon.domain.usercoupon.entity.CommodityCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.Coupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UniversalCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UserCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.vo.CouponTargetType;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-22:04
 * @version: 1.0
 */
@Component
public class CouponRpcAssembler {
    public CouponInfoRpcDTO converterToCouponRpcDTO(UserCoupon userCoupon) {
        Coupon coupon = userCoupon.getCoupon();
        if (CouponTargetType.TARGETTYPE_COMMODITY.equals(userCoupon.getTargetType())) {
            CommodityCoupon commodityCoupon = (CommodityCoupon) coupon;
            CommodityCouponRpcDTO commodityCouponRestDTO = new CommodityCouponRpcDTO(
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
        if (CouponTargetType.TARGETTYPE_UNIVERSAL.equals(userCoupon.getTargetType())) {
            UniversalCoupon universalCoupon = (UniversalCoupon) coupon;
            UniversalCouponRpcDTO universalCouponRestDTO = new UniversalCouponRpcDTO(
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
