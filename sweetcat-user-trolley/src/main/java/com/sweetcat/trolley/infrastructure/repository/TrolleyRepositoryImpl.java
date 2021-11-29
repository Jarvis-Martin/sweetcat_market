package com.sweetcat.trolley.infrastructure.repository;

import com.sweetcat.trolley.domain.trolley.entity.Trolley;
import com.sweetcat.trolley.domain.trolley.repository.TrolleyRepository;
import com.sweetcat.trolley.infrastructure.cache.RedisService;
import com.sweetcat.trolley.infrastructure.cache.TrolleyCommodityKeyCreator;
import com.sweetcat.trolley.infrastructure.cache.TrolleyKeyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-20:16
 * @version: 1.0
 */
@Repository
public class TrolleyRepositoryImpl implements TrolleyRepository {
    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * 根据 userId 查找trolley
     * @param userId
     * @return
     */
    @Override
    public Trolley findOneByUserIdWithPartOfData(String userId, Integer page, Integer limit) {
        // 构建 trolley
        Trolley trolley = new Trolley(Long.valueOf(userId));
        // 构建 trolleyKey 用于查找 trolley 信息
        String trolleyKey = TrolleyKeyCreator.getInstance().generateKey(userId);
        // 查询 该 trolley 里中所有的商品信息对应的 keys
        List<Object> keys = redisService.lRange(trolleyKey, page.longValue(), limit.longValue());
        // 填充 TrolleyId
        trolley.setTrolleyId(Long.valueOf(userId));
        // List<object>: keys(redis返回的类型) -> List<String>: keys(业务需要的类型)
        ArrayList<String> keyStrings = keys.stream().collect(
                ArrayList<String>::new,
                (con, key) -> con.add(((String) key)),
                ArrayList<String>::addAll
        );
        // 填充 CommodityKeys
        trolley.setCommodityKeys(keyStrings);
        return trolley;
    }

    /**
     * 向 redis 中加一个 key
     * @param trolley
     */
    @Override
    public void addOne(Trolley trolley) {
        String key = TrolleyKeyCreator.getInstance().generateKey(trolley.getTrolleyId().toString());
        redisService.rPush(key, null);
    }


    /**
     * 移除对应购物车商品在 该list 中的记录
     * @param userId
     * @param commodityId
     */
    @Override
    public void removeOneCommodity(String userId, String commodityId) {
        String trolleyKey = TrolleyKeyCreator.getInstance().generateKey(userId);
        String commodityKey = TrolleyCommodityKeyCreator.getInstance().generateKey(userId, commodityId);
        redisService.lRemoveOne(trolleyKey, commodityKey);
    }
}
