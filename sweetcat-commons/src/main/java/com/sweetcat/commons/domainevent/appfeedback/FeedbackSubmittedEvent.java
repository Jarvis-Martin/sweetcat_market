package com.sweetcat.commons.domainevent.appfeedback;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-18:04
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class FeedbackSubmittedEvent {
    private Long feedbackId;
    private Long receiverId;

    /**
     * 事件发生时间
     */
    private Long occurOn;

    public FeedbackSubmittedEvent(Long feedbackId, Long receiverId) {
        this.feedbackId = feedbackId;
        this.receiverId = receiverId;
        this.occurOn = Instant.now().toEpochMilli();
    }
}
