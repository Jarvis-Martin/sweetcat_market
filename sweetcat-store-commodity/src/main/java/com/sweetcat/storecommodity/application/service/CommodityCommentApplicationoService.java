package com.sweetcat.storecommodity.application.service;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CommentNotExistedException;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.storecommodity.application.command.AddCommodityCommentCommand;
import com.sweetcat.storecommodity.application.rpc.UserInfoRpc;
import com.sweetcat.storecommodity.domain.commoditycomment.entity.CommodityComment;
import com.sweetcat.storecommodity.domain.commoditycomment.repository.CommodityCommentRepository;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.Commodity;
import com.sweetcat.storecommodity.domain.commodityinfo.repository.CommodityInfoRepository;
import com.sweetcat.storecommodity.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
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
public class CommodityCommentApplicationoService {
    private CommodityCommentRepository commentRepository;
    private CommodityInfoRepository commodityInfoRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private UserInfoRpc userInfoRpc;

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setCommodityInfoRepository(CommodityInfoRepository commodityInfoRepository) {
        this.commodityInfoRepository = commodityInfoRepository;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setCommentRepository(CommodityCommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * find commodity comment by commentId
     *
     * @param commentId commentId
     * @return
     */
    @Transactional
    public CommodityComment findByCommentId(Long commentId) {
        // ?????? commentId ??????
        verifyIdFormatService.verifyId(commentId);
        // comment ?????????
        CommodityComment comment = commentRepository.findByCommentId(commentId);
        if (comment == null) {
            throw new CommentNotExistedException(
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorMessage()
            );
        }
        return comment;
    }

    /**
     * find commodity comment by commodityId
     *
     * @param commodityId commodityId
     * @return
     */
    @Transactional
    public List<CommodityComment> findPageByCommodityId(Long commodityId, Integer page, Integer limit) {
        // ?????? commodityId ??????
        verifyIdFormatService.verifyId(commodityId);
        // ???????????????
        if (commodityInfoRepository.findByCommodityId(commodityId) == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
        return commentRepository.findPageByCommodityId(commodityId, page, limit);
    }

    /**
     * add a commodity comment
     *
     * @param command command
     */
    @Transactional
    public void addOne(AddCommodityCommentCommand command) {
        Long commentId = command.getCommentId();
        long userId = command.getUserId();
        long commodityId = command.getCommodityId();
        // ??????id??????
        verifyIdFormatService.verifyId(commentId, userId, commodityId);
        Commodity commodity = commodityInfoRepository.findByCommodityId(commodityId);
        // ???????????????
        if (commodity == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
        // ???????????????
        if (userInfoRpc.getUserInfo(userId) == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // ????????????
        CommodityComment comment = new CommodityComment(commentId, userId, commodityId);
        comment.setContent(command.getContent());
        comment.setContentPics(command.getContentPics());
        comment.setStars(command.getStars());
        comment.setCreateTime(command.getCreateTime());
        // ??????db
        commentRepository.addOne(comment);
        // ???????????????
        commodity.increCommentNumber(1);
        // ??????
        commodityInfoRepository.save(commodity);
    }

    /**
     * remove a commodity comment
     *
     * @param commentId commodityComment
     */
    @Transactional
    public void removeOne(Long commentId) {
        // ?????? id
        verifyIdFormatService.verifyId(commentId);
        // ????????????????????????
        CommodityComment comment = commentRepository.findByCommentId(commentId);
        if (comment == null) {
            throw new CommentNotExistedException(
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorMessage()
            );
        }
        // ?????? comment
        commentRepository.removeOne(comment);
        // ??????????????????
        Commodity commodity = commodityInfoRepository.findByCommodityId(comment.getCommodityId());
        // ???????????????
        commodity.increCommentNumber(-1);
        // ????????????
        commodityInfoRepository.save(commodity);
    }

}
