package com.sweetcat.app_feedback.application.command;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/13-11:22
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class UploadFeedbackCommand {
    private Long userId;
    private String content;
    private String[] feedbackPics;
    private Long feedbackTime;
}
