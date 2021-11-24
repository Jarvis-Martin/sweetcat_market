package com.sweetcat.usercomment.infrastructure.factory;

import com.sweetcat.usercomment.domain.comment.entity.CommentComment;
import com.sweetcat.usercomment.domain.comment.entity.CommodityComment;
import com.sweetcat.usercomment.domain.comment.entity.Publisher;
import com.sweetcat.usercomment.infrastructure.po.CommentCommentPO;
import com.sweetcat.usercomment.infrastructure.po.CommentPO;
import com.sweetcat.usercomment.infrastructure.po.CommodityCommentPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-21:09
 * @version: 1.0
 */
@Component
public class CommentFactory {

    public CommodityComment create(CommentPO commentPO, CommodityCommentPO commodityCommentPO) {
        CommodityComment commodityComment = new CommodityComment(commodityCommentPO.getCommentId());
        Publisher publisher = new Publisher(commentPO.getPublisherId());
        commodityComment.setCommodityId(commodityCommentPO.getCommodityId());
        commodityComment.setStars(commodityCommentPO.getStars());
        commodityComment.setPublisher(publisher);
        commodityComment.setContent(commentPO.getContent());
        commodityComment.setContentPics(commentPO.getContentPics());
        commodityComment.setCreateTime(commentPO.getCreateTime());
        commodityComment.setCommentType(commentPO.getCommentType());
        return commodityComment;
    }

    public CommentComment create(CommentPO commentPO, CommentCommentPO commodityCommentPO) {
        CommentComment commentComment = new CommentComment(commodityCommentPO.getCommentId());
        Publisher publisher = new Publisher(commentPO.getPublisherId());
        commentComment.setParentCommentId(commodityCommentPO.getParentCommentId());
        commentComment.setCommentId(commodityCommentPO.getCommentId());
        commentComment.setPublisher(publisher);
        commentComment.setContent(commentPO.getContent());
        commentComment.setContentPics(commentPO.getContentPics());
        commentComment.setCreateTime(commentPO.getCreateTime());
        commentComment.setCommentType(commentPO.getCommentType());
        return commentComment;
    }

}
