package com.sweetcat.credit.application.service;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.credit.application.command.AddCreditLogCommand;
import com.sweetcat.credit.application.rpc.UserInfoRpc;
import com.sweetcat.credit.domain.creditlog.entity.CreditLog;
import com.sweetcat.credit.domain.creditlog.entity.CreditLogUser;
import com.sweetcat.credit.domain.creditlog.repository.CreditLogRepository;
import com.sweetcat.credit.infrastructure.cache.BloomFilter;
import com.sweetcat.credit.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.credit.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/15-23:11
 * @version: 1.0
 */
@Service
public class CreditLogApplicationService {
    private CreditLogRepository logRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private UserInfoRpc userInfoRpc;
    private BloomFilter bloomFilter;

    @Autowired
    public void setBloomFilter(BloomFilter bloomFilter) {
        this.bloomFilter = bloomFilter;
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
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setLogRepository(CreditLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    /**
     * 添加一项记录
     *
     * @param command
     */
    public void addOne(AddCreditLogCommand command) {
        long userId = command.getUserId();
        // 检查 userId
        verifyIdFormatService.verifyIds(userId);
        bloomFilter.add(userId);
        // 检查用户信息
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 用户不存在
        checkUser(userInfo);
        // 生成 credit log id
        long creditLogId = snowFlakeService.snowflakeId();
        // 创建 creditLog 实例
        CreditLog creditLog = new CreditLog(creditLogId);
        CreditLogUser creditLogUser = new CreditLogUser(userId);
        inflateCreditLog(command, creditLog, creditLogUser);
        // 加入db
        logRepository.addOne(creditLog);
    }

    private void inflateCreditLog(AddCreditLogCommand command, CreditLog creditLog, CreditLogUser creditLogUser) {
        creditLog.setCreditLogUser(creditLogUser);
        creditLog.setLogType(command.getLogType());
        creditLog.setDescription(command.getDescription());
        creditLog.setCreditNumber(command.getCreditNumber());
        creditLog.setCreateTime(command.getCreateTime());
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
     * 获得指定月份的分页数据
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public List<CreditLog> findPageForCurrentMonth(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        // 创建用户
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 用户不存在
        checkUser(userInfo);
        // page，limit 检查
        limit = limit < 0 ? 15 : limit;
        page = page < 0 ? 0 : page * limit;
        // 解析用户给定的时间戳
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        // 获得年月
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        // 获得本月 第一天和 最后 一天 的 时间戳
        long firstDayTimeStampOfGivenTimeStamp =
                LocalDateTime.of(year, month, 1, 0, 0)
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long lastDayTimeStampOfGivenTimeStamp =
                LocalDateTime.of(year, month, dateTime.getDayOfMonth(), 23, 59)
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        // 查位于该区间内的数据据
        return logRepository.findPageBetween(firstDayTimeStampOfGivenTimeStamp, lastDayTimeStampOfGivenTimeStamp, userId, page, limit);
    }

    /**
     * 获得近3个月的积分收支记录的分页数据
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public List<CreditLog> findPageForNearlyThreeMonths(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        // 创建用户
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 用户不存在
        checkUser(userInfo);
        // page，limit 检查
        limit = limit < 0 ? 15 : limit;
        page = page < 0 ? 0 : page * limit;
        // 解析用户给定的时间戳
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        // 获得年月
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        // 获得本月 第一天和 最后 一天 的 时间戳
        long firstDayTimeStampOfGivenTimeStamp =
                LocalDateTime.of(year, month - 3, 1, 0, 0)
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long lastDayTimeStampOfGivenTimeStamp =
                LocalDateTime.of(year, month, dateTime.getDayOfMonth(), 23, 59)
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // 查位于该区间内的数据据
        return logRepository.findPageBetween(firstDayTimeStampOfGivenTimeStamp, lastDayTimeStampOfGivenTimeStamp, userId, page, limit);
    }

    /**
     * 获得本月总收入
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public Long totalIncomeForThisMonth(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        List<CreditLog> pageForCurrentMonth = findPageForCurrentMonth(timestamp, userId, page, limit);
        return pageForCurrentMonth.stream()
                .mapToLong(
                        creditLog -> CreditLog.LOGTYPE_ACQUIRE.equals(creditLog.getLogType()) ? creditLog.getCreditNumber() : 0
                ).sum();
    }

    /**
     * 获得本月总支出
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public Long totalOutComeForThisMonth(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        List<CreditLog> pageForCurrentMonth = findPageForCurrentMonth(timestamp, userId, page, limit);
        return pageForCurrentMonth.stream()
                .mapToLong(
                        creditLog -> CreditLog.LOGTYPE_EXPAND.equals(creditLog.getLogType()) ? creditLog.getCreditNumber() : 0
                ).sum();
    }

    /**
     * 获得近3个月总收入
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public Long totalIncomeForNearlyThreeMonths(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        List<CreditLog> pageForCurrentMonth = findPageForNearlyThreeMonths(timestamp, userId, page, limit);
        return pageForCurrentMonth.stream()
                .mapToLong(
                        creditLog -> CreditLog.LOGTYPE_ACQUIRE.equals(creditLog.getLogType()) ? creditLog.getCreditNumber() : 0
                ).sum();
    }

    /**
     * 获得近3个月总支出
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public Long totalOutComeForNearlyThreeMonths(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        List<CreditLog> pageForCurrentMonth = findPageForNearlyThreeMonths(timestamp, userId, page, limit);
        return pageForCurrentMonth.stream()
                .mapToLong(
                        creditLog -> CreditLog.LOGTYPE_EXPAND.equals(creditLog.getLogType()) ? creditLog.getCreditNumber() : 0
                ).sum();
    }
}