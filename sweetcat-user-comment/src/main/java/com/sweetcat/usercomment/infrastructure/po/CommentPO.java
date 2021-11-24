package com.sweetcat.usercomment.infrastructure.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * t_user_comment
 * @author 
 */
@Data
public class CommentPO implements Serializable {
    /**
     * 评论id
     */
    private Long commentId;

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
     * 评论的类型；0商品评价；1商评价用户评论
     */
    private Integer commentType;

    /**
     * 评论配图
     */
    private List<String> contentPics;

    private static final long serialVersionUID = 1L;
}