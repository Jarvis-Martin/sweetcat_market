package com.sweetcat.usercomment.domain.comment.repository;

import com.sweetcat.usercomment.domain.comment.entity.Comment;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-16:51
 * @version: 1.0
 */
public interface CommentRepository {
    /**
     * 添加一条记录
     * @param comment 评论
     * @param <C> 评论
     */
    <C extends Comment> void addOne(C comment);

    /**
     * 移除一个
     * @param comment 评论
     * @param <C> 评论
     */
    <C extends Comment> void removeOne(C comment);

    /**
     * 根据 commentId 查找 comment
     * @param commentId
     * @param <C>
     * @return
     */
    <C extends Comment> C findOneByCommentId(Long commentId);

    /**
     * 更加 publisherId 查找 commentPage
     * @param publisherId
     * @return
     */
    <T extends Comment> List<T> findPageByPublisherId(Long publisherId, Integer page, Integer limit);
}
