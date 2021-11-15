package com.sweetcat.credit.domain.user.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-16:02
 * @version: 1.0
 */
@Getter
public class User {

    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户持有的积分数量
     */
    private Long credit;
    /**
     * 记录创建时间
     */
    private Long createTime;

    public User(Long userId) {
        this.userId = userId;
    }

    public void setUserId(Long userId) {
        if (userId == null || userId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.userId = userId;
    }

    public void setCredit(Long credit) {
        if (credit == null || credit < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.credit = credit;
    }

    public void setCreateTime(Long createTime) {
        if (createTime == null || createTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.createTime = createTime;
    }

    /**
     * 收入积分
     *
     * @param credit
     */
    public void acquire(Long credit) {
        setCredit(this.credit + credit);
    }

    /**
     * 支出积分
     *
     * @param credit
     */
    public void expend(Long credit) {
        setCredit(this.credit - credit);
    }

//
//    /**
//     * 积分中心签到功能: 签到给定日期
//     * @return
//     */
//    public Boolean checkIn(LocalDateTime checkInDateTime) {
//        // 操作 redis
//        RedisService redisService = new RedisService();
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisService.setRedisTemplate(redisTemplate);
//
//        // 生产 redis key
//        RedisKeyBuildService keyBuildService = new RedisKeyBuildService();
//        // 获得今天所在年月
//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMM");
//        // 签到日期对应的年份
//        String currentMonth = pattern.format(checkInDateTime);
//        // 签到对应的redis bitmap key格式位：user:credit_checkin:{user_id}:{date}, 如 user:credit_checkin:521:202111表示2021年11月
//        String redisKey = keyBuildService.buildKey(":", "user", "credit_checkin", userId.toString(), currentMonth);
//        // 签到
//        return redisService.setBit(redisKey, checkInDateTime.getDayOfMonth(), true);
//    }
//
//    /**
//     * 获得本月连签天数
//     * @return
//     */
//    public Integer getContinuousCheckInDays() {
//        // 操作 redis
//        RedisService redisService = new RedisService();
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisService.setRedisTemplate(redisTemplate);
//
//        // 生产 redis key
//        RedisKeyBuildService keyBuildService = new RedisKeyBuildService();
//        // 今天的日期
//        LocalDateTime now = LocalDateTime.now();
//        // 日期格式化类
//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMM");
//        // 签到日期对应的年月，用于 rediskey
//        String currentMonth = pattern.format(now);
//        // 签到对应的redis bitmap key格式位：user:credit_checkin:{user_id}:{date}, 如 user:credit_checkin:521:202111表示2021年11月
//        String redisKey = keyBuildService.buildKey(":", "user", "credit_checkin", userId.toString(), currentMonth);
//        // 连签天数
//        int checkInDays = 0;
//        // 获取从 今天到bitmap偏移量位0的区间内，bitmap 状态
//        // 设 今天为 3号，1-3号签到状态为：1已签2未签3已签 => bitmap存储情况为：101
//        // bitmap 高位为过去，地位为近期，最后一位
//        List<Long> list = redisService.bitField(redisKey, now.getDayOfMonth(), 0);
//        if (list != null && list.size() > 0) {
//            // 取低位连续不为 0 的个数为连续签到次数，需要考虑当天尚未签到的情况
//            long v = list.get(0) == null ? 0 : list.get(0);
//            // 循环次数位 本月天数
//            for (int i = 0; i < now.getDayOfMonth(); i++) {
//                // 右移1位，再左移1位，所值不变，说明未签到，否则已签到
//                // 111 >> 1 = 011, 011 << 1 = 110, 110 != 111 可以验证今天用户已签到
//                if (v >> 1 << 1 == v) {
//                    // 地位位 0 且不是当前，说明连签中断
//                    if (i > 0) {
//                        break;
//                    }
//                } else {
//                    checkInDays += 1;
//                }
//                v >>= 1;
//            }
//        }
//        return checkInDays;
//    }
//
//    /**
//     * 查询用户本月总签到次数
//     * @return
//     */
//    public Integer getTotalCheckInDaysThisMonth() {
//        // 操作 redis
//        RedisService redisService = new RedisService();
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisService.setRedisTemplate(redisTemplate);
//
//        // 生产 redis key
//        RedisKeyBuildService keyBuildService = new RedisKeyBuildService();
//        // 获得今天所在年月
//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMM");
//        // 签到日期对应的年份
//        String currentMonth = pattern.format(LocalDate.now());
//        // 签到对应的redis bitmap key格式位：user:credit_checkin:{user_id}:{date}, 如 user:credit_checkin:521:202111表示2021年11月
//        String redisKey = keyBuildService.buildKey(":", "user", "credit_checkin", userId.toString(), currentMonth);
//        // 插入本月签到次数
//        return redisService.bitCount(redisKey);
//    }
}
