package com.sweetcat.storecommodity.interfaces.facade;

import com.sweetcat.storecommodity.application.command.AddCommodityCommentCommand;
import com.sweetcat.storecommodity.application.service.CommodityCommentApplicationoService;
import com.sweetcat.storecommodity.domain.commoditycomment.entity.CommodityComment;
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
public class CommodityCommentFacade {
    private CommodityCommentApplicationoService commentApplicationoService;

    @Autowired
    public void setCommentApplicationoService(CommodityCommentApplicationoService commentApplicationoService) {
        this.commentApplicationoService = commentApplicationoService;
    }

    /**
     * find commodity comment by commentId
     *
     * @param commentId commentId
     * @return
     */
    public CommodityComment findByCommentId(Long commentId) {
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
    public List<CommodityComment> findPageByCommodityId(Long commodityId, Integer page, Integer limit) {
        return commentApplicationoService.findPageByCommodityId(commodityId, page, limit);
    }

    /**
     * add a commodity comment
     *
     * @param command command
     */
    public void addOne(AddCommodityCommentCommand command) {
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
