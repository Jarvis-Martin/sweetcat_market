package com.sweetcat.usercomment.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.domainevent.usercomment.UserDeletedCommodityCommentEvent;
import com.sweetcat.commons.domainevent.usercomment.UserPublishedCommodityCommentEvent;
import com.sweetcat.commons.exception.CommentNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.usercomment.application.command.AddCommentCommentCommand;
import com.sweetcat.usercomment.application.command.AddCommodityCommentCommand;
import com.sweetcat.usercomment.application.event.publish.DomainEventPublisher;
import com.sweetcat.usercomment.application.rpc.CommodityInfoRpc;
import com.sweetcat.usercomment.application.rpc.UserInfoRpc;
import com.sweetcat.usercomment.domain.comment.entity.Comment;
import com.sweetcat.usercomment.domain.comment.entity.CommentComment;
import com.sweetcat.usercomment.domain.comment.entity.CommodityComment;
import com.sweetcat.usercomment.domain.comment.entity.Publisher;
import com.sweetcat.usercomment.domain.comment.repository.CommentRepository;
import com.sweetcat.usercomment.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.usercomment.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-22:25
 * @version: 1.0
 */
@Service
public class CommentApplicationService {
    private CommentRepository commentRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private DomainEventPublisher domainEventPublisher;
    private UserInfoRpc userInfoRpc;
    private CommodityInfoRpc commodityInfoRpc;
    private SnowFlakeService snowFlakeService;

