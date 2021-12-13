package com.sweetcat.appcarousel.application.command;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/13-15:23
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class AddCarouselCommand {

    /**
     * 图片路径
     */
    private String picPath;

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

    /**
     * 是否立即展示.0：不是；1：是
     */
    private Byte showImmediately;
}
