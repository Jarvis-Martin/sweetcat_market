package com.sweetcat.app_feedback.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * t_app_feedback
 * @author 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
     * 响应标题
     */
    private String responseTitle;

    /**
     * 处理时间
     */
    private Long processTime;

    private static final long serialVersionUID = 1L;
}