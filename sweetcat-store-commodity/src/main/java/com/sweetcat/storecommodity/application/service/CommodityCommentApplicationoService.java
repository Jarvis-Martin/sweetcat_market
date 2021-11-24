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
import com.sweetcat.storecommodity.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private SnowFlakeService snowFlakeService;
    private VerifyIdFormatService verifyIdFormatService;
    private UserInfoRpc userInfoRpc;

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

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
    public CommodityComment findByCommentId(Long commentId) {
        // 检查 commentId 格式
        verifyIdFormatService.verifyId(commentId);
        // comment 不存在
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
    public List<CommodityComment> findPageByCommodityId(Long commodityId, Integer page, Integer limit) {
        // 检查 commodityId 格式
        verifyIdFormatService.verifyId(commodityId);
        // 商品不存在
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
    public void addOne(AddCommodityCommentCommand command) {
        Long commentId = command.getCommentId();
        long userId = command.getUserId();
        long commodityId = command.getCommodityId();
        // 检查id格式
        verifyIdFormatService.verifyId(commentId, userId, commodityId);
        Commodity commodity = commodityInfoRepository.findByCommodityId(commodityId);
        // 商品不存在
        if (commodity == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
        // 用户不存在
        if (userInfoRpc.getUserInfo(userId) == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 新建评论
        CommodityComment comment = new CommodityComment(commentId, userId, commodityId);
        comment.setContent(command.getContent());
        comment.setContentPics(command.getContentPics());
        comment.setStars(command.getStars());
        comment.setCreateTime(command.getCreateTime());
        // 加入db
        commentRepository.addOne(comment);
        // 增加评论书
        commodity.increaseCommentNumber();
        // 入库
        commodityInfoRepository.save(commodity);
    }

    /**
     * remove a commodity comment
     *
     * @param commentId commodityComment
     */
    public void removeOne(Long commentId) {
        // 检查 id
        verifyIdFormatService.verifyId(commentId);
        // 检查评论是否存在
        CommodityComment comment = commentRepository.findByCommentId(commentId);
        // 删除 comment
        commentRepository.removeOne(comment);
    }
}
