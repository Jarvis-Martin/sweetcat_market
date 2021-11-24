package com.sweetcat.storecommodity.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/9-19:39
 * @version: 1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddCommodityCommentCommand {
    /**
     * 评论id
     */
    private Long commentId;
    /**
     * 发布评论的用户id
     */
    private Long userId;

    /**
     * 被评论商品的id
     */
    private Long commodityId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论配图
     */
    private List<String> contentPics;

    /**
     * 星级
     */
    private Integer stars;

    /**
     * 发布时间
     */
    private Long createTime;
}
