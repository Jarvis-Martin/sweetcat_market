package com.sweetcat.appactivity.domain.activity.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * t_app_activity
 * @author 
 */
@Data
public class Activity implements Serializable {
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
    private String picContent;

    /**
     * 是否立即展示.0：不是；1：是
     */
    private Integer showImmediately;

    /**
     * 目标链接
     */
    private String targetUrl;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long deadline;

    private static final long serialVersionUID = 1L;
}