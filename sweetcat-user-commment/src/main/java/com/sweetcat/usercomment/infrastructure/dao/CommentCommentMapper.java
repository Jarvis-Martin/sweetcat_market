package com.sweetcat.usercomment.infrastructure.dao;

import com.sweetcat.usercomment.domain.comment.entity.CommentComment;
import com.sweetcat.usercomment.infrastructure.po.CommentCommentPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentCommentMapper {
    /**
     * 添加一条记录
     * @param comment 评论
     */
    void addOne(CommentComment comment);

    /**
     * 移除一个
     * @param comment 评论
     */
    void deleteOne(CommentComment comment);

    /**
     * 根据 commentId 查找 comment
     * @param commentId
     * @return
     */
    CommentCommentPO findOneByCommentId(Long commentId);
}