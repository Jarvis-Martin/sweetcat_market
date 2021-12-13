package com.sweetcat.appactivity.application.service.impl;

import com.sweetcat.appactivity.application.command.AddActivityCommand;
import com.sweetcat.appactivity.application.service.ActivityApplicationService;
import com.sweetcat.appactivity.domain.activity.entity.Activity;
import com.sweetcat.appactivity.domain.activity.repository.ActivityRepository;
import com.sweetcat.appactivity.infrastructure.cache.BloomFilter;
import com.sweetcat.appactivity.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.appactivity.infrastructure.service.snowflake_service.SnowFlakeService;
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

    private ActivityRepository activityRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private BloomFilter bloomFilter;

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setBloomFilter(BloomFilter bloomFilter) {
        this.bloomFilter = bloomFilter;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setActivityRepository(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> getActivitPage(Integer page, Integer limit, Long curTimeStamp) {
        page = page < 0 ? 0 : page;
        limit = limit < 0 ? maxActivity : limit;
        curTimeStamp = curTimeStamp < 0 ? Instant.now().toEpochMilli() : curTimeStamp;

        return activityRepository.find(page, limit, curTimeStamp);
    }

    @Override
    public Activity getActivityDetail(Long activityId) {
        verifyIdFormatService.verifyIds(activityId);
        bloomFilter.verifyIds(activityId);
        return activityRepository.find(activityId);
    }

    @Override
    public void addOne(AddActivityCommand command) {
        long activityId = snowFlakeService.snowflakeId();
        Activity activity = new Activity(activityId);
        bloomFilter.add(activityId);
        inflateActivity(command, activity);
        activityRepository.addOne(activity);
    }

    private void inflateActivity(AddActivityCommand command, Activity activity) {
        activity.setPicSmall(command.getPicSmall());
        activity.setPicContent(command.getPicContent());
        activity.setShowImmediately(command.getShowImmediately());
        activity.setTargetUrl(command.getTargetUrl());
        activity.setCreateTime(command.getCreateTime());
        activity.setUpdateTime(command.getCreateTime());
        activity.setStartTime(command.getStartTime());
        activity.setDeadline(command.getDeadline());
    }
}
