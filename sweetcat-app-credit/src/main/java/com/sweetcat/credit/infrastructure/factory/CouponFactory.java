package com.sweetcat.credit.infrastructure.factory;

import com.sweetcat.credit.domain.commodity.entity.Coupon;
import com.sweetcat.credit.domain.commodity.vo.Creator;
import com.sweetcat.credit.infrastructure.po.BaseCommodityPO;
import com.sweetcat.credit.infrastructure.po.CouponPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/17-19:18
 * @version: 1.0
 */
@Component
public class CouponFactory {
    public Coupon create(BaseCommodityPO baseCommodityPO, CouponPO couponPO) {
        Creator creator = new Creator(baseCommodityPO.getCreatorId());
        creator.setCreatorName(baseCommodityPO.getCreatorName());
        Coupon coupon = new Coupon(
                baseCommodityPO.getMarketItemId(),
                creator,
                baseCommodityPO.getStock(),
                baseCommodityPO.getCreateTime(),
                baseCommodityPO.getUpdateTime(),
                baseCommodityPO.getCreditNumber(),
                baseCommodityPO.getCommodityType()
        );
        coupon.setCouponId(couponPO.getCouponId());
        coupon.setThresholdPrice(couponPO.getThresholdPrice());
        coupon.setCounteractPrice(couponPO.getCounteractPrice());
        coupon.setTargetType(couponPO.getTargetType());
        coupon.setStoreId(couponPO.getStoreId());
        coupon.setStoreName(couponPO.getStoreName());
        coupon.setCommodityId(couponPO.getCommodityId());
        coupon.setCommodityPicSmall(couponPO.getCommodityPicSmall());
        coupon.setCommodityName(couponPO.getCommodityName());
        coupon.setTimeType(couponPO.getTimeType());
        coupon.setValidDuration(couponPO.getValidDuration());
        coupon.setStartTime(couponPO.getStartTime());
        coupon.setDeadline(couponPO.getDeadline());
        return coupon;
    }
}
