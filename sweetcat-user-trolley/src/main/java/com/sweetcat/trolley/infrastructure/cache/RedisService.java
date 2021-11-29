package com.sweetcat.trolley.infrastructure.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    /**
     * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
     *
     * @param key
     * @param field
     * @param value
     */
    public void hSet(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public void hMSet(String key, Map<Object, Object> values) {
        redisTemplate.opsForHash().putAll(key,values);
    }

    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     *
     * @param key
     * @param field
     * @return
     */
    public String hGet(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param fields
     */
    public void hDel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 实现命令：RPUSH key value
     * @param key
     * @param value
     */
    public void rPush(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 实现命令： ZRANGE key offset size
     * @param key
     * @param offset
     * @param size
     * @return
     */
    public List<Object> lRange(String key, Long offset, Long size) {
        return redisTemplate.opsForList().range(key, offset, size);
    }

    /**
     * 实现命令：LLEN key
     * @param key
     * @return
     */
    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 实现命令：LREM key 0 value
     */
    public void lRemoveOne(String key, Object value) {
        redisTemplate.opsForList().remove(key, 0, value);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public void get(String key) {
        redisTemplate.opsForValue().get(key);
    }

    public void hIncreBy(String key, String field, Integer increment) {
        redisTemplate.opsForHash().increment(key, field, increment);
    }
}
