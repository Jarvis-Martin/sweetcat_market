package com.sweetcat.app_feedback.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-19:32
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessFeedbackCommand {
    /**
     * 被处理的反馈的id
     */
    private Long feedbackId;

    /**
     * 处理人id
     */
    private Long processorId;

    /**
     * 处理事件
     */
    private Long processTime;

    /**
     * 处理后返回给用户的响应内容
     */
    private String responseContent;
}
