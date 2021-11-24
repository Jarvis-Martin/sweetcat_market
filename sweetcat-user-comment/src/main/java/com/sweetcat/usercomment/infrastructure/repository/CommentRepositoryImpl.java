package com.sweetcat.usercomment.infrastructure.repository;

import com.sweetcat.usercomment.domain.comment.entity.Comment;
import com.sweetcat.usercomment.domain.comment.entity.CommentComment;
import com.sweetcat.usercomment.domain.comment.entity.CommodityComment;
import com.sweetcat.usercomment.domain.comment.repository.CommentRepository;
import com.sweetcat.usercomment.infrastructure.dao.CommentCommentMapper;
import com.sweetcat.usercomment.infrastructure.dao.CommentMapper;
import com.sweetcat.usercomment.infrastructure.dao.CommodityCommentMapper;
import com.sweetcat.usercomment.infrastructure.factory.CommentFactory;
import com.sweetcat.usercomment.infrastructure.po.CommentCommentPO;
import com.sweetcat.usercomment.infrastructure.po.CommentPO;
import com.sweetcat.usercomment.infrastructure.po.CommodityCommentPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-19:52
 * @version: 1.0
 */
@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private CommentMapper commentMapper;
    private CommodityCommentMapper commodityCommentMapper;
    private CommentCommentMapper commentCommentMapper;
    private CommentFactory commentFactory;

    @Autowired
    public void setCommentCommodityMapper(CommodityCommentMapper commodityCommentMapper) {
        this.commodityCommentMapper = commodityCommentMapper;
    }

    @Autowired
    public void setCommodityCommentMapper(CommodityCommentMapper commodityCommentMapper) {
        this.commodityCommentMapper = commodityCommentMapper;
    }

    @Autowired
    public void setCommentCommentMapper(CommentCommentMapper commentCommentMapper) {
        this.commentCommentMapper = commentCommentMapper;
    }

    @Autowired
    public void setCommentFactory(CommentFactory commentFactory) {
        this.commentFactory = commentFactory;
    }

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    /**
     * 添加一条记录
     *
     * @param comment 评论
     * @param <C>     评论
     */
    @Override
    public <C extends Comment> void addOne(C comment) {
        // 添加 comment
        commentMapper.addOne(comment);
        // 添加商品评论
        if (Comment.COMMENTTYPE_COMMODITY.equals(comment.getCommentType())) {
            CommodityComment commodityComment = (CommodityComment) comment;
            commodityCommentMapper.addOne(commodityComment);
        }
        // 添加评论评论
        if (Comment.COMMENTTYPE_COMMENT.equals(comment.getCommentType())) {
            CommentComment commentComment = (CommentComment) comment;
            commentCommentMapper.addOne(commentComment);
        }
    }

    /**
     * 移除一个
     *
     * @param comment 评论
     * @param <C>     评论
     */
    @Override
    public <C extends Comment> void removeOne(C comment) {
        // 移除 comment
        commentMapper.deleteOne(comment);
        // 移除商品评论
        if (Comment.COMMENTTYPE_COMMODITY.equals(comment.getCommentType())) {
            CommodityComment commodityComment = (CommodityComment) comment;
            commodityCommentMapper.deleteOne(commodityComment);
        }
        // 移除评论评论
        if (Comment.COMMENTTYPE_COMMENT.equals(comment.getCommentType())) {
            CommentComment commentComment = (CommentComment) comment;
            commentCommentMapper.deleteOne(commentComment);
        }
    }

    /**
     * 根据 commentId 查找 comment
     *
     * @param commentId
     * @param <C>
     * @return
     */
    @Override
    public <C extends Comment> C findOneByCommentId(Long commentId) {
        CommentPO commentPO = commentMapper.findOneByCommentId(commentId);
        if (commentPO == null) {
            return null;
        }
        // 移除商品评论
        if (Comment.COMMENTTYPE_COMMODITY.equals(commentPO.getCommentType())) {
            CommodityCommentPO commodityCommentPO = commodityCommentMapper.findOneByCommentId(commentId);
            if (commodityCommentPO == null) {
                return null;
            }
            return (C) commentFactory.create(commentPO, commodityCommentPO);
        }
        // 移除评论评论
        if (Comment.COMMENTTYPE_COMMENT.equals(commentPO.getCommentType())) {
            CommentCommentPO commentCommentPO = commentCommentMapper.findOneByCommentId(commentId);
            if (commentCommentPO == null) {
                return null;
            }
            return (C) commentFactory.create(commentPO, commentCommentPO);
        }
        return null;
    }

    /**
     * 更加 publisherId 查找 commentPage
     *
     * @param publisherId
     * @return
     */
    @Override
    public <T extends Comment> List<T> findPageByPublisherId(Long publisherId, Integer page, Integer limit) {
        List<CommentPO> commentPOPage = commentMapper.findPageByPublisherId(publisherId, page, limit);
        if (commentPOPage == null || commentPOPage.size() <= 0) {
            return null;
        }
        ArrayList<T> commentPage = commentPOPage.stream().collect(
                ArrayList<T>::new,
                (con, commentPO) -> {
                    // 查找商品评论分页
                    if (Comment.COMMENTTYPE_COMMODITY.equals(commentPO.getCommentType())) {
                        CommodityCommentPO commodityCommentPO = commodityCommentMapper.findOneByCommentId(commentPO.getCommentId());
                        con.add((T) commentFactory.create(commentPO, commodityCommentPO));
                    }
                    // 查找评论评论分页
                    if (Comment.COMMENTTYPE_COMMENT.equals(commentPO.getCommentType())) {
                        CommentCommentPO commentCommentPO = commentCommentMapper.findOneByCommentId(commentPO.getCommentId());
                        con.add((T) commentFactory.create(commentPO, commentCommentPO));
                    }
                },
                ArrayList<T>::addAll
        );
        return commentPage;
    }

}
