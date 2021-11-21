package com.sweetcat.couponcenter.infrastructure.dao;

import com.sweetcat.couponcenter.domain.coupon.entity.CommodityCoupon;
import com.sweetcat.couponcenter.domain.coupon.entity.UniversalCoupon;
import com.sweetcat.couponcenter.infrastructure.po.ConcreteCouponPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 86152
 */
@Mapper
public interface ConcreteCouponMapper {

    /**
     * 查询分页数据
     *
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    List<ConcreteCouponPO> findPage(@Param("targetType") Integer targetType, @Param("page") Integer page, @Param("limit") Integer limit);

    void addOneCommodityCoupon(CommodityCoupon coupon);

    void addOneUniversalCoupon(UniversalCoupon coupon);
}