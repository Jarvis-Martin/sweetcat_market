package com.sweetcat.usercomment.infrastructure.po;

import lombok.Data;

import java.io.Serializable;

/**
 * t_user_comment_commodity
 * @author 
 */
@Data
public class CommodityCommentPO  extends CommentPO  implements Serializable {
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

    private static final long serialVersionUID = 1L;
}