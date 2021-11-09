package com.sweetcat.takeawaymaninfo.infrastructure.dao;

import com.sweetcat.takeawaymaninfo.domain.maninfo.entity.ManInfo;
import com.sweetcat.takeawaymaninfo.infrastructure.po.ManInfoPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManInfoMapper {
    /**
     * 添加
     *
     * @param mainInfo mainInfo
     */
    void addOne(ManInfo mainInfo);

    /**
     * find maninfo by manid provided
     *
     * @param manId
     */
    ManInfoPO getOne(Long manId);
}