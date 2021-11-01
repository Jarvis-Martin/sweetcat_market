package com.sweetcat.app_activity.interfaces.facade;

import com.sweetcat.app_activity.domain.activity.entity.Activity;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-21:03
 * @Version: 1.0
 */
public interface Facade {
    List<Activity> getActivityPage(Integer page, Integer limit, Long curTimeStamp);
    Activity getActivityDetail(Long activityId);
}
