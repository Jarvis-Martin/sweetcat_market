package com.sweetcat.trolley.domain.trolley.repository;

import com.sweetcat.trolley.domain.trolley.entity.Trolley;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-20:10
 * @version: 1.0
 */
public interface TrolleyRepository {
    /**
     * 根据 userId 查找trolley
     * @param userId
     * @return
     */
    Trolley findOneByUserIdWithPartOfData(String userId, Integer page, Integer limit);

    /**
     * 向 redis 中加一个 key
     * @param trolley
     */
    void addOne(Trolley trolley);

    /**
     * 移除对应购物车商品在 该list 中的记录
     * @param userId
     * @param commodityId
     */
    void removeOneCommodity(String userId, String commodityId);
}
