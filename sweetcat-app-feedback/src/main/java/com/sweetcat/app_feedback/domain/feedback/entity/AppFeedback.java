package com.sweetcat.app_feedback.domain.feedback.entity;

import java.io.Serializable;
import java.time.Instant;

import lombok.Data;

/**
 * t_app_feedback
 *
 * @author
 */
public class AppFeedback implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Integer STATUS_PROCESSING = 0;

    /**
     * 反馈记录id
     */
    private Long feedbackId;

    /**
     * 评论人id
     */
    private Long userId;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 反馈配图
     */
    private String feedbackPics;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 处理时间
     */
    private Long processTime;

    public AppFeedback(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public AppFeedback(Long feedbackId, Long userId, String content, String feedbackPics, Integer status, Long processTime) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.content = content;
        this.feedbackPics = feedbackPics;
        this.status = status;
        this.createTime = Instant.now().toEpochMilli();
        this.processTime = processTime;
    }

    private void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFeedbackPics(String feedbackPics) {
        this.feedbackPics = feedbackPics;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setProcessTime(Long processTime) {
        this.processTime = processTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public String getFeedbackPics() {
        return feedbackPics;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Long getProcessTime() {
        return processTime;
    }
}