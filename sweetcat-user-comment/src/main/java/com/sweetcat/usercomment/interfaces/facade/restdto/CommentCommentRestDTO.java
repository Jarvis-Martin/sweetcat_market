package com.sweetcat.usercomment.interfaces.facade.restdto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-19:05
 * @version: 1.0
 */
@Getter
@Setter
public class CommentCommentRestDTO extends CommentRestDTO {
    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 被评论的那条评论的id
     */
    private Long parentCommentId;

    public CommentCommentRestDTO(Long commentId) {
        super(commentId);
        this.commentId = commentId;
    }
}
