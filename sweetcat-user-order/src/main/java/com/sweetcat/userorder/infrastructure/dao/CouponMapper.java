package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.infrastructure.po.CouponPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 86152
 */
@Mapper
public interface CouponMapper {
    void addOne(CouponPO timeInfo);

    void updateOne(CouponPO timeInfo);

    void deleteOne(CouponPO timeInfo);

    List<CouponPO> findPageByOrderId(@Param("orderId") Long orderId, @Param("page") Integer page, @Param("limit") Integer limit);

    List<CouponPO> findAllByOrderId(@Param("orderId") Long orderId);
}