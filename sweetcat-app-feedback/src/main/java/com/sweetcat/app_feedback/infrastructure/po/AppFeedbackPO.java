package com.sweetcat.app_feedback.infrastructure.po;

import lombok.Getter;

import java.io.Serializable;

/**
 * t_app_feedback
 * @author 
 */
@Getter
public class AppFeedbackPO implements Serializable {
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
     * 处理人id
     */
    private Long processorId;

    /**
     * 响应内容
     */
    private String responseContent;

    /**
     * 处理时间
     */
    private Long processTime;

    public AppFeedbackPO() {}

    public AppFeedbackPO(Long feedbackId, Long userId, String content, String feedbackPics, Integer status, Long createTime, Long processorId, String responseContent, Long processTime) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.content = content;
        this.feedbackPics = feedbackPics;
        this.status = status;
        this.createTime = createTime;
        this.processorId = processorId;
        this.responseContent = responseContent;
        this.processTime = processTime;
    }

    public void setFeedbackId(Long feedbackId) {
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

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setProcessorId(Long processorId) {
        this.processorId = processorId;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public void setProcessTime(Long processTime) {
        this.processTime = processTime;
    }

    private static final long serialVersionUID = 1L;
}