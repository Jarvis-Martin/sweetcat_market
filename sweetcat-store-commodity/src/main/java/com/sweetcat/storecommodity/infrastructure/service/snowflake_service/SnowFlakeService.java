package com.sweetcat.storecommodity.infrastructure.service.snowflake_service;

import com.sweetcat.commons.util.SnowFlakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/30-14:56
 * @Version: 1.0
 */
@Service
public class SnowFlakeService {
    private SnowFlakeUtils snowFlakeUtils;

    @Autowired
    public void setSnowFlakeUtils(SnowFlakeUtils snowFlakeUtils) {
        this.snowFlakeUtils = snowFlakeUtils;
    }

    public synchronized long snowflakeId() {
        return this.snowFlakeUtils.snowflakeId();
    }
}
