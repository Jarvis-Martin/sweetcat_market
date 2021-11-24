package com.sweetcat.usercomment.interfaces.facade.assembler;

import com.sweetcat.usercomment.domain.comment.entity.Comment;
import com.sweetcat.usercomment.domain.comment.entity.CommentComment;
import com.sweetcat.usercomment.domain.comment.entity.CommodityComment;
import com.sweetcat.usercomment.interfaces.facade.restdto.CommentCommentRestDTO;
import com.sweetcat.usercomment.interfaces.facade.restdto.CommentRestDTO;
import com.sweetcat.usercomment.interfaces.facade.restdto.CommodityCommentRestDTO;
import com.sweetcat.usercomment.interfaces.facade.restdto.PublisherRestDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/23-23:19
 * @version: 1.0
 */
@Component
public class CommentAssembler {
    public <RestDTO extends CommentRestDTO, C extends Comment> RestDTO converterToCommentRestDTO(C comment) {
        // 如果 comment 是商品评价
        if (Comment.COMMENTTYPE_COMMODITY.equals(comment.getCommentType())) {
            CommodityComment commodityComment = (CommodityComment) comment;
            CommodityCommentRestDTO commodityCommentRestDTO = new CommodityCommentRestDTO(commodityComment.getCommentId());
            PublisherRestDTO publisherRestDTO = new PublisherRestDTO(commodityComment.getPublisher().getPublisherId());
            // 填充 CommodityCommentRestDTO
            inflateCommodityCommentRestDTO(commodityComment, commodityCommentRestDTO, publisherRestDTO);
            return (RestDTO) commodityCommentRestDTO;
        }
        // 如果 comment 是评论评论
        if (Comment.COMMENTTYPE_COMMENT.equals(comment.getCommentType())) {
            CommentComment commentComment = (CommentComment) comment;
            CommentCommentRestDTO commentCommentRestDTO = new CommentCommentRestDTO(commentComment.getCommentId());
            PublisherRestDTO publisherRestDTO = new PublisherRestDTO(commentComment.getPublisher().getPublisherId());
            // 填充 CommodityCommentRestDTO
            inflateCommentCommentRestDTO(commentComment, commentCommentRestDTO, publisherRestDTO);
            return (RestDTO) commentCommentRestDTO;
        }
        return null;
    }

    /**
     * 填充 CommodityCommentRestDTO
     * @param commentComment
     * @param commentCommentRestDTO
     * @param publisherRestDTO
     */
    private void inflateCommentCommentRestDTO(CommentComment commentComment, CommentCommentRestDTO commentCommentRestDTO, PublisherRestDTO publisherRestDTO) {
        // 填充 CommodityCommentRestDTO
        commentCommentRestDTO.setParentCommentId(commentComment.getParentCommentId());
        commentCommentRestDTO.setPublisher(publisherRestDTO);
        commentCommentRestDTO.setContent(commentComment.getContent());
        commentCommentRestDTO.setContentPics(commentComment.getContentPics());
        commentCommentRestDTO.setCreateTime(commentComment.getCreateTime());
        commentCommentRestDTO.setCommentType(commentComment.getCommentType());
    }

    /**
     * 填充 CommodityCommentRestDTO
     * @param commodityComment
     * @param commodityCommentRestDTO
     * @param publisherRestDTO
     */
    private void inflateCommodityCommentRestDTO(CommodityComment commodityComment, CommodityCommentRestDTO commodityCommentRestDTO, PublisherRestDTO publisherRestDTO) {
        commodityCommentRestDTO.setCommodityId(commodityComment.getCommodityId());
        commodityCommentRestDTO.setStars(commodityComment.getStars());
        commodityCommentRestDTO.setPublisher(publisherRestDTO);
        commodityCommentRestDTO.setContent(commodityComment.getContent());
        commodityCommentRestDTO.setContentPics(commodityComment.getContentPics());
        commodityCommentRestDTO.setCreateTime(commodityComment.getCreateTime());
        commodityCommentRestDTO.setCommentType(commodityComment.getCommentType());
    }
}
