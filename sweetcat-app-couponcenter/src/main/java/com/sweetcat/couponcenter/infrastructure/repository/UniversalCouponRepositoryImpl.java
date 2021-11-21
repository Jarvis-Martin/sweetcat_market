package com.sweetcat.couponcenter.infrastructure.repository;

import com.sweetcat.couponcenter.domain.coupon.entity.UniversalCoupon;
import com.sweetcat.couponcenter.domain.coupon.repository.UniversalCouponRepository;
import com.sweetcat.couponcenter.infrastructure.dao.ConcreteCouponMapper;
import com.sweetcat.couponcenter.infrastructure.dao.CouponMapper;
import com.sweetcat.couponcenter.infrastructure.factory.UniversalCouponFactory;
import com.sweetcat.couponcenter.infrastructure.po.ConcreteCouponPO;
import com.sweetcat.couponcenter.infrastructure.po.CouponPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-21:18
 * @version: 1.0
 */
@Repository
public class UniversalCouponRepositoryImpl implements UniversalCouponRepository {
    private CouponMapper couponMapper;
    private ConcreteCouponMapper concreteCouponMapper;
    private UniversalCouponFactory universalCouponFactory;

    @Autowired
    public void setUniversalCouponFactory(UniversalCouponFactory universalCouponFactory) {
        this.universalCouponFactory = universalCouponFactory;
    }

    @Autowired
    public void setCouponMapper(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    @Autowired
    public void setConcreteCouponMapper(ConcreteCouponMapper concreteCouponMapper) {
        this.concreteCouponMapper = concreteCouponMapper;
    }
    /**
     * 查询分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<UniversalCoupon> findPage(Integer targetType, Integer page, Integer limit) {
        List<ConcreteCouponPO> couponPOPage = concreteCouponMapper.findPage(targetType, page, limit);
        return couponPOPage.stream().collect(
                ArrayList<UniversalCoupon>::new,
                (con, concreteCouponPO) -> {
                    CouponPO couponPO = couponMapper.findByCouponId(concreteCouponPO.getCouponId());
                    con.add(universalCouponFactory.create(couponPO, concreteCouponPO));
                },
                ArrayList<UniversalCoupon>::addAll
        );
    }


    /**
     * add a universal coupon
     *
     * @param universalCoupon
     */
    @Override
    public void addOne(UniversalCoupon universalCoupon) {
        couponMapper.addOne(universalCoupon);
        concreteCouponMapper.addOneUniversalCoupon(universalCoupon);
    }
}
