package com.sweetcat.secondkill.application.service;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CommentNotExistedException;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.secondkill.application.command.AddSKCommodityCommentCommand;
import com.sweetcat.secondkill.application.rpc.UserInfoRpc;
import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;
import com.sweetcat.secondkill.domain.commodity.repository.SKCommodityRepository;
import com.sweetcat.secondkill.domain.commoditycomment.entity.SKCommodityComment;
import com.sweetcat.secondkill.domain.commoditycomment.repository.SKCommodityCommentRepository;
import com.sweetcat.secondkill.domain.commoditycomment.vo.Publisher;
import com.sweetcat.secondkill.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.secondkill.infrastructure.service.snowflake_service.SnowFlakeService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/9-18:49
 * @version: 1.0
 */
@Service
public class SKCommodityCommentApplicationoService {
    private SKCommodityCommentRepository commentRepository;
    private SKCommodityRepository commodityRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private UserInfoRpc userInfoRpc;

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setCommodityInfoRepository(SKCommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setCommentRepository(SKCommodityCommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * find commodity comment by commentId
     *
     * @param commentId commentId
     * @return
     */
    @Transactional
    public SKCommodityComment findByCommentId(Long commentId) {
        // ?????? commentId ??????
        verifyIdFormatService.verifyIds(commentId);
        // comment ?????????
        SKCommodityComment comment = commentRepository.findByCommentId(commentId);
        checkComment(comment);
        return comment;
    }

    private void checkComment(SKCommodityComment comment) {
        if (comment == null) {
            throw new CommentNotExistedException(
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * find commodity comment by commodityId
     *
     * @param commodityId commodityId
     * @return
     */
    @Transactional
    public List<SKCommodityComment> findPageByCommodityId(Long commodityId, Integer page, Integer limit) {
        // ?????? commodityId ??????
        verifyIdFormatService.verifyIds(commodityId);
        SKCommodity commodity = commodityRepository.findOneByCommodityId(commodityId);
        // ???????????????
        checkCommodity(commodity);
        return commentRepository.findPageByCommodityId(commodityId, page, limit);
    }

    /**
     * add a commodity comment
     *
     * @param command command
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOne(AddSKCommodityCommentCommand command) {
        Long commentId = command.getCommentId();
        long userId = command.getUserId();
        long commodityId = command.getCommodityId();
        // ??????id??????
        verifyIdFormatService.verifyIds(commentId, userId, commodityId);
        SKCommodity commodity = commodityRepository.findOneByCommodityId(commodityId);
        // ???????????????
        checkCommodity(commodity);
        // ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ???????????????
        checkUser(userInfo);
        // ????????????
        SKCommodityComment comment = new SKCommodityComment(commentId);
        Publisher publisher = new Publisher(userId);
        comment.setPublisher(publisher);
        comment.setCommodityId(commodityId);
        comment.setContent(command.getContent());
        comment.setContentPics(command.getContentPics());
        comment.setStars(command.getStars());
        comment.setCreateTime(command.getCreateTime());
        // ??????db
        commentRepository.addOne(comment);
        // ???????????????
        commodity.increaseCommentNumber();
        // ??????
        commodityRepository.save(commodity);
    }

    private void checkUser(UserInfoRpcDTO userInfo) {
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    private void checkCommodity(SKCommodity commodity) {
        if (commodity == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * remove a commodity comment
     *
     * @param commentId commodityComment
     */
    @Transactional
    public void removeOne(Long commentId) {
        // ?????? id
        verifyIdFormatService.verifyIds(commentId);
        // ????????????????????????
        SKCommodityComment comment = commentRepository.findByCommentId(commentId);
        if (comment != null) {
            // ?????? comment
            commentRepository.removeOne(comment);
        }
    }
}
