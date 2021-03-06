package com.sweetcat.credit.application.service;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.domainevent.couponcenter.UserAcquiredCommodityCouponEvent;
import com.sweetcat.commons.domainevent.creditcenter.CreditRedeemedCommodityEvent;
import com.sweetcat.commons.domainevent.creditcenter.UserCreditChangedEvent;
import com.sweetcat.commons.exception.*;
import com.sweetcat.credit.application.command.AddUserCommand;
import com.sweetcat.credit.application.event.publish.DomainEventPublisher;
import com.sweetcat.credit.application.rpc.UserInfoRpc;
import com.sweetcat.credit.domain.commodity.entity.BaseCommodity;
import com.sweetcat.credit.domain.commodity.entity.Coupon;
import com.sweetcat.credit.domain.commodity.repository.CommodityRepository;
import com.sweetcat.credit.domain.commodity.repository.CouponRepository;
import com.sweetcat.credit.domain.creditlog.entity.CreditLog;
import com.sweetcat.credit.domain.user.entity.User;
import com.sweetcat.credit.domain.user.repository.UserRepository;
import com.sweetcat.credit.domain.user.service.UserDomainService;
import com.sweetcat.credit.infrastructure.cache.BloomFilter;
import com.sweetcat.credit.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-19:32
 * @version: 1.0
 */
@Service
public class UserApplicationService {
    Logger logger = LoggerFactory.getLogger(UserApplicationService.class);
    @Value("${base-credit-bonus-per-day}")
    private Integer baseCreditBonusPerDay;
    private VerifyIdFormatService verifyIdFormatService;
    private UserRepository userRepository;
    private UserInfoRpc userInfoRpc;
    private UserDomainService userDomainService;
    private DomainEventPublisher domainEventPublisher;
    private CommodityRepository commodityRepository;
    private CouponRepository couponRepository;
    private BloomFilter bloomFilter;

    @Autowired
    public void setBloomFilter(BloomFilter bloomFilter) {
        this.bloomFilter = bloomFilter;
    }

