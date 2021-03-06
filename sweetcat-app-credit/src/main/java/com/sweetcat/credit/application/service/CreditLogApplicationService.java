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
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * ??????????????????
     *
     * @param command
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOne(AddCreditLogCommand command) {
        long userId = command.getUserId();
        // ?????? userId
        verifyIdFormatService.verifyIds(userId);
        bloomFilter.add(userId);
        // ??????????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ???????????????
        checkUser(userInfo);
        // ?????? credit log id
        long creditLogId = snowFlakeService.snowflakeId();
        // ?????? creditLog ??????
        CreditLog creditLog = new CreditLog(creditLogId);
        CreditLogUser creditLogUser = new CreditLogUser(userId);
        inflateCreditLog(command, creditLog, creditLogUser);
        // ??????db
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
     * ?????????????????????????????????
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public List<CreditLog> findPageForCurrentMonth(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        // ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ???????????????
        checkUser(userInfo);
        // page???limit ??????
        limit = limit < 0 ? 15 : limit;
        page = page < 0 ? 0 : page * limit;
        // ??????????????????????????????
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        // ????????????
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        // ???????????? ???????????? ?????? ?????? ??? ?????????
        long firstDayTimeStampOfGivenTimeStamp =
                LocalDateTime.of(year, month, 1, 0, 0)
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long lastDayTimeStampOfGivenTimeStamp =
                LocalDateTime.of(year, month, dateTime.getDayOfMonth(), 23, 59)
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        // ?????????????????????????????????
        return logRepository.findPageBetween(firstDayTimeStampOfGivenTimeStamp, lastDayTimeStampOfGivenTimeStamp, userId, page, limit);
    }

    /**
     * ?????????3??????????????????????????????????????????
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public List<CreditLog> findPageForNearlyThreeMonths(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        // ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ???????????????
        checkUser(userInfo);
        // page???limit ??????
        limit = limit < 0 ? 15 : limit;
        page = page < 0 ? 0 : page * limit;
        // ??????????????????????????????
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        // ????????????
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        // ???????????? ???????????? ?????? ?????? ??? ?????????
        long firstDayTimeStampOfGivenTimeStamp =
                LocalDateTime.of(year, month - 3, 1, 0, 0)
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long lastDayTimeStampOfGivenTimeStamp =
                LocalDateTime.of(year, month, dateTime.getDayOfMonth(), 23, 59)
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // ?????????????????????????????????
        return logRepository.findPageBetween(firstDayTimeStampOfGivenTimeStamp, lastDayTimeStampOfGivenTimeStamp, userId, page, limit);
    }

    /**
     * ?????????????????????
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Transactional
    public Long totalIncomeForThisMonth(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        List<CreditLog> pageForCurrentMonth = findPageForCurrentMonth(timestamp, userId, page, limit);
        return pageForCurrentMonth.stream()
                .mapToLong(
                        creditLog -> CreditLog.LOGTYPE_ACQUIRE.equals(creditLog.getLogType()) ? creditLog.getCreditNumber() : 0
                ).sum();
    }

    /**
     * ?????????????????????
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Transactional
    public Long totalOutComeForThisMonth(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        List<CreditLog> pageForCurrentMonth = findPageForCurrentMonth(timestamp, userId, page, limit);
        return pageForCurrentMonth.stream()
                .mapToLong(
                        creditLog -> CreditLog.LOGTYPE_EXPAND.equals(creditLog.getLogType()) ? creditLog.getCreditNumber() : 0
                ).sum();
    }

    /**
     * ?????????3???????????????
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Transactional
    public Long totalIncomeForNearlyThreeMonths(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        List<CreditLog> pageForCurrentMonth = findPageForNearlyThreeMonths(timestamp, userId, page, limit);
        return pageForCurrentMonth.stream()
                .mapToLong(
                        creditLog -> CreditLog.LOGTYPE_ACQUIRE.equals(creditLog.getLogType()) ? creditLog.getCreditNumber() : 0
                ).sum();
    }

    /**
     * ?????????3???????????????
     *
     * @param timestamp
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Transactional
    public Long totalOutComeForNearlyThreeMonths(Long timestamp, Long userId, Integer page, Integer limit) {
        bloomFilter.verifyIds(userId);
        List<CreditLog> pageForCurrentMonth = findPageForNearlyThreeMonths(timestamp, userId, page, limit);
        return pageForCurrentMonth.stream()
                .mapToLong(
                        creditLog -> CreditLog.LOGTYPE_EXPAND.equals(creditLog.getLogType()) ? creditLog.getCreditNumber() : 0
                ).sum();
    }
}