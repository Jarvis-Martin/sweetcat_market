package com.sweetcat.appcarousel.domain.carousel.entity;

import lombok.Getter;

import java.io.Serializable;

/**
 * t_app_carousel
 * @author 
 */
@Getter
public class Carousel implements Serializable {
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

    public Carousel(Long carouselId) {
        this.carouselId = carouselId;
    }

    public void setCarouselId(Long carouselId) {
        this.carouselId = carouselId;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public void setShowImmediately(Byte showImmediately) {
        this.showImmediately = showImmediately;
    }

    private static final long serialVersionUID = 1L;
}