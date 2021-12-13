package com.sweetcat.appactivity.interfaces.facade.assembler;

import com.sweetcat.appactivity.domain.activity.entity.Activity;
import com.sweetcat.appactivity.interfaces.facade.restdto.ActivityDetailDTO;
import com.sweetcat.appactivity.interfaces.facade.restdto.ActivityPageDTO;
import com.sweetcat.commons.util.JSONUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/16-20:04
 * @Version: 1.0
 */
@Component
public class ActivityAssembler {

    public ActivityPageDTO converterToActivityPageDTO(Activity activityDO) {
        ActivityPageDTO activityPageDTO = new ActivityPageDTO();
        inflateActivityPageDTO(activityDO, activityPageDTO);
        return activityPageDTO;
    }

    public ActivityDetailDTO converterToActivityDetailDTO(Activity activityDO) {
        ActivityDetailDTO activityDetailDTO = new ActivityDetailDTO();
        inflateActivityDetailDTO(activityDO, activityDetailDTO);
        return activityDetailDTO;
    }

    private void inflateActivityPageDTO(Activity activityDO, ActivityPageDTO activityPageDTO) {
        activityPageDTO.setActivityId(activityDO.getActivityId());
        activityPageDTO.setPicSmall(activityDO.getPicSmall());
        activityPageDTO.setTargetUrl(activityDO.getTargetUrl());
        activityPageDTO.setStartTime(activityDO.getStartTime());
        activityPageDTO.setDeadline(activityDO.getDeadline());
    }

    private void inflateActivityDetailDTO(Activity activityDO, ActivityDetailDTO activityDetailDTO) {
        activityDetailDTO.setActivityId(activityDO.getActivityId());
        activityDetailDTO.setPicSmall(activityDO.getPicSmall());
        activityDetailDTO.setPicContent(JSONUtils.fromJson(activityDO.getPicContent(), List.class));
        activityDetailDTO.setTargetUrl(activityDO.getTargetUrl());
        activityDetailDTO.setStartTime(activityDO.getStartTime());
        activityDetailDTO.setDeadline(activityDO.getDeadline());
    }
}
