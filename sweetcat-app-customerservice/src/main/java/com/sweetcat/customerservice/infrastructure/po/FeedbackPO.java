package com.sweetcat.customerservice.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_app_customerservice_feedback_from_user
 * @author 
 */
@Data
public class FeedbackPO implements Serializable {
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

    /**
     * 接收人id
     */
    private Long receiverId;

    /**
     * 处理该反馈的客服id
     */
    private Long staffId;

    /**
     * 处理后的响应内容
     */
    private String responseContent;

    private static final long serialVersionUID = 1L;
}