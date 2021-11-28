package com.sweetcat.secondkill.infrastructure.repository;

import com.sweetcat.secondkill.domain.commoditycomment.entity.SKCommodityComment;
import com.sweetcat.secondkill.domain.commoditycomment.repository.SKCommodityCommentRepository;
import com.sweetcat.secondkill.infrastructure.dao.SKCommodityCommentMapper;
import com.sweetcat.secondkill.infrastructure.factory.SKCommodityCommentFactory;
import com.sweetcat.secondkill.infrastructure.po.SKCommodityCommentPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/9-17:30
 * @Version: 1.0
 */
@Repository
public class SKCommodityCommentRepositoryImpl implements SKCommodityCommentRepository {
    private SKCommodityCommentMapper commentMapper;
    private SKCommodityCommentFactory commentFactory;

    @Autowired
    public void setCommentMapper(SKCommodityCommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Autowired
    public void setCommentFactory(SKCommodityCommentFactory commentFactory) {
        this.commentFactory = commentFactory;
    }

    @Override
    public SKCommodityComment findByCommentId(Long commentId) {
        SKCommodityCommentPO commentPO = commentMapper.findByCommentId(commentId);
        SKCommodityComment SKCommodityComment = null;
        if (commentPO != null) {
            SKCommodityComment = commentFactory.create(commentPO);
        }
        return SKCommodityComment;
    }

    @Override
    public List<SKCommodityComment> findPageByCommodityId(Long commodityId, Integer page, Integer limit) {
        List<SKCommodityCommentPO> commentPOList = commentMapper.findPageByCommodityId(commodityId, page, limit);
        ArrayList<SKCommodityComment> commentList = null;
        if (commentPOList != null) {
            commentList = commentPOList.stream().collect(
                    ArrayList<SKCommodityComment>::new,
                    (con, commentPO) -> con.add(commentFactory.create(commentPO)),
                    ArrayList::addAll
            );
        }
        return commentList;
    }

    @Override
    public void addOne(SKCommodityComment SKCommodityComment) {
        commentMapper.insertOne(SKCommodityComment);
    }

    @Override
    public void removeOne(SKCommodityComment SKCommodityComment) {
        commentMapper.deleteOne(SKCommodityComment);
    }
}
