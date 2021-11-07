package com.sweetcat.app_feedback.infrastructure.po;

import java.io.Serializable;

/**
 * t_app_feedback
 * @author 
 */
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
     * 处理时间
     */
    private Long processTime;

    public AppFeedbackPO() {}

    public AppFeedbackPO(Long feedbackId, Long userId, String content, String feedbackPics, Integer status, Long createTime, Long processTime) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.content = content;
        this.feedbackPics = feedbackPics;
        this.status = status;
        this.createTime = createTime;
        this.processTime = processTime;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeedbackPics() {
        return feedbackPics;
    }

    public void setFeedbackPics(String feedbackPics) {
        this.feedbackPics = feedbackPics;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Long processTime) {
        this.processTime = processTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;
}