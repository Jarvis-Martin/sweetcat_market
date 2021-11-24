package com.sweetcat.usercomment.infrastructure.dao;

import com.sweetcat.usercomment.domain.comment.entity.Comment;
import com.sweetcat.usercomment.infrastructure.po.CommentPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 86152
 */
@Mapper
public interface CommentMapper {
        /**
         * 添加一条记录
         * @param comment 评论
         */
        void addOne(Comment comment);

        /**
         * 移除一个
         * @param comment 评论
         */
        void deleteOne(Comment comment);

        /**
         * 根据 commentId 查找 comment
         * @param commentId
         * @return
         */
        CommentPO findOneByCommentId(Long commentId);

        /**
         * 更加 publisherId 查找 commentPage
         * @param publisherId
         * @return
         */
        List<CommentPO> findPageByPublisherId(@Param("publisherId") Long publisherId, @Param("page") Integer page, @Param("limit") Integer limit);
}