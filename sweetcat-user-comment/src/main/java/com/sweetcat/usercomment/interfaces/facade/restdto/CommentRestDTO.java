package com.sweetcat.usercomment.interfaces.facade.restdto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-16:08
 * @version: 1.0
 */
@Getter
@Setter
public class CommentRestDTO {
    /**
     * 评价商品的评论
     */
    public static Integer COMMENTTYPE_COMMODITY = 0;
    /**
     * 评价评论的评论
     */
    public static Integer COMMENTTYPE_COMMENT = 1;

    /**
     * 评论的id
     */
    private Long commentId;
    /**
     * 发布人
     */
    private PublisherRestDTO publisher;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论配图
     */
    private List<String> contentPics;
    /**
     * 评论事件
     */
    private Long createTime;
    /**
     * 评论的类型：0商品评价，1用户评论
     */
    private Integer commentType;

    public CommentRestDTO(Long commentId) {
        this.commentId = commentId;
    }

}
