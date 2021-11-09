package com.sweetcat.storecommodity.infrastructure.factory;

import com.sweetcat.storecommodity.domain.commoditycomment.entity.CommodityComment;
import com.sweetcat.storecommodity.infrastructure.po.CommodityCommentPO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/9-17:31
 * @Version: 1.0
 */
@Component
public class CommodityCommentFactory {

    public CommodityComment create(CommodityCommentPO commentPO) {
        CommodityComment commodityComment = new CommodityComment(commentPO.getCommentId(), commentPO.getUserId(), commentPO.getCommodityId());
        commodityComment.setContent(commentPO.getContent());
        commodityComment.setContentPics(commentPO.getContentPics());
        commodityComment.setStars(commentPO.getStars());
        commodityComment.setCreateTime(commentPO.getCreateTime());
        return commodityComment;
    }
}
