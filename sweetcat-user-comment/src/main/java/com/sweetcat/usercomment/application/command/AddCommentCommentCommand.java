package com.sweetcat.usercomment.application.command;

import lombok.Data;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-22:42
 * @version: 1.0
 */
@Data
public class AddCommentCommentCommand {

    /**
     * 鍙戝竷璇勮鐨勭敤鎴穒d
     */
    private Long publisherId;

    /**
     * 发布时间
     */
    private Long createTime;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论配图
     */
    private List<String> contentPics;

    /**
     * 被评论的那条评论的id
     */
    private Long parentCommentId;
}
