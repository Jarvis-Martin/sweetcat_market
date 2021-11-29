package com.sweetcat.trolley.domain.service;

import com.sweetcat.trolley.domain.commodity.entity.Commodity;
import com.sweetcat.trolley.domain.trolley.entity.Trolley;
import com.sweetcat.trolley.infrastructure.cache.RedisService;
import com.sweetcat.trolley.infrastructure.cache.TrolleyKeyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-21:39
 * @version: 1.0
 */
@Service
public class AddOneCommodityToTrolleyService {
    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    public void addOneCommodityToTrolleyService(Trolley trolley, Commodity commodity) {
        String trolleyId = TrolleyKeyCreator.getInstance().generateKey(trolley.getTrolleyId().toString());
        redisService.rPush(trolleyId, commodity.getKey());
    }
}
