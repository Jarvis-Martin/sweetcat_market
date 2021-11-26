package com.sweetcat.userinformation.application.command;

import lombok.Data;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/26-12:16
 * @version: 1.0
 */
@Data
public class AddSystemInformationCommand {
    /**
     * 通知id
     */
    private Long informationId;
    /**
     * 发布人
     */
    private Long publisherId;
    /**
     * 接收人
     */
    private Long receiverId;
    /**
     * 内容
     */
    private String content;
    /**
     * 内容配图
     */
    private List<String> contentPics;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 处理时间
     */
    private Long processTime;
    /**
     * 响应标题
     */
    private String responseTitle;
}
