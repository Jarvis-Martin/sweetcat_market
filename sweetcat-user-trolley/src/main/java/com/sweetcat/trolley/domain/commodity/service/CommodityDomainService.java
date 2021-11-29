package com.sweetcat.trolley.domain.commodity.service;

import com.sweetcat.trolley.domain.commodity.entity.Commodity;
import com.sweetcat.trolley.infrastructure.cache.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-19:37
 * @version: 1.0
 */
@Service
public class CommodityDomainService {
    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    void increaseCount(Commodity commodity) {
        redisService.hIncreBy(commodity.getKey(), "count", 1);
        redisService.hSet(commodity.getKey(), "updateTime", Instant.now().toEpochMilli());
    }

    void reduceCount(Commodity commodity) {
        redisService.hIncreBy(commodity.getKey(), "count", -1);
        redisService.hSet(commodity.getKey(), "updateTime", Instant.now().toEpochMilli());
    }
}
