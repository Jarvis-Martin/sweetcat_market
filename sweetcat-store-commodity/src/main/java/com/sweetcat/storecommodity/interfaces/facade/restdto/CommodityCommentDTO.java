package com.sweetcat.storecommodity.interfaces.facade.restdto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * t_store_commodity_comment
 * @author 
 */
@Data
public class CommodityCommentDTO implements Serializable {
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

    private static final long serialVersionUID = 1L;
}