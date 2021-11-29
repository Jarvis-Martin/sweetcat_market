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
public class SKCommodityCommentApplicationoService {
    private SKCommodityCommentRepository commentRepository;
    private SKCommodityRepository commodityRepository;
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
    public SKCommodityComment findByCommentId(Long commentId) {
        // 检查 commentId 格式
        verifyIdFormatService.verifyIds(commentId);
        // comment 不存在
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
    public List<SKCommodityComment> findPageByCommodityId(Long commodityId, Integer page, Integer limit) {
        // 检查 commodityId 格式
        verifyIdFormatService.verifyIds(commodityId);
        SKCommodity commodity = commodityRepository.findOneByCommodityId(commodityId);
        // 商品不存在
        checkCommodity(commodity);
        return commentRepository.findPageByCommodityId(commodityId, page, limit);
    }

    /**
     * add a commodity comment
     *
     * @param command command
     */
    public void addOne(AddSKCommodityCommentCommand command) {
        Long commentId = command.getCommentId();
        long userId = command.getUserId();
        long commodityId = command.getCommodityId();
        // 检查id格式
        verifyIdFormatService.verifyIds(commentId, userId, commodityId);
        SKCommodity commodity = commodityRepository.findOneByCommodityId(commodityId);
        // 商品不存在
        checkCommodity(commodity);
        // 检查用户
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 用户不存在
        checkUser(userInfo);
        // 新建评论
        SKCommodityComment comment = new SKCommodityComment(commentId);
        Publisher publisher = new Publisher(userId);
        comment.setPublisher(publisher);
        comment.setCommodityId(commodityId);
        comment.setContent(command.getContent());
        comment.setContentPics(command.getContentPics());
        comment.setStars(command.getStars());
        comment.setCreateTime(command.getCreateTime());
        // 加入db
        commentRepository.addOne(comment);
        // 增加评论书
        commodity.increaseCommentNumber();
        // 入库
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
    public void removeOne(Long commentId) {
        // 检查 id
        verifyIdFormatService.verifyIds(commentId);
        // 检查评论是否存在
        SKCommodityComment comment = commentRepository.findByCommentId(commentId);
        if (comment != null) {
            // 删除 comment
            commentRepository.removeOne(comment);
        }
    }
}
