package com.sweetcat.appactivity.infrastructure.factory;

import com.sweetcat.appactivity.domain.activity.entity.Activity;
import com.sweetcat.appactivity.infrastructure.dao.ActivityMapper;
import com.sweetcat.appactivity.infrastructure.po.ActivityPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-20:03
 * @Version: 1.0
 */
@Component
public class ActivityFactory {
    /// 该类用户 构建，装载 CarouselAggregate 对象

    private  final Logger logger = LoggerFactory.getLogger(ActivityFactory.class);

    @Autowired
    private ActivityMapper carouselMapper;

    public Activity create(ActivityPO carouselPO) {
        return converterToCarousel(carouselPO);
    }

    private Activity converterToCarousel(ActivityPO activityPO) {
        Activity activity = new Activity(activityPO.getActivityId());
        activity.setPicSmall(activityPO.getPicSmall());
        activity.setPicContent(activityPO.getPicContent());
        activity.setShowImmediately(activityPO.getShowImmediately());
        activity.setTargetUrl(activityPO.getTargetUrl());
        activity.setCreateTime(activityPO.getCreateTime());
        activity.setUpdateTime(activityPO.getUpdateTime());
        activity.setStartTime(activityPO.getStartTime());
        activity.setDeadline(activityPO.getDeadline());
        return activity;
    }
}