    @Autowired
    public void setCouponRepository(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Autowired
    public void setCommodityRepository(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    @Autowired
    public void setDomainEventPublisher(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Autowired
    public void setUserDomainService(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
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
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * find user-credit by userIf
     *
     * @param userId
     * @return
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public User findOneByUserId(Long userId) {
        // ??????id
        verifyIdFormatService.verifyIds(userId);
        bloomFilter.verifyIds(userId);
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ???????????????
        checkUser(userInfo);
        // ??????
        return userRepository.findOneByUserId(userId);
    }

    /**
     * ??? db ????????????????????????????????? ???????????? UserRegisteredEvent ????????????
     *
     * @param command
     */
    @Transactional
    public void addOne(AddUserCommand command) {
        Long userId = command.getUserId();
        // ?????? userId
        verifyIdFormatService.verifyIds(userId);
        bloomFilter.add(userId);
        // ?????? User
        User user = new User(userId);
        // ????????????
        user.setCredit(0L);
        user.setCreateTime(command.getCreateTime());
        // ??????db
        userRepository.addOne(user);
    }

    /**
     * ??????????????????
     *
     * @param userId
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void checkIn(Long userId) {
        // ??????id
        verifyIdFormatService.verifyIds(userId);
        // bloomFilter ?????? userId ????????????
        bloomFilter.verifyIds(userId);
        // rpc ???????????????
        UserInfoRpcDTO userInfoRpcDTO = userInfoRpc.getUserInfo(userId);
        // ???????????????
        checkUser(userInfoRpcDTO);
        LocalDateTime now = LocalDateTime.now();
        // ??????????????????
        User user = userRepository.findOneByUserId(userId);
        // ???????????????????????????
        Boolean isCheckedIn = userDomainService.isCheckedIn(user, now);
        // ??????
        if (Boolean.TRUE.equals(isCheckedIn)) {
            throw new CheckedInException(
                    ResponseStatusEnum.CHECKEDIN.getErrorCode(),
                    ResponseStatusEnum.CHECKEDIN.getErrorMessage()
            );
        }
        // ??????
        userDomainService.checkIn(user, now);
        // ??????????????????
        Integer continuousCheckInDays = userDomainService.getContinuousCheckInDays(user);
        continuousCheckInDays = continuousCheckInDays == null ? 0 : continuousCheckInDays;
        // ???????????? ????????????????????????
        long creditBonus = baseCreditBonusPerDay + continuousCheckInDays * 10;
        // ??????????????????
        user.acquire(creditBonus);
        // ?????????db
        userRepository.save(user);
        // ?????????????????? CreditCenterCheckedInEvent
        UserCreditChangedEvent userCreditChangedEvent = new UserCreditChangedEvent(userId);
        inflateUserCreditChangedEvent(creditBonus, userCreditChangedEvent, CreditLog.LOGTYPE_ACQUIRE, "?????????????????????????????????: ", creditBonus, Instant.now().toEpochMilli());
        logger.info("sweetcat-app-credit: ?????????????????? CreditCenterCheckedInEvent ????????????%d" + Instant.now().toEpochMilli());
        domainEventPublisher.syncSend("credit_center_topic:credit_change", userCreditChangedEvent);
    }

    private void inflateUserCreditChangedEvent(long creditBonus, UserCreditChangedEvent userCreditChangedEvent, Integer logtypeAcquire, String s, long creditBonus2, long l) {
        userCreditChangedEvent.setLogType(logtypeAcquire);
        userCreditChangedEvent.setDescription(s + creditBonus);
        userCreditChangedEvent.setCreditNumber(creditBonus2);
        userCreditChangedEvent.setOccurOn(l);
    }

    /**
     * ????????????????????????
     *
     * @param userId
     * @param marketItemId
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void redeemCommodity(Long userId, Long marketItemId, Long createTime) {
        // ??????userId
        verifyIdFormatService.verifyIds(userId, marketItemId);
        // ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ?????? userId ??? marketItemId
        bloomFilter.verifyIds(userId, marketItemId);
        // ???????????????
        checkUser(userInfo);
        // ?????????????????????????????????????????????????????????
        User user = userRepository.findOneByUserId(userId);
        // ????????????????????????????????????
        if (null == user) {
            // ????????????????????????
            addOneUserByUserInfo(userInfo);
        }
        // ?????? baseCommodity
        BaseCommodity commodity = commodityRepository.findOneMarketItemId(marketItemId);
        // ???????????????
        checkCommodity(commodity);
        // ????????????
        long commodityStock = commodity.getStock();
        // ????????????
        if (commodityStock <= 0) {
            throw new StockOutException(
                    ResponseStatusEnum.StockOut.getErrorCode(),
                    ResponseStatusEnum.StockOut.getErrorMessage()
            );
        }
        // ????????????
        long creditNumberOfCommodity = commodity.getCreditNumber();
        // ??????????????????????????????
        long creditOfUser = user.getCredit();
        // ??????????????????
        if (creditNumberOfCommodity > creditOfUser) {
            throw new CreditNotEnoughException(
                    ResponseStatusEnum.CreditNotEnough.getErrorCode(),
                    ResponseStatusEnum.CreditNotEnough.getErrorMessage()
            );
        }
        // ???????????????????????????????????????????????????????????????
        // ???????????????
        if (BaseCommodity.COMMODITYTYPE_COUPON.equals(commodity.getCommodityType())) {
            // ?????????????????????
            commodity.changeStock(-1L);
            // ??????commodity??????
            commodityRepository.save(commodity);
            // ?????????????????? CreditRedeemedCommodityEvent
            CreditRedeemedCommodityEvent creditRedeemedCommodityEvent = new CreditRedeemedCommodityEvent();
            inflateCreditRedeemedCommodityEvent(userId, createTime, commodity, creditNumberOfCommodity, creditRedeemedCommodityEvent);
            logger.info("sweetcat-app-credit: ?????????????????? CreditRedeemedCommodityEvent ????????????{}", Instant.now().toEpochMilli());
            domainEventPublisher.syncSend("credit_center_topic:credit_redeem_coupon", creditRedeemedCommodityEvent);
            // ?????????????????? UserAcquireCommodityCouponEvent
            UserAcquiredCommodityCouponEvent userAcquiredCommodityCouponEvent = new UserAcquiredCommodityCouponEvent();
            // ?????? coupon data ??????????????? UserAcquiredCommodityCouponEvent
            Coupon coupon = couponRepository.findOneByMarketItemId(marketItemId);
            inflateUserAcquiredCommodityCouponEvent(userId, userAcquiredCommodityCouponEvent, coupon);
            logger.info("sweetcat-app-credit: ?????????????????? UserAcquireCommodityCouponEvent ????????????{}", Instant.now().toEpochMilli());
            domainEventPublisher.syncSend("credit_center_topic:user_acquire_commodity_coupon", userAcquiredCommodityCouponEvent);
        }
        // ?????????????????? UserCreditChangedEvent
        UserCreditChangedEvent creditChangedEvent = new UserCreditChangedEvent(userId);
        inflateUserCreditChangedEvent(creditNumberOfCommodity, creditChangedEvent, CreditLog.LOGTYPE_EXPAND, "?????????????????????????????????: ", -creditNumberOfCommodity, createTime);
        // ?????????????????? UserCreditChangedEvent
        domainEventPublisher.syncSend("credit_center_topic:credit_change", creditChangedEvent);
    }

    private void inflateCreditRedeemedCommodityEvent(Long userId, Long createTime, BaseCommodity commodity, long creditNumberOfCommodity, CreditRedeemedCommodityEvent creditRedeemedCommodityEvent) {
        creditRedeemedCommodityEvent.setRedeemUserId(userId);
        creditRedeemedCommodityEvent.setCommodityId(commodity.getMarketItemId());
        creditRedeemedCommodityEvent.setCostCreditNumber(creditNumberOfCommodity);
        creditRedeemedCommodityEvent.setCreateTime(commodity.getCreateTime());
        creditRedeemedCommodityEvent.setOccurOn(createTime);
    }

    private void inflateUserAcquiredCommodityCouponEvent(Long userId, UserAcquiredCommodityCouponEvent userAcquiredCommodityCouponEvent, Coupon coupon) {
        userAcquiredCommodityCouponEvent.setUserId(userId);
        userAcquiredCommodityCouponEvent.setCouponId(coupon.getCouponId());
        userAcquiredCommodityCouponEvent.setThresholdPrice(coupon.getThresholdPrice());
        userAcquiredCommodityCouponEvent.setCounteractPrice(coupon.getCounteractPrice());
        userAcquiredCommodityCouponEvent.setTargetType(coupon.getTargetType());
        userAcquiredCommodityCouponEvent.setStoreId(coupon.getStoreId());
        userAcquiredCommodityCouponEvent.setStoreName(coupon.getStoreName());
        userAcquiredCommodityCouponEvent.setCommodityId(coupon.getCommodityId());
        userAcquiredCommodityCouponEvent.setCommodityPicSmall(coupon.getCommodityPicSmall());
        userAcquiredCommodityCouponEvent.setCommodityName(coupon.getCommodityName());
        userAcquiredCommodityCouponEvent.setTimeType(coupon.getTimeType());
        userAcquiredCommodityCouponEvent.setValidDuration(coupon.getValidDuration());
        userAcquiredCommodityCouponEvent.setStartTime(coupon.getStartTime());
        userAcquiredCommodityCouponEvent.setDeadline(coupon.getDeadline());
        userAcquiredCommodityCouponEvent.setObtainTime(Instant.now().toEpochMilli());
        userAcquiredCommodityCouponEvent.setOccurOn(Instant.now().toEpochMilli());
    }

    private void checkCommodity(BaseCommodity commodity) {
        if (commodity == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
    }

    private void addOneUserByUserInfo(UserInfoRpcDTO userInfo) {
        AddUserCommand addUserCommand = new AddUserCommand(userInfo.getUserId());
        addUserCommand.setCreateTime(userInfo.getCreateTime());
        addOne(addUserCommand);
    }

    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void updateUserCredit(Long userId, Long incrementOfCredit, Long updateTime) {
        // ?????? userId
        verifyIdFormatService.verifyIds(userId);
        bloomFilter.verifyIds(userId);
        // ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ?????????????????????
        checkUser(userInfo);
        // ?????????????????????????????????????????????
        checkAndAddOne(userId, userInfo.getCreateTime());
        // ?????? user
        User user = userRepository.findOneByUserId(userId);
        // ??????user ??????
        user.updateCredit(incrementOfCredit);
        // ??????db
        userRepository.save(user);
    }

    private void checkUser(UserInfoRpcDTO userInfo) {
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * ???????????????????????????????????????????????????
     * @param userId
     * @param userCreateTime
     */
    private void checkAndAddOne(Long userId, Long userCreateTime) {
        // ????????????????????????
        User user = userRepository.findOneByUserId(userId);
        if (user == null) {
            AddUserCommand addUserCommand = new AddUserCommand(userId);
            addUserCommand.setCreateTime(userCreateTime);
            this.addOne(addUserCommand);
        }
    }
}
