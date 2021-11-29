package com.sweetcat.trolley.domain.commodity.entity;

import com.sweetcat.trolley.domain.commodity.vo.BaseInfo;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-14:45
 * @version: 1.0
 */
@Getter
public class Commodity {
    /**
     * 所属购物车的id
     */
    private String key;

    private BaseInfo baseInfo;

    /**
     * 加购数量
     */
    private Long count;

    /**
     * 更新时间
     */
    private Long updateTime;

    public void setKey(String key) {
        this.key = key;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public void increaseCount() {
        this.setCount(count + 1);
    }

    public void reduceCount() {
        long countAfterReduce = count - 1;
        this.setCount(countAfterReduce <= 0 ? 1 : countAfterReduce);
    }
}
