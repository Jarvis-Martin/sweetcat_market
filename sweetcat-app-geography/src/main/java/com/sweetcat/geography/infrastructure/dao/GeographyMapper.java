package com.sweetcat.geography.infrastructure.dao;

import com.sweetcat.geography.domain.geography.entity.Geography;
import com.sweetcat.geography.infrastructure.po.GeographyPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GeographyMapper {
    /**
     * 添加一条geography
     *
     * @param geography geography
     */
    void addOne(Geography geography);

    /**
     * 根据 addressCode 查找一个 geography
     *
     * @param addressCode addressCode
     * @return
     */
    GeographyPO find(String addressCode);

    /**
     * 移除一条geography
     *
     * @param geography geography
     */
    void delete(Geography geography);
}