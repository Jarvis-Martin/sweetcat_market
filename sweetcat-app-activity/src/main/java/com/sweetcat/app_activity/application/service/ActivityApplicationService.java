package com.sweetcat.app_activity.application.service;

import com.sweetcat.app_activity.domain.activity.entity.Activity;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-21:07
 * @Version: 1.0
 */
public interface ActivityApplicationService {
    /**
     * 获得分页 activity 数据
     * @param page page
     * @param limit limit
     * @param curTimeStamp curTimeStamp
     * @return 分页 activity 数据
     */
    List<Activity> getActivitPage(Integer page, Integer limit, Long curTimeStamp);

    /**
     * 根据 activityId 获得activity 数据
     * @param activityId activityId
     * @return activity 数据
     */
    Activity getActivityDetail(Long activityId);
}
