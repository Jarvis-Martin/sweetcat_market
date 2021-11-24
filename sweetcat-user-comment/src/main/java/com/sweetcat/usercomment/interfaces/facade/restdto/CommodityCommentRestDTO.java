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
public class CommodityCommentRestDTO extends CommentRestDTO {
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
    private Integer stars;

    public CommodityCommentRestDTO(Long commentId) {
        super(commentId);
        this.commentId = commentId;
    }
}
