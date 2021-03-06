package com.sweetcat.couponcenter.application.service;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.domainevent.couponcenter.UserAcquiredCommodityCouponEvent;
import com.sweetcat.commons.exception.CouponNotEnoughException;
import com.sweetcat.commons.exception.CouponNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.couponcenter.application.event.publish.DomainEventPublisher;
import com.sweetcat.couponcenter.application.rpc.UserInfoRpc;
import com.sweetcat.couponcenter.domain.coupon.entity.CommodityCoupon;
import com.sweetcat.couponcenter.domain.coupon.entity.Coupon;
import com.sweetcat.couponcenter.domain.coupon.repository.CommodityCouponRepository;
import com.sweetcat.couponcenter.domain.coupon.repository.CouponRepository;
import com.sweetcat.couponcenter.domain.coupon.vo.CouponTargetType;
import com.sweetcat.couponcenter.infrastructure.cache.BloomFilter;
import com.sweetcat.couponcenter.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-11:16
 * @version: 1.0
 */
@Service
public class CouponApplicationService {
    private CouponRepository couponRepository;
    private CommodityCouponRepository commodityCouponRepository;
    private UserInfoRpc userInfoRpc;
    private DomainEventPublisher domainEventPublisher;
    private VerifyIdFormatService verifyIdFormatService;
    private BloomFilter bloomFilter;

    @Autowired
    public void setBloomFilter(BloomFilter bloomFilter) {
        this.bloomFilter = bloomFilter;
    }

    @Autowired
    public void setDomainEventPublisher(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Autowired
    public void setCommodityCouponRepository(CommodityCouponRepository commodityCouponRepository) {
        this.commodityCouponRepository = commodityCouponRepository;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setCouponRepository(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon findByCouponId(Long couponId) {
        // ?????? id
        verifyIdFormatService.verifyIds(couponId);
        bloomFilter.verifyIds(couponId);
        return couponRepository.findByCouponId(couponId);
    }


    /**
     * ???????????????
     * @param userId
     * @param couponId
     * @return
     */
    public void getOneCoupon(Long userId, Long couponId) {
        // ?????? id
        verifyIdFormatService.verifyIds(userId, couponId);
        // ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ???????????????????????? coupon
        bloomFilter.verifyIds(couponId);
        // ?????? user
        checkUser(userInfo);
        Coupon coupon = couponRepository.findByCouponId(couponId);
        // ???????????????????????????
        checkCoupon(coupon);
        // ???????????????
        if (coupon.getStock() <= 0) {
            throw new CouponNotEnoughException(
                    ResponseStatusEnum.COMMODITYNOTENOUGH.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTENOUGH.getErrorMessage()
            );
        }
        if (CouponTargetType.TARGETTYPE_COMMODITY.equals(coupon.getTargetType())) {
            // ?????????????????????????????????????????????
            publishUserAcquireCommodityCouponEvent(userId, couponId, coupon);
            // ??????????????????
            coupon.stock(coupon.getStock() - 1);
            // ????????????
            couponRepository.save(coupon);
        }
    }

    private void publishUserAcquireCommodityCouponEvent(Long userId, Long couponId, Coupon coupon) {
        CommodityCoupon commodityCoupon = commodityCouponRepository.findOneByCouponId(couponId);
        // ?????????????????? UserAcquireCommodityCouponEvent
        UserAcquiredCommodityCouponEvent userAcquiredCommodityCouponEvent = new UserAcquiredCommodityCouponEvent();
        // ?????? coupon data ??????????????? UserAcquiredCommodityCouponEvent
        userAcquiredCommodityCouponEvent.setUserId(userId);
        userAcquiredCommodityCouponEvent.setCouponId(coupon.getCouponId());
        userAcquiredCommodityCouponEvent.setThresholdPrice(coupon.getThresholdPrice());
        userAcquiredCommodityCouponEvent.setCounteractPrice(coupon.getCounteractPrice());
        userAcquiredCommodityCouponEvent.setTargetType(coupon.getTargetType());
        userAcquiredCommodityCouponEvent.setStoreId(commodityCoupon.getStore().getStoreId());
        userAcquiredCommodityCouponEvent.setStoreName(commodityCoupon.getStore().getStoreName());
        userAcquiredCommodityCouponEvent.setCommodityId(commodityCoupon.getCommodity().getCommodityId());
        userAcquiredCommodityCouponEvent.setCommodityPicSmall(commodityCoupon.getCommodity().getCommodityPicSmall());
        userAcquiredCommodityCouponEvent.setCommodityName(commodityCoupon.getCommodity().getCommodityName());
        userAcquiredCommodityCouponEvent.setTimeType(commodityCoupon.getTimeType());
        userAcquiredCommodityCouponEvent.setValidDuration(commodityCoupon.getValidDuration());
        userAcquiredCommodityCouponEvent.setStartTime(commodityCoupon.getStartTime());
        userAcquiredCommodityCouponEvent.setDeadline(commodityCoupon.getDeadline());
        userAcquiredCommodityCouponEvent.setObtainTime(Instant.now().toEpochMilli());
        userAcquiredCommodityCouponEvent.setOccurOn(Instant.now().toEpochMilli());
        System.out.println("sweetcat-app-credit: ?????????????????? UserAcquireCommodityCouponEvent ????????????" + Instant.now().toEpochMilli());
        domainEventPublisher.syncSend("credit_center_topic:user_acquire_commodity_coupon", userAcquiredCommodityCouponEvent);
    }

    /**
     * ???????????????????????????????????????????????????
     * @param userInfo
     */
    private void checkUser(UserInfoRpcDTO userInfo) {
        // ???????????????
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * ???????????????????????????????????????????????????
     * @param coupon
     */
    private void checkCoupon(Coupon coupon) {
        if (coupon == null) {
            throw new CouponNotExistedException(
                    ResponseStatusEnum.CouponNotExisted.getErrorCode(),
                    ResponseStatusEnum.CouponNotExisted.getErrorMessage()
            );
        }
    }

}
