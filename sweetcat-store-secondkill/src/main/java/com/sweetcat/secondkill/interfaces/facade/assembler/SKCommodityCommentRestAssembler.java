package com.sweetcat.secondkill.interfaces.facade.assembler;

import com.sweetcat.secondkill.domain.commoditycomment.entity.SKCommodityComment;
import com.sweetcat.secondkill.interfaces.facade.restdto.SKCommodityCommentDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/9-20:09
 * @version: 1.0
 */
@Component
public class SKCommodityCommentRestAssembler {
    public SKCommodityCommentDTO converterToSKCommodityCommentDTO(SKCommodityComment comment) {
        SKCommodityCommentDTO commentDTO = new SKCommodityCommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setUserId(comment.getPublisher().getPublisherId());
        commentDTO.setCommodityId(comment.getCommodityId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setContentPics(comment.getContentPics());
        commentDTO.setStars(comment.getStars());
        commentDTO.setCreateTime(comment.getCreateTime());
        return commentDTO;
    }
}
