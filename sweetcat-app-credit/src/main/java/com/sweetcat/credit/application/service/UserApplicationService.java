package com.sweetcat.credit.application.service;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.domainevent.creditcenter.CreditCenterCheckedInEvent;
import com.sweetcat.commons.exception.CheckedInException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.credit.application.command.AddUserCommand;
import com.sweetcat.credit.application.event.publish.DomainEventPublisher;
import com.sweetcat.credit.application.rpc.UserInfoRpc;
import com.sweetcat.credit.domain.creditlog.entity.CreditLog;
import com.sweetcat.credit.domain.user.entity.User;
import com.sweetcat.credit.domain.user.repository.UserRepository;
import com.sweetcat.credit.domain.user.service.UserDomainService;
import com.sweetcat.credit.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
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
        int creditBonus = baseCreditBonusPerDay + continuousCheckInDays * 10;
        // 增加用户积分
        user.acquire((long) creditBonus);
        // 保存进db
        userRepository.save(user);
        // 触发领域事件 CreditCenterCheckedInEvent
        CreditCenterCheckedInEvent creditCenterCheckedInEvent = new CreditCenterCheckedInEvent(userId);
        creditCenterCheckedInEvent.setLogType(CreditLog.LOGTYPE_ACQUIRE);
        creditCenterCheckedInEvent.setDescription("积分中心签到，积分收入: " + creditBonus);
        creditCenterCheckedInEvent.setCreditNumber(creditBonus);
        creditCenterCheckedInEvent.setOccurOn(Instant.now().toEpochMilli());
        System.out.println("sweetcat-app-credit: 触发领域事件 CreditCenterCheckedInEvent 时间为：" + Instant.now().toEpochMilli());
        System.out.println(creditCenterCheckedInEvent.getUserId());
        domainEventPublisher.syncSend("credit_center_topic:checkin", creditCenterCheckedInEvent);
    }

}
