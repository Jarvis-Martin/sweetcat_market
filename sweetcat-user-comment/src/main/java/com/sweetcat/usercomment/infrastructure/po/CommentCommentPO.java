package com.sweetcat.usercomment.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_user_comment_comment
 * @author 
 */
@Data
public class CommentCommentPO extends CommentPO implements Serializable {
    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 被评论的那条评论的id
     */
    private Long parentCommentId;

    private static final long serialVersionUID = 1L;
}