    @Autowired
    public void setDomainEventPublisher(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * 添加一条记录
     * @param command
     */
    public void addOneCommodityComment(AddCommodityCommentCommand command) {
        Long publisherId = command.getPublisherId();
        Long commodityId = command.getCommodityId();
        // 检查 id
        verifyIdFormatService.verifyIds(publisherId, commodityId);
        // 查找 user
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(publisherId);
        // 检查 用户
        checkUser(userInfo);
        // 查找 commodity 信息
        CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(commodityId);
        // 检查 商品
        checkCommodity(commodityInfo);
        // 生成 commentId
        long commentId = snowFlakeService.snowflakeId();
        // 构建 CommodityComment.publisher
        Publisher publisher = new Publisher(publisherId);
        // 构建 CommodityComment.commodityComment
        CommodityComment commodityComment = new CommodityComment(commentId);
        // 填充 CommodityComment
        inflateCommodityComment(command, publisher, commodityComment);
        // 入库
        commentRepository.addOne(commodityComment);
        // -- 创建领域事件
        //    领域事件唯一标识
        long domainEventId = snowFlakeService.snowflakeId();
        //    创建领域事件对象
        UserPublishedCommodityCommentEvent userPublishedCommodityCommentEvent = new UserPublishedCommodityCommentEvent(domainEventId);
        //    填充领域事件
        inflateUserPublishedCommodityCommentEvent(command, commentId, publisherId, commodityId, userPublishedCommodityCommentEvent);
        //    log
        System.out.println("sweetcat-app-credit: 触发领域事件 UserPublishedCommodityCommentEvent 时间为：" + Instant.now().toEpochMilli());
        //    发布领域事件
        domainEventPublisher.syncSend("sweetcat_user_comment:add_comment_commodity", userPublishedCommodityCommentEvent);
    }

    /**
     * 填充领域事件
     * @param command
     * @param commentId
     * @param publisherId
     * @param commodityId
     * @param userPublishedCommodityCommentEvent
     */
    private void inflateUserPublishedCommodityCommentEvent(AddCommodityCommentCommand command, Long commentId, Long publisherId, Long commodityId, UserPublishedCommodityCommentEvent userPublishedCommodityCommentEvent) {
        userPublishedCommodityCommentEvent.setOccurOn(Instant.now().toEpochMilli());
        userPublishedCommodityCommentEvent.setCommentId(commentId);
        userPublishedCommodityCommentEvent.setPublisherId(publisherId);
        userPublishedCommodityCommentEvent.setCommodityId(commodityId);
        userPublishedCommodityCommentEvent.setContent(command.getContent());
        userPublishedCommodityCommentEvent.setContentPics(command.getContentPics());
        userPublishedCommodityCommentEvent.setStars(command.getStars());
        userPublishedCommodityCommentEvent.setCreateTime(command.getCreateTime());
    }

    public void addOneCommentComment(AddCommentCommentCommand command) {
        Long publisherId = command.getPublisherId();
        Long parentCommentId = command.getParentCommentId();
        // 检查 id
        verifyIdFormatService.verifyIds(publisherId, parentCommentId);
        // 查找 user
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(publisherId);
        // 检查 用户
        checkUser(userInfo);
        // 查找 comment
        Comment comment = commentRepository.findOneByCommentId(parentCommentId);
        // 检查 评论
        checkComment(comment);
        // 生成 commentId
        long commentId = snowFlakeService.snowflakeId();
        CommentComment commentComment = new CommentComment(commentId);
        Publisher publisher = new Publisher(publisherId);
        // 填充 commentComment
        inflateCommentComment(command, parentCommentId, commentComment, publisher);
        // 入库
        commentRepository.addOne(commentComment);
    }

    /**
     * 填充 CommentComment 对象
     * @param command
     * @param parentCommentId
     * @param commentComment
     * @param publisher
     */
    private void inflateCommentComment(AddCommentCommentCommand command, Long parentCommentId, CommentComment commentComment, Publisher publisher) {
        commentComment.setParentCommentId(parentCommentId);
        commentComment.setPublisher(publisher);
        commentComment.setContent(command.getContent());
        commentComment.setContentPics(command.getContentPics());
        commentComment.setCreateTime(command.getCreateTime());
        commentComment.setCommentType(Comment.COMMENTTYPE_COMMENT);
    }

    /**
     * 填充 CommodityComment 对象
     * @param command
     * @param publisher
     * @param commodityComment
     */
    private void inflateCommodityComment(AddCommodityCommentCommand command, Publisher publisher, CommodityComment commodityComment) {
        commodityComment.setPublisher(publisher);
        commodityComment.setCreateTime(command.getCreateTime());
        commodityComment.setContent(command.getContent());
        commodityComment.setCommodityId(command.getCommodityId());
        commodityComment.setStars(command.getStars());
        commodityComment.setContentPics(command.getContentPics());
        commodityComment.setCommentType(Comment.COMMENTTYPE_COMMODITY);
    }

    /**
     * 检查 comment 是否存在，不存在则直接返回前端
     * @param comment
     */
    private void checkComment(Comment comment) {
        if (comment == null) {
            throw new CommentNotExistedException(
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * 检查 userInfo 是否存在，不存在则直接返回前端
     * @param userInfo
     */
    private void checkUser(UserInfoRpcDTO userInfo) {
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * 检查 userInfo 是否存在，不存在则直接返回前端
     * @param commodityInfoRpcDTO
     */
    private void checkCommodity(CommodityInfoRpcDTO commodityInfoRpcDTO) {
        if (commodityInfoRpcDTO == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
    }


    /**
     * 移除一个
     * @param commentId
     */
    public void removeOne(Long commentId) {
        // 检查 id
        verifyIdFormatService.verifyIds(commentId);
        // 查找 comment 通过 commentId
        Comment comment = commentRepository.findOneByCommentId(commentId);
        // 移除
        commentRepository.removeOne(comment);
        // 触发领域事件 UserDeletedCommodityCommentEvent
        //      -- 生成领域事件id
        long domainEventId = snowFlakeService.snowflakeId();
        //      -- 构建领域事件
        UserDeletedCommodityCommentEvent deletedCommodityCommentEvent = new UserDeletedCommodityCommentEvent(domainEventId);
        //      -- 填充领域事件
        inflateUserDeletedCommodityCommentEvent(commentId, comment, deletedCommodityCommentEvent);
        //      -- log
        System.out.println("sweetcat-app-credit: 触发领域事件 UserDeletedCommodityCommentEvent 时间为：" + Instant.now().toEpochMilli());
        //      -- 发布领域事件
        domainEventPublisher.syncSend("sweetcat_user_comment:delete_comment_commodity", deletedCommodityCommentEvent);
    }

    private void inflateUserDeletedCommodityCommentEvent(Long commentId, Comment comment, UserDeletedCommodityCommentEvent deletedCommodityCommentEvent) {
        deletedCommodityCommentEvent.setOccurOn(Instant.now().toEpochMilli());
        deletedCommodityCommentEvent.setUserId(comment.getPublisher().getPublisherId());
        deletedCommodityCommentEvent.setCommentId(commentId);
    }

    /**
     * 根据 commentId 查找 comment
     * @param commentId
     * @param <C>
     * @return
     */
    public <C extends Comment> C findOneByCommentId(Long commentId) {
        // 检查 id
        verifyIdFormatService.verifyIds(commentId);
        // 查找 comment by commentId
        return commentRepository.findOneByCommentId(commentId);
    }

    /**
     * 更加 publisherId 查找 commentPage
     * @param publisherId
     * @return
     */
    public <T extends Comment> List<T> findPageByPublisherId(Long publisherId, Integer page, Integer limit) {
        // 检查 publisherId
        verifyIdFormatService.verifyIds(publisherId);
        // 调整 page limit
        limit = limit == null || limit <= 0 ? 15 : limit;
        page = page == null || page <= 0 ? 0 : page * limit;
        // 查找分页 by  publisherId
        return commentRepository.findPageByPublisherId(publisherId, page, limit);
    }
}
