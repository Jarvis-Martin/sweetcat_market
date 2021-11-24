package com.sweetcat.usercomment.domain.comment.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-19:05
 * @version: 1.0
 */
@Getter
public class CommentComment extends Comment {
    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 被评论的那条评论的id
     */
    private Long parentCommentId;

    public CommentComment(Long commentId) {
        super(commentId);
        this.commentId = commentId;
    }

    @Override
    public void setCommentId(Long commentId) {
        if (commentId == null || commentId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commentId = commentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        if (parentCommentId == null || parentCommentId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.parentCommentId = parentCommentId;
    }
}
