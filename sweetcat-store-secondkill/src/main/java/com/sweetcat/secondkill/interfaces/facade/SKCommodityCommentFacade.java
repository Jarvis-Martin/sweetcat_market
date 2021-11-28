package com.sweetcat.secondkill.interfaces.facade;

import com.sweetcat.secondkill.application.command.AddSKCommodityCommentCommand;
import com.sweetcat.secondkill.application.service.SKCommodityCommentApplicationoService;
import com.sweetcat.secondkill.domain.commoditycomment.entity.SKCommodityComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/9-20:00
 * @version: 1.0
 */
@Component
public class SKCommodityCommentFacade {
    private SKCommodityCommentApplicationoService commentApplicationoService;

    @Autowired
    public void setCommentApplicationoService(SKCommodityCommentApplicationoService commentApplicationoService) {
        this.commentApplicationoService = commentApplicationoService;
    }

    /**
     * find commodity comment by commentId
     *
     * @param commentId commentId
     * @return
     */
    public SKCommodityComment findByCommentId(Long commentId) {
        return commentApplicationoService.findByCommentId(commentId);
    }

    /**
     * find commodity comment by commodityId
     *
     * @param commodityId commodityId
     * @param page
     * @param limit
     * @return
     */
    public List<SKCommodityComment> findPageByCommodityId(Long commodityId, Integer page, Integer limit) {
        return commentApplicationoService.findPageByCommodityId(commodityId, page, limit);
    }

    /**
     * add a commodity comment
     *
     * @param command command
     */
    public void addOne(AddSKCommodityCommentCommand command) {
        commentApplicationoService.addOne(command);
    }

    /**
     * remove a commodity comment
     *
     * @param commentId commentId
     */
    public void removeOne(Long commentId) {
        commentApplicationoService.removeOne(commentId);
    }
}
