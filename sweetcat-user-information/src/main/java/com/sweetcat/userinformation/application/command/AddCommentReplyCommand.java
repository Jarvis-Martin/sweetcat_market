package com.sweetcat.userinformation.application.command;

import lombok.Data;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/26-19:37
 * @version: 1.0
 */
@Data
public class AddCommentReplyCommand {
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
     * 通知状态
     */
    private Integer status;
    /**
     * 商品评论的回复
     */
    public Long commodityId;
    /**
     * 商品所属的店家
     */
    private Long storeId;
    /**
     * 目标url
     */
    private String targetUrl;

}
