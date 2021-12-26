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
     * 消费(使用)一张优惠券
     *
     * @param userId
     * @param couponId
     * @param consumeTime
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void consumeOneCoupon(Long userId, Long couponId, Long consumeTime) {
        // 检查 id
        verifyIdFormatService.verifyIds(userId, couponId);
        // 检查用户
        checkUser(userId);
        UserCoupon userCoupon = userCouponRepository.findOneByUserIdAndCouponId(userId, couponId);
        // 检查 优惠券是否已拥有
        checkUserCoupon(userCoupon);
        // 获得优惠券 数据
        Coupon coupon = userCoupon.getCoupon();
        // 检查 优惠券是否有效，无效则直接返回
        checkCouponValidOrNot(userCoupon, coupon);
        // 创建记录id
        long recordId = snowFlakeService.snowflakeId();
        // 构建 usageRecord
        UsageRecord usageRecord = new UsageRecord(recordId, userId, couponId);
        // 填充 usageRecord
        inflateUsageRecord(consumeTime, usageRecord);
        // 入库
        usageRecordeRepository.addOne(usageRecord);
        // 构建领域事件 ConsumedCouponEvent
        ConsumedCouponEvent consumedCouponEvent = new ConsumedCouponEvent();
        // 填充 ConsumedCouponEvent
        inflateConsumedCouponEvent(userId, couponId, consumeTime, consumedCouponEvent);
        // log
        logger.info("sweetcat-app-credit: 触发领域事件 ConsumedCouponEvent 时间为：" + Instant.now().toEpochMilli());
        // 发布领域事件 consumedCouponEvent
        domainEventPublisher.syncSend("credit_center_topic:user_acquire_commodity_coupon", consumedCouponEvent);
    }

    private void inflateConsumedCouponEvent(Long userId, Long couponId, Long consumeTime, ConsumedCouponEvent consumedCouponEvent) {
        consumedCouponEvent.setUserId(userId);
        consumedCouponEvent.setCouponId(couponId);
        consumedCouponEvent.setConsumeTime(consumeTime);
        consumedCouponEvent.setOccurOne(consumeTime);
    }

    /**
     * 填充 usageRecord
     *
     * @param consumeTime
     * @param usageRecord
     */
    private void inflateUsageRecord(Long consumeTime, UsageRecord usageRecord) {
        usageRecord.setType(UsageRecord.USAGETYPE_USE);
        usageRecord.setTime(consumeTime);
    }

    /**
     * 检查优惠券是否有效，无效则直接返回
     * @param userCoupon
     * @param coupon
     */
    private void checkCouponValidOrNot(UserCoupon userCoupon, Coupon coupon) {
        // -- 商品券
        if (CouponTargetType.TARGETTYPE_COMMODITY.equals(userCoupon.getTargetType())) {
            // 强转为 commodityCoupon
            CommodityCoupon commodityCoupon = (CommodityCoupon) coupon;
            // 优惠券失效
            if (!commodityCoupon.isValid()) {
                throw new CouponInvalidateException(
                        ResponseStatusEnum.CouponInvalidated.getErrorCode(),
                        ResponseStatusEnum.CouponInvalidated.getErrorMessage()
                );
            }
        }
        // -- 通用券
        if (CouponTargetType.TARGETTYPE_UNIVERSAL.equals(userCoupon.getTargetType())) {
            // 强转为 universalCoupon
            UniversalCoupon universalCoupon = (UniversalCoupon) coupon;
            // 优惠券失效
            if (!universalCoupon.isValid()) {
                throw new CouponInvalidateException(
                        ResponseStatusEnum.CouponInvalidated.getErrorCode(),
                        ResponseStatusEnum.CouponInvalidated.getErrorMessage()
                );
            }
        }
    }


    /**
     * 检查用户是否存在，不存在则直接返回
     *
     * @param userId
     */
    private void checkUser(Long userId) {
        // 检查用户
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * 检查优惠券
     *
     * @param userCoupon
     */
    private void checkUserCoupon(UserCoupon userCoupon) {
        // 检查用户
        // 用户不存在
        if (userCoupon == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.CouponNotExisted.getErrorCode(),
                    ResponseStatusEnum.CouponNotExisted.getErrorMessage()
            );
        }
    }
}
