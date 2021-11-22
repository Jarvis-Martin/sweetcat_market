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
import com.sweetcat.credit.domain.redeemlog.repository.RedeemLogRepository;
import com.sweetcat.credit.domain.user.entity.User;
import com.sweetcat.credit.domain.user.repository.UserRepository;
import com.sweetcat.credit.domain.user.service.UserDomainService;
import com.sweetcat.credit.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.credit.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    @Value("${base-credit-bonus-per-day}")
    private Integer baseCreditBonusPerDay;
    private VerifyIdFormatService verifyIdFormatService;
    private UserRepository userRepository;
    private UserInfoRpc userInfoRpc;
    private UserDomainService userDomainService;
    private DomainEventPublisher domainEventPublisher;
    private CommodityRepository commodityRepository;
    private RedeemLogRepository redeemLogRepository;
    private SnowFlakeService snowFlakeService;
    private CouponRepository couponRepository;

    @Autowired
    public void setCouponRepository(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setRedeemLogRepository(RedeemLogRepository redeemLogRepository) {
        this.redeemLogRepository = redeemLogRepository;
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
    public User findOneByUserId(Long userId) {
        // 检查id
        verifyIdFormatService.verifyIds(userId);
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 查找
        return userRepository.findOneByUserId(userId);
    }

    /**
     * 向 db 中加入一条记录，一般由 领域事件 UserRegisteredEvent 触发执行
     *
     * @param command
     */
    public void addOne(AddUserCommand command) {
        Long userId = command.getUserId();
        // 检查 userId
        verifyIdFormatService.verifyIds(userId);
        // 创建 User
        User user = new User(userId);
        // 填充数据
        user.setCredit(0L);
        user.setCreateTime(command.getCreateTime());
        // 加入db
        userRepository.addOne(user);
    }

    /**
     * 用户签到功能
     *
     * @param userId
     */
    public void checkIn(Long userId) {
        // 检查id
        verifyIdFormatService.verifyIds(userId);
        // rpc 查用户信息
        UserInfoRpcDTO userInfoRpcDTO = userInfoRpc.getUserInfo(userId);
        // 用户不存在
        if (userInfoRpcDTO == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        LocalDateTime now = LocalDateTime.now();
        // 找到用户记录
        User user = userRepository.findOneByUserId(userId);
        // 判断今天是否已签到
        Boolean isCheckedIn = userDomainService.isCheckedIn(user, now);
        // 已签
        if (isCheckedIn) {
            throw new CheckedInException(
                    ResponseStatusEnum.CHECKEDIN.getErrorCode(),
                    ResponseStatusEnum.CHECKEDIN.getErrorMessage()
            );
        }
        // 签到
        userDomainService.checkIn(user, now);
        // 用户连签天数
        Integer continuousCheckInDays = userDomainService.getContinuousCheckInDays(user);
        continuousCheckInDays = continuousCheckInDays == null ? 0 : continuousCheckInDays;
        // 计算今天 签到所得积分数量
        long creditBonus = baseCreditBonusPerDay + continuousCheckInDays * 10;
        // 增加用户积分
        user.acquire((long) creditBonus);
        // 保存进db
        userRepository.save(user);
        // 触发领域事件 CreditCenterCheckedInEvent
        UserCreditChangedEvent userCreditChangedEvent = new UserCreditChangedEvent(userId);
        userCreditChangedEvent.setLogType(CreditLog.LOGTYPE_ACQUIRE);
        userCreditChangedEvent.setDescription("积分中心签到，积分收入: " + creditBonus);
        userCreditChangedEvent.setCreditNumber(creditBonus);
        userCreditChangedEvent.setOccurOn(Instant.now().toEpochMilli());
        System.out.println("sweetcat-app-credit: 触发领域事件 CreditCenterCheckedInEvent 时间为：" + Instant.now().toEpochMilli());
        domainEventPublisher.syncSend("credit_center_topic:credit_change", userCreditChangedEvent);
    }

    /**
     * 用户兑换兑换商品
     *
     * @param userId
     * @param marketItemId
     */
    public void redeemCommodity(Long userId, Long marketItemId, Long createTime) {
        // 检查userId
        verifyIdFormatService.verifyIds(userId, marketItemId);
        // 检查用户
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 检查用户在积分商城微服务中是否存在记录
        User user = userRepository.findOneByUserId(userId);
        // 积分商城不存在该用户记录
        if (user == null) {
            // 添加一个用户记录
            addOneUserByUserInfo(userInfo);
        }
        // 检查 baseCommodity
        BaseCommodity commodity = commodityRepository.findOneMarketItemId(marketItemId);
        // 商品不存在
        if (commodity == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
        // 库存检查
        Long commodityStock = commodity.getStock();
        // 库存不足
        if (!(commodityStock.compareTo(0L) > 0)) {
            throw new StockOutException(
                    ResponseStatusEnum.StockOut.getErrorCode(),
                    ResponseStatusEnum.StockOut.getErrorMessage()
            );
        }
        // 积分检查
        Long creditNumberOfCommodity = commodity.getCreditNumber();
        // 判断用户积分是否充足
        Long creditOfUser = user.getCredit();
        // 用户积分不足
        if (creditNumberOfCommodity.compareTo(creditOfUser) > 0) {
            throw new CreditNotEnoughException(
                    ResponseStatusEnum.CreditNotEnough.getErrorCode(),
                    ResponseStatusEnum.CreditNotEnough.getErrorMessage()
            );
        }
        // 根据用户兑换的商品的类型发送对应的领域事件
        // 兑换优惠券
        if (BaseCommodity.COMMODITYTYPE_COUPON.equals(commodity.getCommodityType())) {
            // 扣减优惠券库存
            commodity.changeStock(-1L);
            // 保存commodity记录
            commodityRepository.save(commodity);
            // 构建领域事件 CreditRedeemedCommodityEvent
            CreditRedeemedCommodityEvent creditRedeemedCommodityEvent = new CreditRedeemedCommodityEvent();
            creditRedeemedCommodityEvent.setRedeemUserId(userId);
            creditRedeemedCommodityEvent.setCommodityId(commodity.getMarketItemId());
            creditRedeemedCommodityEvent.setCostCreditNumber(creditNumberOfCommodity);
            creditRedeemedCommodityEvent.setCreateTime(commodity.getCreateTime());
            creditRedeemedCommodityEvent.setOccurOn(createTime);
            System.out.println("sweetcat-app-credit: 触发领域事件 CreditRedeemedCommodityEvent 时间为：" + Instant.now().toEpochMilli());
            domainEventPublisher.syncSend("credit_center_topic:credit_redeem_coupon", creditRedeemedCommodityEvent);
            // 构建领域时间 UserAcquireCommodityCouponEvent
            UserAcquiredCommodityCouponEvent userAcquiredCommodityCouponEvent = new UserAcquiredCommodityCouponEvent();
            // 获得 coupon data 以便于填充 UserAcquiredCommodityCouponEvent
            Coupon coupon = couponRepository.findOneByMarketItemId(marketItemId);
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
            System.out.println("sweetcat-app-credit: 触发领域事件 UserAcquireCommodityCouponEvent 时间为：" + Instant.now().toEpochMilli());
            domainEventPublisher.syncSend("credit_center_topic:user_acquire_commodity_coupon", userAcquiredCommodityCouponEvent);
        }
        // 构建领域事件 UserCreditChangedEvent
        UserCreditChangedEvent creditChangedEvent = new UserCreditChangedEvent(userId);
        creditChangedEvent.setLogType(CreditLog.LOGTYPE_EXPAND);
        creditChangedEvent.setDescription("兑换优惠商品，支出积分: " + creditNumberOfCommodity);
        creditChangedEvent.setCreditNumber(-creditNumberOfCommodity);
        creditChangedEvent.setOccurOn(createTime);
        // 触发领域事件 UserCreditChangedEvent
        domainEventPublisher.syncSend("credit_center_topic:credit_change", creditChangedEvent);
    }

    private void addOneUserByUserInfo(UserInfoRpcDTO userInfo) {
        AddUserCommand addUserCommand = new AddUserCommand(userInfo.getUserId());
        addUserCommand.setCreateTime(userInfo.getCreateTime());
        addOne(addUserCommand);
    }

    public void updateUserCredit(Long userId, Long incrementOfCredit, Long updateTime) {
        // 检查 userId
        verifyIdFormatService.verifyIds(userId);
        // 检查用户
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 用户账号不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 检查并判断是否需要新增一条记录
        checkAndAddOne(userId, userInfo.getCreateTime());
        // 找到 user
        User user = userRepository.findOneByUserId(userId);
        // 根据user 积分
        user.updateCredit(incrementOfCredit);
        // 存入db
        userRepository.save(user);
    }

    /**
     * 检查账号是否存在，不存在则新增一条
     * @param userId
     * @param userCreateTime
     */
    private void checkAndAddOne(Long userId, Long userCreateTime) {
        // 创建本地用户记录
        User user = userRepository.findOneByUserId(userId);
        if (user == null) {
            AddUserCommand addUserCommand = new AddUserCommand(userId);
            addUserCommand.setCreateTime(userCreateTime);
            this.addOne(addUserCommand);
        }
    }
}
