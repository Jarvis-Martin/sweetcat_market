package com.sweetcat.appactivity.interfaces.web;

import com.sweetcat.appactivity.domain.activity.entity.Activity;
import com.sweetcat.appactivity.interfaces.facade.AppActivityFacade;
import com.sweetcat.appactivity.interfaces.facade.assembler.ActivityAssembler;
import com.sweetcat.appactivity.interfaces.facade.restdto.ActivityDetailDTO;
import com.sweetcat.appactivity.interfaces.facade.restdto.ActivityPageDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-21:02
 * @Version: 1.0
 */
@RestController
@RequestMapping("/app")
public class ActivityController {
    private ActivityAssembler activityAssembler;
    private AppActivityFacade facade;

    @Autowired
    public void setActivityAssembler(ActivityAssembler activityAssembler) {
        this.activityAssembler = activityAssembler;
    }

    @Autowired
    public void setFacade(AppActivityFacade facade) {
        this.facade = facade;
    }

    /**
     * 返回分页数据
     *
     * @return 分页数据
     */
    @GetMapping("/activities")
    public ResponseDTO getPage() {
        int page = 0;
        int limit = 6;
        long curTimeStamp = Instant.now().toEpochMilli();
        // 调用 防腐层，获得 领域对象 Carousel实例 集合
        List<Activity> activityPage = facade.getActivityPage(page, limit, curTimeStamp);
        if (activityPage == null || activityPage.size() <= 0) {
            return response("查询平台活动分页信息成功", "{}");
        }
        // 领域对象 Carousel 转 CarouseDTO
        List<ActivityPageDTO> activities = activityPage.stream().collect(
                ArrayList<ActivityPageDTO>::new,
                (l, carousel) -> l.add(activityAssembler.converterToActivityPageDTO(carousel)),
                ArrayList::addAll
        );
        // 组装 ResponseDTO
        Map<String, List<ActivityPageDTO>> dataSection = new HashMap<>(16);
        dataSection.put("activities", activities);
        return response("查询平台活动分页信息成功", dataSection);
    }

    @GetMapping("/activity/{activity_id}")
    public ResponseDTO getActivityDetail(@PathVariable("activity_id") Long activityId) {
        Activity activityDetail = facade.getActivityDetail(activityId);
        if (activityDetail == null) {
            return response("查询平台官方活动详细信息成功", "{}");
        }
        ActivityDetailDTO activityDetailDTO = activityAssembler.converterToActivityDetailDTO(activityDetail);

        // 组装 ResponseDTO
        Map<String, ActivityDetailDTO> dataSection = new HashMap<>(16);
        dataSection.put("activity", activityDetailDTO);
        return response("查询平台官方活动详细信息成功", dataSection);
    }

    /**
     * 通用的放回 ResponseDTO
     *
     * @param tip  用户提示信息
     * @param data 数据部分
     * @return ResponseDTO
     */
    private ResponseDTO response(String tip, Object data) {
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                tip,
                data
        );
    }
}
