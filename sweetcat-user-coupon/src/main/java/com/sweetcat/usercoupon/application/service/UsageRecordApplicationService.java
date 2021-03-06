package com.sweetcat.usercoupon.application.service;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.domainevent.usercoupon.ConsumedCouponEvent;
import com.sweetcat.commons.exception.CouponInvalidateException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.usercoupon.application.event.publish.DomainEventPublisher;
import com.sweetcat.usercoupon.application.rpc.UserInfoRpc;
import com.sweetcat.usercoupon.domain.usagerecord.entity.UsageRecord;
import com.sweetcat.usercoupon.domain.usagerecord.repository.UsageRecordeRepository;
import com.sweetcat.usercoupon.domain.usercoupon.entity.CommodityCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.Coupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UniversalCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UserCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.repository.UserCouponRepository;
import com.sweetcat.usercoupon.domain.usercoupon.vo.CouponTargetType;
import com.sweetcat.usercoupon.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.usercoupon.infrastructure.service.snowflake_service.SnowFlakeService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-20:43
 * @version: 1.0
 */
@Service
public class UsageRecordApplicationService {
    Logger logger = LoggerFactory.getLogger(UserCouponApplicationService.class);
    private VerifyIdFormatService verifyIdFormatService;
    private UserInfoRpc userInfoRpc;
    private UserCouponRepository userCouponRepository;
    private UsageRecordeRepository usageRecordeRepository;
    private SnowFlakeService snowFlakeService;
    private DomainEventPublisher domainEventPublisher;

    @Autowired
    public void setDomainEventPublisher(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setUsageRecordeRepository(UsageRecordeRepository usageRecordeRepository) {
        this.usageRecordeRepository = usageRecordeRepository;
    }

    @Autowired
    public void setUserCouponRepository(UserCouponRepository userCouponRepository) {
        this.userCouponRepository = userCouponRepository;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    /**
     * ??????(??????)???????????????
     *
     * @param userId
     * @param couponId
     * @param consumeTime
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void consumeOneCoupon(Long userId, Long couponId, Long consumeTime) {
        // ?????? id
        verifyIdFormatService.verifyIds(userId, couponId);
        // ????????????
        checkUser(userId);
        UserCoupon userCoupon = userCouponRepository.findOneByUserIdAndCouponId(userId, couponId);
        // ?????? ????????????????????????
        checkUserCoupon(userCoupon);
        // ??????????????? ??????
        Coupon coupon = userCoupon.getCoupon();
        // ?????? ?????????????????????????????????????????????
        checkCouponValidOrNot(userCoupon, coupon);
        // ????????????id
        long recordId = snowFlakeService.snowflakeId();
        // ?????? usageRecord
        UsageRecord usageRecord = new UsageRecord(recordId, userId, couponId);
        // ?????? usageRecord
        inflateUsageRecord(consumeTime, usageRecord);
        // ??????
        usageRecordeRepository.addOne(usageRecord);
        // ?????????????????? ConsumedCouponEvent
        ConsumedCouponEvent consumedCouponEvent = new ConsumedCouponEvent();
        // ?????? ConsumedCouponEvent
        inflateConsumedCouponEvent(userId, couponId, consumeTime, consumedCouponEvent);
        // log
        logger.info("sweetcat-app-credit: ?????????????????? ConsumedCouponEvent ????????????" + Instant.now().toEpochMilli());
        // ?????????????????? consumedCouponEvent
        domainEventPublisher.syncSend("credit_center_topic:user_acquire_commodity_coupon", consumedCouponEvent);
    }

    private void inflateConsumedCouponEvent(Long userId, Long couponId, Long consumeTime, ConsumedCouponEvent consumedCouponEvent) {
        consumedCouponEvent.setUserId(userId);
        consumedCouponEvent.setCouponId(couponId);
        consumedCouponEvent.setConsumeTime(consumeTime);
        consumedCouponEvent.setOccurOne(consumeTime);
    }

    /**
     * ?????? usageRecord
     *
     * @param consumeTime
     * @param usageRecord
     */
    private void inflateUsageRecord(Long consumeTime, UsageRecord usageRecord) {
        usageRecord.setType(UsageRecord.USAGETYPE_USE);
        usageRecord.setTime(consumeTime);
    }

    /**
     * ???????????????????????????????????????????????????
     * @param userCoupon
     * @param coupon
     */
    private void checkCouponValidOrNot(UserCoupon userCoupon, Coupon coupon) {
        // -- ?????????
        if (CouponTargetType.TARGETTYPE_COMMODITY.equals(userCoupon.getTargetType())) {
            // ????????? commodityCoupon
            CommodityCoupon commodityCoupon = (CommodityCoupon) coupon;
            // ???????????????
            if (!commodityCoupon.isValid()) {
                throw new CouponInvalidateException(
                        ResponseStatusEnum.CouponInvalidated.getErrorCode(),
                        ResponseStatusEnum.CouponInvalidated.getErrorMessage()
                );
            }
        }
        // -- ?????????
        if (CouponTargetType.TARGETTYPE_UNIVERSAL.equals(userCoupon.getTargetType())) {
            // ????????? universalCoupon
            UniversalCoupon universalCoupon = (UniversalCoupon) coupon;
            // ???????????????
            if (!universalCoupon.isValid()) {
                throw new CouponInvalidateException(
                        ResponseStatusEnum.CouponInvalidated.getErrorCode(),
                        ResponseStatusEnum.CouponInvalidated.getErrorMessage()
                );
            }
        }
    }


    /**
     * ???????????????????????????????????????????????????
     *
     * @param userId
     */
    private void checkUser(Long userId) {
        // ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ???????????????
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * ???????????????
     *
     * @param userCoupon
     */
    private void checkUserCoupon(UserCoupon userCoupon) {
        // ????????????
        // ???????????????
        if (userCoupon == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.CouponNotExisted.getErrorCode(),
                    ResponseStatusEnum.CouponNotExisted.getErrorMessage()
            );
        }
    }
}
