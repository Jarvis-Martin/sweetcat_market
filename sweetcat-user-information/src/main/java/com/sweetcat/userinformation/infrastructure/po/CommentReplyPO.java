package com.sweetcat.userinformation.infrastructure.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * t_user_comment_reply
 * @author 
 */
@Data
public class CommentReplyPO implements Serializable {
    /**
     * 通知id
     */
    private Long informationId;

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 商品主图
     */
    private List<String> commodityPicSmall;

    /**
     * 商家id
     */
    private Long storeId;

    /**
     * 商家名
     */
    private String storeName;

    /**
     * 响应给用户的标题
     */
    private String targetUrl;

    private static final long serialVersionUID = 1L;
}