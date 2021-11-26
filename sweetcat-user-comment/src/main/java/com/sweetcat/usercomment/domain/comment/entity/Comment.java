package com.sweetcat.usercomment.domain.comment.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-16:08
 * @version: 1.0
 */
@Getter
public class Comment {
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
    private Publisher publisher;
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
    /**
     * 被评论商品的id
     */
    private Long commodityId;


    public Comment(Long commentId) {
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

    public void setContent(String content) {
        if (content == null || content.length() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.content = content;
    }

    public void setContentPics(List<String> contentPics) {
        if (contentPics == null || contentPics.size() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.contentPics = contentPics;
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

    public void setCommentType(Integer commentType) {
        if (commentType == null || commentType < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.commentType = commentType;
    }

}
