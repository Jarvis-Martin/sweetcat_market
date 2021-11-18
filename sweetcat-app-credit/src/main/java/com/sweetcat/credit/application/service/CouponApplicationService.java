package com.sweetcat.credit.application.service;

import com.sweetcat.credit.domain.commodity.entity.Coupon;
import com.sweetcat.credit.domain.commodity.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-17:19
 * @version: 1.0
 */
@Service
public class CouponApplicationService {
    private CouponRepository couponRepository;

    @Autowired
    public void setCouponRepository(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    /**
     * find data of coupon by targetType of coupon
     *
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    public List<Coupon> findPageByTargetType(Long targetType, Integer page, Integer limit) {
        // page limit 检查
        limit = limit == null || limit < 0 ? 15 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // 查询并返回
        return couponRepository.findPageByTargetType(targetType, page, limit);
    }

    /**
     * find page data of timeType of coupon
     *
     * @param timeType
     * @param page
     * @param limit
     * @return
     */
    public List<Coupon> findPageByTimeType(Long timeType, Integer page, Integer limit) {
        // page limit 检查
        limit = limit == null || limit < 0 ? 15 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // 查询并返回
        return couponRepository.findPageByTimeType(timeType, page, limit);
    }

}
