package com.sweetcat.usercoupon.infrastructure.repository;

import com.sweetcat.usercoupon.domain.usercoupon.entity.UserCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.repository.UserCouponRepository;
import com.sweetcat.usercoupon.infrastructure.dao.CouponMapper;
import com.sweetcat.usercoupon.infrastructure.dao.UserCouponMapper;
import com.sweetcat.usercoupon.infrastructure.factory.UserCouponFactory;
import com.sweetcat.usercoupon.infrastructure.po.CouponPO;
import com.sweetcat.usercoupon.infrastructure.po.UserCouponPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/21-21:45
 * @version: 1.0
 */
@Repository
public class UesrCouponRepositoryImpl implements UserCouponRepository {
    private UserCouponMapper userCouponMapper;
    private UserCouponFactory userCouponFactory;
    private CouponMapper couponMapper;

    @Autowired
    public void setCouponMapper(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    @Autowired
    public void setUserCouponFactory(UserCouponFactory userCouponFactory) {
        this.userCouponFactory = userCouponFactory;
    }

    @Autowired
    public void setUserCouponMapper(UserCouponMapper userCouponMapper) {
        this.userCouponMapper = userCouponMapper;
    }

    /**
     * find userCoupon log by userId and couponId
     * @param userId
     * @param couponId
     * @return
     */
    @Override
    public UserCoupon findOneByUserIdAndCouponId(Long userId, Long couponId) {
        UserCouponPO userCouponPO = userCouponMapper.findOneByUserIdAndCouponId(userId, couponId);
        if (userCouponPO == null) {
            return null;
        }
        CouponPO couponPO = couponMapper.findOneByCouponId(couponId);
        return userCouponFactory.create(userCouponPO, couponPO);
    }

    /**
     * find userCoupon page by userId
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<UserCoupon> findPageByUserId(Long userId, Integer page, Integer limit) {
        List<UserCouponPO> userCouponPOPage = userCouponMapper.findPageByUserId(userId, page, limit);
        if (userCouponPOPage == null || userCouponPOPage.isEmpty()) {
            return Collections.emptyList();
        }
        return userCouponPOPage.stream().collect(
                ArrayList<UserCoupon>::new,
                (con, userCouponPO) -> {
                    CouponPO couponPO = couponMapper.findOneByCouponId(userCouponPO.getCouponId());
                    con.add(userCouponFactory.create(userCouponPO, couponPO));
                },
                ArrayList<UserCoupon>::addAll
        );
    }

    /**
     * 移除
     * @param userCoupon
     */
    @Override
    public void remove(UserCoupon userCoupon) {
        userCouponMapper.deleteOne(userCoupon);
    }

    /**
     * 添加
     * @param userCoupon
     */
    @Override
    public void addOne(UserCoupon userCoupon) {
        userCouponMapper.addOne(userCoupon);
    }
}
