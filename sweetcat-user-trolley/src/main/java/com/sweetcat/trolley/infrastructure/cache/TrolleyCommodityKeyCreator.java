package com.sweetcat.trolley.infrastructure.cache;

import org.springframework.stereotype.Service;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-16:45
 * @version: 1.0
 */
@Service
public class TrolleyCommodityKeyCreator {
    private final String redisKeyDelimiter = ":";
    /**
     * 购物车 key 格式为：trolley:{user_id}
     */
    private volatile static TrolleyCommodityKeyCreator ins;
    private final String PREFIX = "trolley" + this.redisKeyDelimiter + "commodity" + this.redisKeyDelimiter;

    private TrolleyCommodityKeyCreator() { }

    public static TrolleyCommodityKeyCreator getInstance() {
        if (ins == null) {
            synchronized (TrolleyCommodityKeyCreator.class) {
                if (ins == null) {
                    ins = new TrolleyCommodityKeyCreator();
                }
            }
        }
        return ins;
    }

    public String generateKey(String userId, String commodityId) {
        return this.PREFIX + userId + redisKeyDelimiter + commodityId;
    }
}
