package com.sweetcat.credit.infrastructure.cache;

import com.sweetcat.commons.exception.RedisSaveFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    /**
     * bitmap 数据结构 setbit 操作
     * @param key key
     * @param offset 偏移量，即 从 bitmap 第几位开始
     * @param b 要给给定设置的值。bit只有2种值；0、1；为了限制使用方，故使用 boolean，false=0，true=1
     * @return
     */
    public Boolean setBit(String key, Integer offset, Boolean b) {
        return redisTemplate.opsForValue().setBit(key, offset, b);
    }

    /**
     * bitmap 数据结构 getbit 操作
     * @param key key
     * @param offset 要获取值的偏移量
     * @return offset 对应的位上的值
     */
    public Boolean getBit(String key, Integer offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * 统计 key 对应的 value 中 1 的个数
     * @param key key
     * @return key 对应的 value 中 1 的个数
     */
    public Integer bitCount(String key) {
        return redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes())).intValue();
    }

    /**
     * 操作多字节位域
     * @param key key
     * @param limit 要操作的 bit 的数量
     * @param offset 其实偏移量
     * @return
     */
    public List<Long> bitField(String key, Integer limit, Integer offset) {
        return ((List<Long>) redisTemplate.execute((RedisCallback<List<Long>>) con ->
                con.bitField(key.getBytes(), BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(limit)).valueAt(offset))));
    }

}
