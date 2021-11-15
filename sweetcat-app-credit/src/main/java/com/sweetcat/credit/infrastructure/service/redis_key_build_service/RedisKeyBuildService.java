package com.sweetcat.credit.infrastructure.service.redis_key_build_service;

import org.springframework.stereotype.Service;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/15-12:49
 * @version: 1.0
 */
@Service
public class RedisKeyBuildService {
    /**
     * 根据给定分隔符 delimiter 和 key的组成部分构造 redis key。
     *
     * @param delimiter redis key 的分隔符
     * @param items     构成 key 的组成成分 字符串
     * @return
     */
    public String buildKey(String delimiter, String... items) {
        StringBuilder key = new StringBuilder(items[0]);
        for (int i = 1; i < items.length; i++) {
            key.append(delimiter).append(items[i]);
        }
        return key.toString();
    }
}
