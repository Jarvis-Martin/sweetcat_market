package com.sweetcat.app_activity.interfaces.facade.impl;

import com.sweetcat.app_activity.application.service.ActivityApplicationService;
import com.sweetcat.app_activity.domain.activity.entity.Activity;
import com.sweetcat.app_activity.interfaces.facade.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-21:05
 * @Version: 1.0
 */
@Component
public class FacadeImpl implements Facade {

    @Autowired
    private ActivityApplicationService activityApplicationService;

    @Override
    public List<Activity> getActivityPage(Integer page, Integer limit, Long curTimeStamp) {
        return activityApplicationService.getActivitPage(page, limit, curTimeStamp);
    }

    @Override
    public Activity getActivityDetail(Long activityId) {
        return activityApplicationService.getActivityDetail(activityId);
    }
}
