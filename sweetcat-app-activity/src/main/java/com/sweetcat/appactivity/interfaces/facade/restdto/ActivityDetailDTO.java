package com.sweetcat.appactivity.interfaces.facade.restdto;

import lombok.Data;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-20:22
 * @Version: 1.0
 */
@Data
public class ActivityDetailDTO {

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 宣传小图
     */
    private String picSmall;

    /**
     * 活动内容图
     */
    private List picContent;

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
