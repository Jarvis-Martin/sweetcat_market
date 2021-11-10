package com.sweetcat.storecommodity.interfaces.facade.assembler;

import com.sweetcat.storecommodity.domain.commoditycomment.entity.CommodityComment;
import com.sweetcat.storecommodity.interfaces.facade.restdto.CommodityCommentDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/9-20:09
 * @version: 1.0
 */
@Component
public class CommodityCommentRestAssembler {
    public CommodityCommentDTO converterToCommodityCommentDTO(CommodityComment comment) {
        CommodityCommentDTO commentDTO = new CommodityCommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setUserId(comment.getUserId());
        commentDTO.setCommodityId(comment.getCommodityId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setContentPics(comment.getContentPics());
        commentDTO.setStars(comment.getStars());
        commentDTO.setCreateTime(comment.getCreateTime());
        return commentDTO;
    }
}
