package com.sweetcat.secondkill.infrastructure.dao;

import com.sweetcat.secondkill.domain.commoditycomment.entity.SKCommodityComment;
import com.sweetcat.secondkill.infrastructure.po.SKCommodityCommentPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SKCommodityCommentMapper {
    SKCommodityCommentPO findByCommentId(Long commentId);

    List<SKCommodityCommentPO> findPageByCommodityId(@Param("commodityId") Long commodityId, @Param("page") Integer page, @Param("limit") Integer limit);

    void insertOne(SKCommodityComment skCommodityComment);

    void deleteOne(SKCommodityComment skCommodityComment);
}