package com.sweetcat.usercomment.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.secondkill.SKCommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.domainevent.usercomment.UserDeletedCommodityCommentEvent;
import com.sweetcat.commons.domainevent.usercomment.UserPublishedCommentCommentEvent;
import com.sweetcat.commons.domainevent.usercomment.UserPublishedCommodityCommentEvent;
import com.sweetcat.commons.domainevent.usercomment.UserPublishedSKCommodityCommentEvent;
import com.sweetcat.commons.exception.CommentNotExistedException;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.usercomment.application.command.AddCommentCommentCommand;
import com.sweetcat.usercomment.application.command.AddCommodityCommentCommand;
import com.sweetcat.usercomment.application.event.publish.DomainEventPublisher;
import com.sweetcat.usercomment.application.rpc.CommodityInfoRpc;
import com.sweetcat.usercomment.application.rpc.SKCommodityInfoRpc;
import com.sweetcat.usercomment.application.rpc.UserInfoRpc;
import com.sweetcat.usercomment.domain.comment.entity.Comment;
import com.sweetcat.usercomment.domain.comment.entity.CommentComment;
import com.sweetcat.usercomment.domain.comment.entity.CommodityComment;
import com.sweetcat.usercomment.domain.comment.entity.Publisher;
import com.sweetcat.usercomment.domain.comment.repository.CommentRepository;
import com.sweetcat.usercomment.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.usercomment.infrastructure.service.snowflake_service.SnowFlakeService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private SKCommodityInfoRpc skCommodityInfoRpc;
    private SnowFlakeService snowFlakeService;

    @Autowired
    public void setSkCommodityInfoRpc(SKCommodityInfoRpc skCommodityInfoRpc) {
        this.skCommodityInfoRpc = skCommodityInfoRpc;
    }

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
     * ??????????????????
     * @param command
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOneCommodityComment(AddCommodityCommentCommand command) {
        boolean isSecondKillCommodity = false;
        Long publisherId = command.getPublisherId();
        Long commodityId = command.getCommodityId();
        // ?????? id
        verifyIdFormatService.verifyIds(publisherId, commodityId);
        // ?????? user
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(publisherId);
        // ?????? ??????
        checkUser(userInfo);
        // ?????? commodity ??????
        CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(commodityId);
        SKCommodityInfoRpcDTO skCommodityInfoRpcDTO = skCommodityInfoRpc.findByCommodityId(commodityId);
        if (skCommodityInfoRpcDTO != null) {
            isSecondKillCommodity = true;
        }
        // ????????????????????????????????????????????????
        if (commodityInfo == null && skCommodityInfoRpcDTO == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorMessage()
            );
        }
        // ?????? commentId
        long commentId = snowFlakeService.snowflakeId();
        // ?????? CommodityComment.publisher
        Publisher publisher = new Publisher(publisherId);
        // ?????? CommodityComment.commodityComment
        CommodityComment commodityComment = new CommodityComment(commentId);
        // ?????? CommodityComment
        inflateCommodityComment(command, publisher, commodityComment);
        // ??????
        commentRepository.addOne(commodityComment);
        // -- ??????????????????
        //    ????????????????????????
        long domainEventId = snowFlakeService.snowflakeId();
        if (isSecondKillCommodity) {
            //    ????????????????????????
            UserPublishedSKCommodityCommentEvent userPublishedSKCommodityCommentEvent = new UserPublishedSKCommodityCommentEvent(domainEventId);
            //    ??????????????????
            inflateUserPublishedSKCommodityCommentEvent(command, commentId, publisherId, commodityId, userPublishedSKCommodityCommentEvent);
            //    log
            System.out.println("sweetcat-user-information: ?????????????????? UserPublishedSKCommodityCommentEvent ????????????" + Instant.now().toEpochMilli());
            //    ??????????????????
            domainEventPublisher.syncSend("sweetcat_user_comment:add_comment_skCommodity", userPublishedSKCommodityCommentEvent);
        } else {
            //    ????????????????????????
            UserPublishedCommodityCommentEvent userPublishedCommodityCommentEvent = new UserPublishedCommodityCommentEvent(domainEventId);
            //    ??????????????????
            inflateUserPublishedCommodityCommentEvent(command, commentId, publisherId, commodityId, userPublishedCommodityCommentEvent);
            //    log
            System.out.println("sweetcat-user-information: ?????????????????? UserPublishedCommodityCommentEvent ????????????" + Instant.now().toEpochMilli());
            //    ??????????????????
            domainEventPublisher.syncSend("sweetcat_user_comment:add_comment_commodity", userPublishedCommodityCommentEvent);
        }
    }

    /**
     * ??????????????????
     * @param command
     * @param commentId
     * @param publisherId
     * @param commodityId
     * @param userPublishedCommodityCommentEvent
     */
    private void inflateUserPublishedSKCommodityCommentEvent(AddCommodityCommentCommand command, Long commentId, Long publisherId, Long commodityId, UserPublishedSKCommodityCommentEvent userPublishedCommodityCommentEvent) {
        userPublishedCommodityCommentEvent.setOccurOn(Instant.now().toEpochMilli());
        userPublishedCommodityCommentEvent.setCommentId(commentId);
        userPublishedCommodityCommentEvent.setPublisherId(publisherId);
        userPublishedCommodityCommentEvent.setCommodityId(commodityId);
        userPublishedCommodityCommentEvent.setContent(command.getContent());
        userPublishedCommodityCommentEvent.setContentPics(command.getContentPics());
        userPublishedCommodityCommentEvent.setStars(command.getStars());
        userPublishedCommodityCommentEvent.setCreateTime(command.getCreateTime());
    }

    /**
     * ??????????????????
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

    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOneCommentComment(AddCommentCommentCommand command) {
        Long publisherId = command.getPublisherId();
        Long parentCommentId = command.getParentCommentId();
        // ?????? id
        verifyIdFormatService.verifyIds(publisherId, parentCommentId);
        // ?????? user
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(publisherId);
        // ?????? ??????
        checkUser(userInfo);
        // ?????? comment
        Comment parentComment = commentRepository.findOneByCommentId(parentCommentId);
        // ?????? ??????
        checkComment(parentComment);
        // ?????? commentId
        long commentId = snowFlakeService.snowflakeId();
        CommentComment commentComment = new CommentComment(commentId);
        Publisher publisher = new Publisher(publisherId);
        // ?????? commentComment
        inflateCommentComment(command, parentCommentId, commentComment, publisher);
        // ??????
        commentRepository.addOne(commentComment);
        // ??????????????????
        long domainEventId = snowFlakeService.snowflakeId();
        //    ?????? UserPublishedCommentCommentEvent
        UserPublishedCommentCommentEvent userPublishedCommentCommentEvent = new UserPublishedCommentCommentEvent(domainEventId);
        // ?????????????????????????????????????????? ??????id??????????????????id
        Long commodityId = commentComment.getCommodityId();
        CommodityInfoRpcDTO commodityInfoRpcDTO = commodityInfoRpc.findByCommodityId(commodityId);
        //    ?????? UserPublishedCommentCommentEvent
        inflateUserPublisherCommentCommentEvent(command, publisherId, parentComment, commentId, commentComment, userPublishedCommentCommentEvent, commodityId, commodityInfoRpcDTO);
        //    log
        System.out.println("sweetcat-user-information: ?????????????????? UserPublishedCommentCommentEvent ????????????" + Instant.now().toEpochMilli());
        //    ??????????????????
        domainEventPublisher.syncSend("sweetcat_user_comment:add_comment_commoent", userPublishedCommentCommentEvent);
    }

    private void inflateUserPublisherCommentCommentEvent(AddCommentCommentCommand command, Long publisherId, Comment parentComment, long commentId, CommentComment commentComment, UserPublishedCommentCommentEvent userPublishedCommentCommentEvent, Long commodityId, CommodityInfoRpcDTO commodityInfoRpcDTO) {
        userPublishedCommentCommentEvent.setOccurOne(Instant.now().toEpochMilli());
        userPublishedCommentCommentEvent.setCommentId(commentId);
        userPublishedCommentCommentEvent.setPublisherId(publisherId);
        userPublishedCommentCommentEvent.setContent(command.getContent());
        userPublishedCommentCommentEvent.setContentPics(command.getContentPics());
        userPublishedCommentCommentEvent.setCreateTime(commentComment.getCreateTime());
        userPublishedCommentCommentEvent.setParentCommentId(command.getParentCommentId());
        userPublishedCommentCommentEvent.setCommodityId(commodityId);
        userPublishedCommentCommentEvent.setStoreId(Long.valueOf(commodityInfoRpcDTO.getStoreId()));
        userPublishedCommentCommentEvent.setReceiverId(parentComment.getPublisher().getPublisherId());
    }

    /**
     * ?????? CommentComment ??????
     * @param command
     * @param parentCommentId
     * @param commentComment
     * @param publisher
     */
    private void inflateCommentComment(AddCommentCommentCommand command, Long parentCommentId, CommentComment commentComment, Publisher publisher) {
        commentComment.setParentCommentId(parentCommentId);
        commentComment.setCommodityId(command.getCommodityId());
        commentComment.setPublisher(publisher);
        commentComment.setContent(command.getContent());
        commentComment.setContentPics(command.getContentPics());
        commentComment.setCreateTime(command.getCreateTime());
        commentComment.setCommentType(Comment.COMMENTTYPE_COMMENT);
    }

    /**
     * ?????? CommodityComment ??????
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
     * ?????? comment ?????????????????????????????????????????????
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
     * ?????? userInfo ?????????????????????????????????????????????
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
     * ?????? userInfo ?????????????????????????????????????????????
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
     * ????????????
     * @param commentId
     */
    @Transactional
    public void removeOne(Long commentId) {
        // ?????? id
        verifyIdFormatService.verifyIds(commentId);
        // ?????? comment ?????? commentId
        Comment comment = commentRepository.findOneByCommentId(commentId);
        // ??????
        commentRepository.removeOne(comment);
        // ?????????????????? UserDeletedCommodityCommentEvent
        //      -- ??????????????????id
        long domainEventId = snowFlakeService.snowflakeId();
        //      -- ??????????????????
        UserDeletedCommodityCommentEvent deletedCommodityCommentEvent = new UserDeletedCommodityCommentEvent(domainEventId);
        //      -- ??????????????????
        inflateUserDeletedCommodityCommentEvent(commentId, comment, deletedCommodityCommentEvent);
        //      -- log
        System.out.println("sweetcat-app-credit: ?????????????????? UserDeletedCommodityCommentEvent ????????????" + Instant.now().toEpochMilli());
        //      -- ??????????????????
        domainEventPublisher.syncSend("sweetcat_user_comment:delete_comment_commodity", deletedCommodityCommentEvent);
    }

    private void inflateUserDeletedCommodityCommentEvent(Long commentId, Comment comment, UserDeletedCommodityCommentEvent deletedCommodityCommentEvent) {
        deletedCommodityCommentEvent.setOccurOn(Instant.now().toEpochMilli());
        deletedCommodityCommentEvent.setUserId(comment.getPublisher().getPublisherId());
        deletedCommodityCommentEvent.setCommentId(commentId);
    }

    /**
     * ?????? commentId ?????? comment
     * @param commentId
     * @param <C>
     * @return
     */
    @Transactional
    public <C extends Comment> C findOneByCommentId(Long commentId) {
        // ?????? id
        verifyIdFormatService.verifyIds(commentId);
        // ?????? comment by commentId
        return commentRepository.findOneByCommentId(commentId);
    }

    /**
     * ?????? publisherId ?????? commentPage
     * @param publisherId
     * @return
     */
    @Transactional
    public <T extends Comment> List<T> findPageByPublisherId(Long publisherId, Integer page, Integer limit) {
        // ?????? publisherId
        verifyIdFormatService.verifyIds(publisherId);
        // ?????? page limit
        limit = limit == null || limit <= 0 ? 15 : limit;
        page = page == null || page <= 0 ? 0 : page * limit;
        // ???????????? by  publisherId
        return commentRepository.findPageByPublisherId(publisherId, page, limit);
    }
}
