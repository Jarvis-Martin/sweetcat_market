package com.sweetcat.appcarousel.infrastructure.po;

import java.io.Serializable;
import lombok.Data;

/**
 * t_app_carousel
 * @author 
 */
@Data
public class CarouselPO implements Serializable {
    /**
     * 轮播图id
     */
    private Long carouselId;

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

    /**
     * 是否立即展示.0：不是；1：是
     */
    private Byte showImmediately;

    private static final long serialVersionUID = 1L;
}