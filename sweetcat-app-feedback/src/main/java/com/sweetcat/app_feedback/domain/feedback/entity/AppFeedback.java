package com.sweetcat.app_feedback.domain.feedback.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.io.Serializable;

/**
 * t_app_feedback
 *
 * @author
 */
@Getter
public class AppFeedback implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Integer STATUS_PROCESSING = 0;

    public static final Integer STATUS_PROCESSED = 1;

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
     * 响应标题
     */
    private String responseTitle;

    /**
     * 处理时间
     */
    private Long processTime;

    public AppFeedback(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public AppFeedback(Long feedbackId, Long userId, String content, String feedbackPics, Integer status, Long createTime, Long processorId, String responseContent, Long processTime, String responseTitle) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.content = content;
        this.feedbackPics = feedbackPics;
        this.status = status;
        this.createTime = createTime;
        this.processorId = processorId;
        this.responseContent = responseContent;
        this.processTime = processTime;
        this.setResponseTitle(responseTitle);
    }

    public void setResponseTitle(String responseTitle) {
        this.responseTitle = responseTitle;
    }

    public void setFeedbackId(Long feedbackId) {
        if (feedbackId == null || feedbackId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.feedbackId = feedbackId;
    }

    public void setUserId(Long userId) {
        if (userId == null || userId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFeedbackPics(String feedbackPics) {
        this.feedbackPics = feedbackPics;
    }

    public void setStatus(Integer status) {
        if (status == null || status < 0 || status > 1) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.status = status;
    }

    public void setCreateTime(Long createTime) {
        if (createTime == null || createTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
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
}