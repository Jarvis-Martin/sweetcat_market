package com.sweetcat.credit.infrastructure.dao;

import com.sweetcat.credit.domain.commodity.entity.Coupon;
import com.sweetcat.credit.infrastructure.po.CouponPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CouponMapper {

    /**
     * find coupon info by market_item_id
     * @param marketItemId
     * @return
     */
    CouponPO findOneByMarketItemId(Long marketItemId);

    /**
     * find data of coupon by targetType of coupon
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    List<CouponPO> findPageByTargetType(@Param("targetType") Long targetType, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * find page data of timeType of coupon
     * @param timeType
     * @return
     */
    List<CouponPO> findPageByTimeType(@Param("timeType") Long timeType, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 添加一种优惠券
     * @param coupon
     */
    void addOne(Coupon coupon);
}