package com.sweetcat.secondkill.domain.commoditycomment.repository;

import com.sweetcat.secondkill.domain.commoditycomment.entity.SKCommodityComment;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/9-17:24
 * @Version: 1.0
 */
public interface SKCommodityCommentRepository {
    /**
     * find commodity comment by commentId
     *
     * @param commentId commentId
     * @return
     */
    SKCommodityComment findByCommentId(Long commentId);

    /**
     * find commodity comment by commodityId
     *
     * @param commodityId commodityId
     * @param page
     * @param limit
     * @return
     */
    List<SKCommodityComment> findPageByCommodityId(Long commodityId, Integer page, Integer limit);

    /**
     * add a commodity comment
     *
     * @param SKCommodityComment commodityComment
     */
    void addOne(SKCommodityComment SKCommodityComment);

    /**
     * remove a commodity comment
     *
     * @param SKCommodityComment commodityComment
     */
    void removeOne(SKCommodityComment SKCommodityComment);

}
