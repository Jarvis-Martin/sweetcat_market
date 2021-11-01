package com.sweetcat.commons.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/30-14:49
 * @Version: 1.0
 */
@Component
public class SnowFlakeUtils {
    private long workerId = 0;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    @PostConstruct
    public void init() {
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            System.out.println("当前机器的workerId:{}" + workerId);
        } catch (Exception e) {
            System.out.println("当前机器的workerId获取失败" + e);
            workerId = NetUtil.getLocalhostStr().hashCode();
            System.out.println("当前机器 workId:{}" + workerId);
        }

    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long datacenterId) {
        snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        // 1236610764324864000
        System.out.println(new SnowFlakeUtils().snowflakeId());
    }

}