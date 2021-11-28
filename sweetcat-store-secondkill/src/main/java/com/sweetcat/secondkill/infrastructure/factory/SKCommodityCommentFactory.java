package com.sweetcat.secondkill.infrastructure.factory;

import com.sweetcat.secondkill.domain.commoditycomment.entity.SKCommodityComment;
import com.sweetcat.secondkill.domain.commoditycomment.vo.Publisher;
import com.sweetcat.secondkill.infrastructure.po.SKCommodityCommentPO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/9-17:31
 * @Version: 1.0
 */
@Component
public class SKCommodityCommentFactory {

    public SKCommodityComment create(SKCommodityCommentPO commentPO) {
        SKCommodityComment commodityComment = new SKCommodityComment(commentPO.getCommentId());
        Publisher publisher = new Publisher(commentPO.getUserId());
        inflateSKCommodityComment(commentPO, commodityComment, publisher);
        return commodityComment;
    }

    private void inflateSKCommodityComment(SKCommodityCommentPO commentPO, SKCommodityComment commodityComment, Publisher publisher) {
        commodityComment.setPublisher(publisher);
        commodityComment.setCommodityId(commentPO.getCommodityId());
        commodityComment.setContent(commentPO.getContent());
        commodityComment.setContentPics(commentPO.getContentPics());
        commodityComment.setStars(commentPO.getStars());
        commodityComment.setCreateTime(commentPO.getCreateTime());
    }
}
