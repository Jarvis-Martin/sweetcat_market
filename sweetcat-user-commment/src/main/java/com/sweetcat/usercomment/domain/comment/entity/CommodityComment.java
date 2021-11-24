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
public class CommodityComment extends Comment {
    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 被评论商品的id
     */
    private Long commodityId;

    /**
     * 星级
     */
    private Long stars;

    public CommodityComment(Long commentId) {
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

    public void setCommodityId(Long commodityId) {
        if (commodityId == null || commodityId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commodityId = commodityId;
    }

    public void setStars(Long stars) {
        if (stars == null || stars < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.stars = stars;
    }
}
