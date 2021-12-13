package com.sweetcat.appactivity.domain.activity.repository;

import com.sweetcat.appactivity.domain.activity.entity.Activity;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-20:02
 * @Version: 1.0
 */
public interface ActivityRepository {
    /**
     * 获得分页 carousel
     * @param page page
     * @param limit limit
     * @param curTimeStamp curTimeStamp
     * @return 分页 carousel
     */
    List<Activity> find(Integer page, Integer limit, Long curTimeStamp);

    /**
     * 根据 activityId 查找 activity
     * @param activityId
     * @return
     */
    Activity find(Long activityId);
}
