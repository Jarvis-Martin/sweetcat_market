package com.sweetcat.credit.interfaces.facade.assembler;

import com.sweetcat.credit.domain.commodity.entity.BaseCommodity;
import com.sweetcat.credit.domain.commodity.entity.Coupon;
import com.sweetcat.credit.interfaces.facade.restdto.CreditCenterCommodityRestDTO;
import com.sweetcat.credit.interfaces.facade.restdto.CreditCenterCouponRestDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/17-20:46
 * @version: 1.0
 */
@Component
public class CommodityAssembler {
    public <T extends BaseCommodity> CreditCenterCommodityRestDTO converterToCreditCenterCommodityRestDTO(T commodity) {
        // 查询的是优惠券时
        if (BaseCommodity.COMMODITYTYPE_COUPON.equals(commodity.getCommodityType())) {
            Coupon coupon = (Coupon) commodity;
            CreditCenterCouponRestDTO couponRestDTO = new CreditCenterCouponRestDTO();
            couponRestDTO.setMarketItemId(coupon.getMarketItemId());
            couponRestDTO.setCreatorId(coupon.getCreator().getCreatorId());
            couponRestDTO.setCreatorName(coupon.getCreator().getCreatorName());
            couponRestDTO.setStock(coupon.getStock());
            couponRestDTO.setCreateTime(coupon.getCreateTime());
            couponRestDTO.setUpdateTime(coupon.getUpdateTime());
            couponRestDTO.setCreditNumber(coupon.getCreditNumber());
            couponRestDTO.setCommodityType(coupon.getCommodityType());
            couponRestDTO.setCouponId(coupon.getCouponId());
            couponRestDTO.setThresholdPrice(coupon.getThresholdPrice());
            couponRestDTO.setCounteractPrice(coupon.getCounteractPrice());
            couponRestDTO.setTargetType(coupon.getTargetType());
            couponRestDTO.setStoreId(coupon.getStoreId());
            couponRestDTO.setStoreName(coupon.getStoreName());
            couponRestDTO.setCommodityId(coupon.getCommodityId());
            couponRestDTO.setCommodityName(coupon.getCommodityName());
            couponRestDTO.setTimeType(coupon.getTimeType());
            couponRestDTO.setValidDuration(coupon.getValidDuration());
            couponRestDTO.setStartTime(coupon.getStartTime());
            couponRestDTO.setDeadline(coupon.getDeadline());
            return couponRestDTO;
        }
        return null;
    }
}
