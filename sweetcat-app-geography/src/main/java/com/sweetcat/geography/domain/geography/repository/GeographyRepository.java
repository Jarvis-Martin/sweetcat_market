package com.sweetcat.geography.domain.geography.repository;

import com.sweetcat.geography.domain.geography.entity.Geography;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-14:00
 * @version: 1.0
 */
public interface GeographyRepository {
    /**
     * 添加一条geography
     * @param geography geography
     */
    void addOne(Geography geography);

    /**
     * 根据 addressCode 查找一个 geography
     * @param addressCode addressCode
     * @return
     */
    Geography find(String addressCode);

    /**
     * 移除一条geography
     * @param geography geography
     */
    void remove(Geography geography);
}
