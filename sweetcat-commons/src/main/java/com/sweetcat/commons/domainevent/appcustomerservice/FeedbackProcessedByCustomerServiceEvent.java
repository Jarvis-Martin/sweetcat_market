package com.sweetcat.commons.domainevent.appcustomerservice;

import lombok.*;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-19:13
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class FeedbackProcessedByCustomerServiceEvent {
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

    /**
     * 事件发生时间
     */
    private Long occurOn;

    public FeedbackProcessedByCustomerServiceEvent(Long feedbackId, Long processorId) {
        this.feedbackId = feedbackId;
        this.processorId = processorId;
        this.occurOn = Instant.now().toEpochMilli();
    }
}
