package com.sweetcat.storecommodity.infrastructure.dao;

import com.sweetcat.storecommodity.infrastructure.po.CommodityCommentPO;

public interface CommodityCommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(CommodityCommentPO record);

    int insertSelective(CommodityCommentPO record);

    CommodityCommentPO selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(CommodityCommentPO record);

    int updateByPrimaryKey(CommodityCommentPO record);
}