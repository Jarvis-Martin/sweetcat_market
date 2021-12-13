package com.sweetcat.credit.infrastructure.cache;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.RecodeNotExistedException;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/13-13:49
 * @version: 1.0
 */
@Component
public class BloomFilter {
    private final RBloomFilter<String> bloomFilter;

    @Autowired
    public BloomFilter(RedissonClient redissonClient,
                       @Value("${spring.application.name}") String applicationName) {
        bloomFilter = redissonClient.getBloomFilter(applicationName);
        bloomFilter.tryInit(100000, 0.03);
    }

    public void add(Long ...ids) {
        for (Long id : ids) {
            add(id.toString());
        }
    }
    public void add(String param) {
        bloomFilter.add(param);
    }

    public boolean contains(String param) {
        return bloomFilter.contains(param);
    }

    /**
     * 验证ids，当存在不存在的id时，通知记录用户不存在
     * @param ids
     */
    public void verifyIds(Long ...ids) {
        for (Long id : ids) {
            if (!contains(id.toString())) {
                throw new RecodeNotExistedException(
                        ResponseStatusEnum.RECORDENOTEXISTED.getErrorCode(),
                        ResponseStatusEnum.RECORDENOTEXISTED.getErrorMessage()
                );
            }
        }
    }

}
