package com.sweetcat.user_info.infrastructure.service.redis_service;

import com.sweetcat.commons.exception.RedisSaveFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.Object;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/26-20:27
 * @Version: 1.0
 */
@Component
public class RedisService {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, Object value) throws RedisSaveFailException {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, Long second) throws RedisSaveFailException {
        if (second > 0) {
            redisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    public void setnx(String key, Object value) throws RedisSaveFailException {
        redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public void setnx(String key, Object value, Long second) throws RedisSaveFailException {
        if (second > 0) {
            redisTemplate.opsForValue().setIfAbsent(key, value, second, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 向 zset 中添加元素
     *
     * @param key   key
     * @param value value
     * @param score score
     * @throws RedisSaveFailException redis 存储失败
     */
    public void zSet(String key, Object value, Long score) throws RedisSaveFailException {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public void expire(String key, Long second) throws RedisSaveFailException {
        redisTemplate.expire(key, second, TimeUnit.SECONDS);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
}
