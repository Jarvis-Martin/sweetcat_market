package com.sweetcat.appactivity.infrastructure.repository;

import com.sweetcat.appactivity.domain.activity.entity.Activity;
import com.sweetcat.appactivity.domain.activity.repository.ActivityRepository;
import com.sweetcat.appactivity.infrastructure.dao.ActivityMapper;
import com.sweetcat.appactivity.infrastructure.factory.ActivityFactory;
import com.sweetcat.appactivity.infrastructure.po.ActivityPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-20:07
 * @Version: 1.0
 */
@Repository
public class ActivityRepositoryImpl implements ActivityRepository {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityFactory activityFactory;

    @Override
    public List<Activity> find(Integer page, Integer limit, Long curTimeStamp) {
        // 获取分页 activity
        List<ActivityPO> activityPage = activityMapper.getActivityPage(page, limit, curTimeStamp);
        if (activityPage == null || activityPage.size() <= 0) {
            return null;
        }
        // ActivityPO 转 Activity 并返回
        return activityPage.stream().collect(
                ArrayList<Activity>::new,
                (l, activityPO) -> l.add(activityFactory.create(activityPO)),
                ArrayList::addAll);
    }

    @Override
    public Activity find(Long activityId) {
        ActivityPO activityPO = activityMapper.getOne(activityId);
        if (activityPO == null) {
            return null;
        }
        return activityFactory.create(activityPO);
    }

    @Override
    public void addOne(Activity activity) {
        activityMapper.insertOne(activity);
    }
}
