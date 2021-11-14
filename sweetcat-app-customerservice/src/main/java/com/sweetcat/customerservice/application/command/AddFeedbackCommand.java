package com.sweetcat.customerservice.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-18:00
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFeedbackCommand {

    /**
     * 待处理或已处理的用户反馈的id
     */
    private Long feedbackId;

    /**
     * 反馈创建时间
     */
    private Long createTime;

    /**
     * 接收人id
     */
    private Long receiverId;

}
