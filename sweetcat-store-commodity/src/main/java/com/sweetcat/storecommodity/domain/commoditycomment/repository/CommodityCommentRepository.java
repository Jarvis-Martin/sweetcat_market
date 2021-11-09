package com.sweetcat.storecommodity.domain.commoditycomment.repository;

import com.sweetcat.storecommodity.domain.commoditycomment.entity.CommodityComment;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/9-17:24
 * @Version: 1.0
 */
public interface CommodityCommentRepository {
    /**
     * find commodity comment by commentId
     *
     * @param commentId commentId
     * @return
     */
    CommodityComment findByCommentId(Long commentId);

    /**
     * find commodity comment by commodityId
     *
     * @param commodityId commodityId
     * @param page
     * @param limit
     * @return
     */
    List<CommodityComment> findPageByCommodityId(Long commodityId, Integer page, Integer limit);

    /**
     * add a commodity comment
     *
     * @param commodityComment commodityComment
     */
    void addOne(CommodityComment commodityComment);

    /**
     * remove a commodity comment
     *
     * @param commodityComment commodityComment
     */
    void removeOne(CommodityComment commodityComment);

}
