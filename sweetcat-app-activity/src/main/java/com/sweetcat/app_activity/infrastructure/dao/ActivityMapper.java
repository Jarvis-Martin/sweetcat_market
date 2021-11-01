package com.sweetcat.app_activity.infrastructure.dao;

import com.sweetcat.app_activity.domain.activity.entity.Activity;
import com.sweetcat.app_activity.infrastructure.po.ActivityPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {
    List<ActivityPO> getActivityPage(
            @Param("page") Integer page,
            @Param("limit") Integer limit,
            @Param("curTimeStamp") Long curTimeStamp);

    ActivityPO getOne(Long activityId);
}