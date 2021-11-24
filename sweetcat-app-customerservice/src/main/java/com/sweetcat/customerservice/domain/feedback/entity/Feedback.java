package com.sweetcat.customerservice.domain.feedback.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-12:59
 * @version: 1.0
 */
@Getter
public class Feedback {
    public static final Integer STATUS_UNPROCESSED = 0;
    public static final Integer STATUS_PROCESSED = 1;
    /**
     * 记录id
     */
    private Long recordId;

    /**
     * 待处理或已处理的用户反馈的id
     */
    private Long feedbackId;

    /**
     * 反馈创建时间
     */
    private Long createTime;

    /**
     * 记录更新时间
     */
    private Long updateTime;

    /**
     * 反馈处理时间
     */
    private Long processTime;

    private Integer status;

    private Informer informer;

    private Receiver receiver;
    /**
     * 处理后的响应内容
     */
    private String responseContent;

    /**
     * 响应给用户的标题
     */
    private String responseTitle;

    public Feedback(Long recordId, Long feedbackId) {
        this.setRecordId(recordId);
        this.setFeedbackId(feedbackId);
    }

    public Feedback(Long recordId, Long feedbackId, Long createTime, Long updateTime, Long processTime, Integer status, Informer informer, Receiver receiver, String responseContent, String responseTitle) {
        this.setRecordId(recordId);
        this.setFeedbackId(feedbackId);
        this.setCreateTime(createTime);
        this.setUpdateTime(updateTime);
        this.setProcessTime(processTime);
        this.setStatus(status);
        this.setInformer(informer);
        this.setRecordId(recordId);
        this.setResponseContent(responseContent);
        this.setResponseTitle(this.responseTitle);
    }

    public void setResponseTitle(String responseTitle) {
        if (responseTitle == null || responseTitle.length() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.responseTitle = responseTitle;
    }

    public void setStatus(Integer status) {
        if (status == null || status < Feedback.STATUS_UNPROCESSED || status > Feedback.STATUS_PROCESSED) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.status = status;
    }

    public void setRecordId(Long recordId) {
        if (recordId == null || recordId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.recordId = recordId;
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

    public void setCreateTime(Long createTime) {
        if (createTime == null || createTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.createTime = createTime;
    }

    public void setUpdateTime(Long updateTime) {
        if (updateTime == null || updateTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.updateTime = updateTime;
    }

    public void setProcessTime(Long processTime) {
        this.processTime = processTime;
    }

    public void setInformer(Informer informer) {
        this.informer = informer;
    }

    public void setReceiver(Receiver receiver) {
        if (receiver == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.receiver = receiver;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }
}
