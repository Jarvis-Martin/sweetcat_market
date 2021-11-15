package com.sweetcat.credit.domain.user.service;

import com.sweetcat.credit.domain.user.entity.User;
import com.sweetcat.credit.infrastructure.cache.RedisService;
import com.sweetcat.credit.infrastructure.service.redis_key_build_service.RedisKeyBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/15-20:28
 * @version: 1.0
 */
@Service
public class UserDomainService {
    private RedisService redisService;
    private RedisKeyBuildService keyBuildService;

    @Autowired
    public void setRedisKeyBuildService(RedisKeyBuildService keyBuildService) {
        this.keyBuildService = keyBuildService;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * 积分中心签到功能: 签到给定日期
     * @return
     */
    public Boolean checkIn(User user, LocalDateTime checkInDateTime) {
        // 获得今天所在年月
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMM");
        // 签到日期对应的年份
        String currentMonth = pattern.format(checkInDateTime);
        // 签到对应的redis bitmap key格式位：user:credit_checkin:{user_id}:{date}, 如 user:credit_checkin:521:202111表示2021年11月
        String redisKey = keyBuildService.buildKey(":", "user", "credit_checkin", user.getUserId().toString(), currentMonth);
        // 签到
        return redisService.setBit(redisKey, checkInDateTime.getDayOfMonth(), true);
    }

    /**
     * 检查 user 在 checkInDateTime 当天是否已签到
     * @param user
     * @param checkInDateTime
     * @return
     */
    public Boolean isCheckedIn(User user,  LocalDateTime checkInDateTime) {
        // 获得今天所在年月
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMM");
        // 签到日期对应的年份
        String currentMonth = pattern.format(checkInDateTime);
        // 签到对应的redis bitmap key格式位：user:credit_checkin:{user_id}:{date}, 如 user:credit_checkin:521:202111表示2021年11月
        String redisKey = keyBuildService.buildKey(":", "user", "credit_checkin", user.getUserId().toString(), currentMonth);
        return redisService.getBit(redisKey, checkInDateTime.getDayOfMonth());
    }
    /**
     * 获得本月连签天数
     * @return
     */
    public Integer getContinuousCheckInDays(User user) {
        // 今天的日期
        LocalDateTime now = LocalDateTime.now();
        // 日期格式化类
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMM");
        // 签到日期对应的年月，用于 rediskey
        String currentMonth = pattern.format(now);
        // 签到对应的redis bitmap key格式位：user:credit_checkin:{user_id}:{date}, 如 user:credit_checkin:521:202111表示2021年11月
        String redisKey = keyBuildService.buildKey(":", "user", "credit_checkin", user.getUserId().toString(), currentMonth);
        // 连签天数
        int checkInDays = 0;
        // 获取从 今天到bitmap偏移量位0的区间内，bitmap 状态
        // 设 今天为 3号，1-3号签到状态为：1已签2未签3已签 => bitmap存储情况为：101
        // bitmap 高位为过去，地位为近期，最后一位
        List<Long> list = redisService.bitField(redisKey, now.getDayOfMonth(), 0);
        if (list != null && list.size() > 0) {
            // 取低位连续不为 0 的个数为连续签到次数，需要考虑当天尚未签到的情况
            long v = list.get(0) == null ? 0 : list.get(0);
            // 循环次数位 本月天数
            for (int i = 0; i < now.getDayOfMonth(); i++) {
                // 右移1位，再左移1位，所值不变，说明未签到，否则已签到
                // 111 >> 1 = 011, 011 << 1 = 110, 110 != 111 可以验证今天用户已签到
                if (v >> 1 << 1 == v) {
                    // 地位位 0 且不是当前，说明连签中断
                    if (i > 0) {
                        break;
                    }
                } else {
                    checkInDays += 1;
                }
                v >>= 1;
            }
        }
        return checkInDays;
    }

    /**
     * 查询用户本月总签到次数
     * @return
     */
    public Integer getTotalCheckInDaysThisMonth(User user) {
        // 获得今天所在年月
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMM");
        // 签到日期对应的年份
        String currentMonth = pattern.format(LocalDate.now());
        // 签到对应的redis bitmap key格式位：user:credit_checkin:{user_id}:{date}, 如 user:credit_checkin:521:202111表示2021年11月
        String redisKey = keyBuildService.buildKey(":", "user", "credit_checkin", user.getUserId().toString(), currentMonth);
        // 插入本月签到次数
        return redisService.bitCount(redisKey);
    }
}
