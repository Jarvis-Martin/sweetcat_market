package com.sweetcat.trolley.infrastructure.factory;

import com.sweetcat.trolley.domain.commodity.entity.Commodity;
import com.sweetcat.trolley.domain.commodity.vo.BaseInfo;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-19:27
 * @version: 1.0
 */
@Component
public class CommodityFactory {
    public Commodity create(Map<Object, Object> kv) {
        Commodity commodity = new Commodity();
        commodity.setKey(((String) kv.get("key")));
//        BaseInfo baseInfo = JSONUtils.fromJson(kv.get("baseInfo").toString(), BaseInfo.class);
//        commodity.setBaseInfo(baseInfo);
        commodity.setBaseInfo(((BaseInfo) kv.get("baseInfo")));
        commodity.setCount(((Integer) kv.get("count")).longValue());
        commodity.setUpdateTime(((Long) kv.get("updateTime")));
        return commodity;
    }
}
