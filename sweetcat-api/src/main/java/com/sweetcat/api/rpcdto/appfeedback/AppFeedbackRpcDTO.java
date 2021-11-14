package com.sweetcat.api.rpcdto.appfeedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-17:11
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppFeedbackRpcDTO {

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
}
