package com.sweetcat.trolley.infrastructure.repository;

import com.sweetcat.trolley.domain.commodity.entity.Commodity;
import com.sweetcat.trolley.domain.commodity.repository.CommodityRepository;
import com.sweetcat.trolley.infrastructure.cache.RedisService;
import com.sweetcat.trolley.infrastructure.cache.TrolleyCommodityKeyCreator;
import com.sweetcat.trolley.infrastructure.factory.CommodityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-19:29
 * @version: 1.0
 */
@Repository
public class CommodityRepositoryImpl implements CommodityRepository {
    private RedisService redisService;
    private CommodityFactory commodityFactory;

    @Autowired
    public void setCommodityFactory(CommodityFactory commodityFactory) {
        this.commodityFactory = commodityFactory;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }


    /**
     * 加入一条记录
     * @param commodity
     */
    @Override
    public void addOne(Commodity commodity) {
        HashMap<Object, Object> commodityMap = new HashMap<>(6);
        String key = commodity.getKey();
        commodityMap.put("key", key);
        commodityMap.put("baseInfo", commodity.getBaseInfo());
        commodityMap.put("count", commodity.getCount());
        commodityMap.put("updateTime", commodity.getUpdateTime());
        redisService.hMSet(key, commodityMap);
    }

    /**
     * 根据 key 找到商品信息
     * @param userId
     * @param commodityId
     */
    @Override
    public Commodity findOneByUserIdAndCommodityId(String userId, String commodityId) {
        String key = TrolleyCommodityKeyCreator.getInstance().generateKey(userId, commodityId);
        return findOneBKey(key);
    }

    /**
     * 根据 key 找到商品信息
     * @param key
     */
    @Override
    public Commodity findOneBKey(String key) {
        Map<Object, Object> kv = redisService.hGetAll(key);
        if (kv == null || kv.size() <= 0) {
            return null;
        }
        return commodityFactory.create(kv);
    }

    /**
     * 此方法仅保存commodity的修改
     *
     * @param commodity
     */
    @Override
    public void save(Commodity commodity) {
        HashMap<Object, Object> commodityMap = new HashMap<>(6);
        commodityMap.put("baseInfo", commodity.getBaseInfo());
        commodityMap.put("count", commodity.getCount());
        commodityMap.put("updateTime", commodity.getUpdateTime());
        redisService.hMSet(commodity.getKey(), commodityMap);
    }

    /**
     * 移除一件商品
     * @param commodity
     */
    @Override
    public void removeOne(Commodity commodity) {
        redisService.del(commodity.getKey());
    }
}
