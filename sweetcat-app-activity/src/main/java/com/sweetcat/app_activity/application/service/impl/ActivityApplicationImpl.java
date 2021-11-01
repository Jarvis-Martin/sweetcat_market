package com.sweetcat.app_activity.application.service.impl;

import com.sweetcat.app_activity.application.service.ActivityApplicationService;
import com.sweetcat.app_activity.domain.activity.entity.Activity;
import com.sweetcat.app_activity.domain.activity.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-21:07
 * @Version: 1.0
 */
@Service
public class ActivityApplicationImpl implements ActivityApplicationService {

    @Value("${max-activity}")
    private Integer maxActivity;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> getActivitPage(Integer page, Integer limit, Long curTimeStamp) {
        page = page < 0 ? 0 : page;
        limit = limit < 0 ? maxActivity : limit;
        curTimeStamp = curTimeStamp < 0 ? Instant.now().toEpochMilli() : curTimeStamp;

        return activityRepository.find(page, limit, curTimeStamp);
    }

    @Override
    public Activity getActivityDetail(Long activityId) {
        if (activityId <= 0) {
            throw new IllegalArgumentException("用户提交的 activityId 参数异常");
        }
        return activityRepository.find(activityId);
    }
}
