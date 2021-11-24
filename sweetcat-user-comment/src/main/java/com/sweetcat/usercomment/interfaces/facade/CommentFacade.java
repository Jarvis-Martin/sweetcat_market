package com.sweetcat.usercomment.interfaces.facade;

import com.sweetcat.usercomment.application.command.AddCommentCommentCommand;
import com.sweetcat.usercomment.application.command.AddCommodityCommentCommand;
import com.sweetcat.usercomment.application.service.CommentApplicationService;
import com.sweetcat.usercomment.domain.comment.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-23:08
 * @version: 1.0
 */
@Component
public class CommentFacade {
    private CommentApplicationService commentApplicationService;

    @Autowired
    public void setCommentApplicationService(CommentApplicationService commentApplicationService) {
        this.commentApplicationService = commentApplicationService;
    }

    /**
     * 添加一条 CommodityComment
     * @param command
     */
    public void addOneCommodityComment(AddCommodityCommentCommand command) {
        commentApplicationService.addOneCommodityComment(command);
    }

    /**
     * 添加一条 commentComment
     * @param command
     */
    public void addOneCommentComment(AddCommentCommentCommand command) {
        commentApplicationService.addOneCommentComment(command);
    }
    /**
     * 移除一个
     * @param commentId 评论id
     */
    public void removeOne(Long commentId) {
        commentApplicationService.removeOne(commentId);
    }

    /**
     * 根据 commentId 查找 comment
     * @param commentId
     * @param <C>
     * @return
     */
    public <C extends Comment> C findOneByCommentId(Long commentId) {
        return commentApplicationService.findOneByCommentId(commentId);
    }

    /**
     * 更加 publisherId 查找 commentPage
     * @param publisherId
     * @return
     */
    public <T extends Comment> List<T> findPageByPublisherId(Long publisherId, Integer page, Integer limit) {
        return commentApplicationService.findPageByPublisherId(publisherId, page, limit);
    }
}
