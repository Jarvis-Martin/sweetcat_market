package com.sweetcat.secondkill.domain.commoditycomment.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.secondkill.domain.commoditycomment.vo.Publisher;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * t_store_commodity_comment
 *
 * @author
 */
@Getter
public class SKCommodityComment implements Serializable {
    /**
     * 评论编号
     */
    private Long commentId;

    /**
     * 发布评论的用户id
     */
    private Publisher publisher;

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

    public SKCommodityComment(Long commentId) {
        this.setCommentId(commentId);
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

    public void setPublisher(Publisher publisher) {
        if (publisher == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.publisher = publisher;
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