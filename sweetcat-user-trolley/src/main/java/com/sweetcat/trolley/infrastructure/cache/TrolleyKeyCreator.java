package com.sweetcat.trolley.infrastructure.cache;

import org.springframework.stereotype.Service;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-16:45
 * @version: 1.0
 */
@Service
public class TrolleyKeyCreator {

    private final String redisKeyDelimiter = ":";
    /**
     * 购物车 key 格式为：trolley:{user_id}
     */
    private volatile static TrolleyKeyCreator ins;
    private final String PREFIX = "trolley" + this.redisKeyDelimiter;

    private TrolleyKeyCreator() { }

    public static TrolleyKeyCreator getInstance() {
        if (ins == null) {
            synchronized (TrolleyKeyCreator.class) {
                if (ins == null) {
                    ins = new TrolleyKeyCreator();
                }
            }
        }
        return ins;
    }

    public String generateKey(String userId) {
        return this.PREFIX + userId;
    }
}
