package com.sweetcat.usercomment.infrastructure.dao;

import com.sweetcat.usercomment.domain.comment.entity.CommodityComment;
import com.sweetcat.usercomment.infrastructure.po.CommodityCommentPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommodityCommentMapper {
    /**
     * 添加一条记录
     * @param comment 评论
     */
    void addOne(CommodityComment comment);

    /**
     * 移除一个
     * @param comment 评论
     */
    void deleteOne(CommodityComment comment);

    /**
     * 根据 commentId 查找 comment
     * @param commentId
     * @return
     */
    CommodityCommentPO findOneByCommentId(Long commentId);

}