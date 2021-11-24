package com.sweetcat.storecommodity.infrastructure.dao;

import com.sweetcat.storecommodity.domain.commoditycomment.entity.CommodityComment;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.Commodity;
import com.sweetcat.storecommodity.infrastructure.po.CommodityCommentPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommodityCommentMapper {
    /**
     * find commodity comment by commentId
     *
     * @param commentId commentId
     * @return
     */
    CommodityCommentPO findByCommentId(Long commentId);

    /**
     * find commodity comment by commodityId
     *
     * @param commodityId commodityId
     * @param page
     * @param limit
     * @return
     */
    List<CommodityCommentPO> findPageByCommodityId(@Param("commodityId") Long commodityId, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * add a commodity comment
     *
     * @param commodityComment commodityComment
     */
    void insertOne(CommodityComment commodityComment);

    /**
     * remove a commodity comment
     *
     * @param commodityComment commodityComment
     */
    void deleteOne(CommodityComment commodityComment);
}