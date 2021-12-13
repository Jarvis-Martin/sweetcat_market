package com.sweetcat.appactivity.application.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/13-14:31
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class AddActivityCommand {

    /**
     * 宣传小图
     */
    private String picSmall;

    /**
     * 活动内容图
     */
    private List<String> picContent;

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
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long deadline;
}
