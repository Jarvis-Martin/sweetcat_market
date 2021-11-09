package com.sweetcat.storecommodity.domain.commoditycomment.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * t_store_commodity_comment
 *
 * @author
 */
@Getter
public class CommodityComment implements Serializable {
    /**
     * 评论编号
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

    public CommodityComment(Long commentId, Long userId, Long commodityId) {
        this.setCommentId(commentId);
        this.setUserId(userId);
        this.setCommodityId(commodityId);
    }

    public CommodityComment(Long commentId, Long userId, Long commodityId, String content, List<String> contentPics, Integer stars, Long createTime) {
        this.setCommentId(commentId);
        this.setUserId(userId);
        this.setCommodityId(commodityId);
        this.setContent(content);
        this.setContentPics(contentPics);
        this.setStars(stars);
        this.setCreateTime(createTime);
    }

    public void setCommentId(Long commentId) {
        if (commentId == null || commentId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commentId = commentId;
    }

    public void setUserId(Long userId) {
        if (userId == null || userId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.userId = userId;
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setContentPics(List<String> contentPics) {
        this.contentPics = contentPics;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public void setCreateTime(Long createTime) {
        if (createTime == null || createTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.createTime = createTime;
    }

    private static final long serialVersionUID = 1L;
}