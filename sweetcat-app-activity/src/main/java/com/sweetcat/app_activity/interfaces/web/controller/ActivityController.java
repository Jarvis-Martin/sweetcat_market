package com.sweetcat.app_activity.interfaces.web.controller;

import com.sweetcat.app_activity.domain.activity.entity.Activity;
import com.sweetcat.app_activity.interfaces.facade.Facade;
import com.sweetcat.app_activity.interfaces.facade.assembler.ActivityAssembler;
import com.sweetcat.app_activity.interfaces.facade.dto.ActivityDetailDTO;
import com.sweetcat.app_activity.interfaces.facade.dto.ActivityPageDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ActivityAssembler activityAssembler;

    @Autowired
    private Facade facade;

    /**
     * 返回分页数据
     *
     * @return 分页数据
     */
    @GetMapping("/activity_list")
    public ResponseDTO getPage() {
        int page = 0;
        int limit = 6;
        Long curTimeStamp = Instant.now().toEpochMilli();
        // 调用 防腐层，获得 领域对象 Carousel实例 集合
        List<Activity> activityPage = facade.getActivityPage(page, limit, curTimeStamp);
        // 领域对象 Carousel 转 CarouseDTO
        List<ActivityPageDTO> carouselPageDTOList = activityPage.stream().collect(
                ArrayList<ActivityPageDTO>::new,
                (l, carousel) -> l.add(activityAssembler.converterToActivityPageDTO(carousel)),
                ArrayList::addAll
        );
        // 组装 ResponseDTO
        Map<String, List<ActivityPageDTO>> activity = new HashMap<>(16);
        activity.put("activity_list", carouselPageDTOList);
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                "OK",
                activity);
    }

    @GetMapping("/activity_list/activity/{activity_id}")
    public ResponseDTO getActivityDetail(@PathVariable("activity_id") Long activityId) {
        Activity activityDetail = facade.getActivityDetail(activityId);
        ActivityDetailDTO activityDetailDTO = activityAssembler.converterToActivityDetailDTO(activityDetail);

        // 组装 ResponseDTO
        Map<String, ActivityDetailDTO> activity = new HashMap<>(16);
        activity.put("activity_detail", activityDetailDTO);
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                "OK",
                activity);
    }
}
