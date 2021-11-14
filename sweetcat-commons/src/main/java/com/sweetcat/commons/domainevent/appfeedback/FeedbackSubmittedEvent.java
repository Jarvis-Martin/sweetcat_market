package com.sweetcat.commons.domainevent.appfeedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-18:04
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackSubmittedEvent {
    private Long feedbackId;
    private Long receiverId;

    /**
     * 事件发生时间
     */
    private Long occurOn;
}
