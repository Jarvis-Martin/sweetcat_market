package com.sweetcat.couponcenter.interfaces.facade.assembler;

import com.sweetcat.api.rpcdto.couponcenter.CommodityCouponRpcDTO;
import com.sweetcat.api.rpcdto.couponcenter.CouponRpcDTO;
import com.sweetcat.api.rpcdto.couponcenter.UniversalCouponRpcDTO;
import com.sweetcat.couponcenter.domain.coupon.entity.CommodityCoupon;
import com.sweetcat.couponcenter.domain.coupon.entity.Coupon;
import com.sweetcat.couponcenter.domain.coupon.entity.UniversalCoupon;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-11:09
 * @version: 1.0
 */
@Component
public class CouponRpcAssembler {

    public <T extends Coupon> CouponRpcDTO converterToCouponRpcDTO(T coupon) {
        if (coupon instanceof CommodityCoupon) {
            CommodityCoupon commodityCoupon = (CommodityCoupon) coupon;
            CommodityCouponRpcDTO commodityCouponRpcDTO = new CommodityCouponRpcDTO(
                    coupon.getCouponId(),
                    coupon.getThresholdPrice(),
                    coupon.getCounteractPrice(),
                    coupon.getStock()
            );
            commodityCouponRpcDTO.setTargetType(commodityCoupon.getTargetType());
            commodityCouponRpcDTO.setStoreId(commodityCoupon.getStore().getStoreId());
            commodityCouponRpcDTO.setStoreName(commodityCoupon.getStore().getStoreName());
            commodityCouponRpcDTO.setStoreId(commodityCoupon.getStore().getStoreId());
            commodityCouponRpcDTO.setStoreName(commodityCoupon.getStore().getStoreName());
            commodityCouponRpcDTO.setCommodityId(commodityCoupon.getCommodity().getCommodityId());
            commodityCouponRpcDTO.setCommodityPicSmall(commodityCoupon.getCommodity().getCommodityPicSmall());
            commodityCouponRpcDTO.setCommodityName(commodityCoupon.getCommodity().getCommodityName());
            commodityCouponRpcDTO.setTimeType(commodityCoupon.getTimeType());
            commodityCouponRpcDTO.setValidDuration(commodityCoupon.getValidDuration());
            commodityCouponRpcDTO.setStartTime(commodityCoupon.getStartTime());
            commodityCouponRpcDTO.setDeadline(commodityCoupon.getDeadline());
            return commodityCouponRpcDTO;
        }
        if (coupon instanceof UniversalCoupon) {
            UniversalCoupon universalCoupon = (UniversalCoupon) coupon;
            UniversalCouponRpcDTO universalCouponRpcDTO = new UniversalCouponRpcDTO(
                    coupon.getCouponId(),
                    coupon.getThresholdPrice(),
                    coupon.getCounteractPrice(),
                    coupon.getStock()
            );
            universalCouponRpcDTO.setTargetType(universalCoupon.getTargetType());
            universalCouponRpcDTO.setTimeType(universalCoupon.getTimeType());
            universalCouponRpcDTO.setValidDuration(universalCoupon.getValidDuration());
            universalCouponRpcDTO.setStartTime(universalCoupon.getStartTime());
            universalCouponRpcDTO.setDeadline(universalCoupon.getDeadline());
            return universalCouponRpcDTO;
        }
        return null;
    }
}
