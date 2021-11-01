package com.sweetcat.app_activity.interfaces.facade.dto;

import lombok.Data;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-20:14
 * @Version: 1.0
 */
@Data
public class ActivityPageDTO {
    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 宣传小图
     */
    private String picSmall;

    /**
     * 目标链接
     */
    private String targetUrl;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long deadline;
}
