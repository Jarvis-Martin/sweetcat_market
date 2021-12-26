package com.sweetcat.storecommodity.infrastructure.repository;

import com.sweetcat.storecommodity.domain.commoditycomment.entity.CommodityComment;
import com.sweetcat.storecommodity.domain.commoditycomment.repository.CommodityCommentRepository;
import com.sweetcat.storecommodity.infrastructure.dao.CommodityCommentMapper;
import com.sweetcat.storecommodity.infrastructure.factory.CommodityCommentFactory;
import com.sweetcat.storecommodity.infrastructure.po.CommodityCommentPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/9-17:30
 * @Version: 1.0
 */
@Repository
public class CommodityCommentRepositoryImpl implements CommodityCommentRepository {
    private CommodityCommentMapper commentMapper;
    private CommodityCommentFactory commentFactory;

    @Autowired
    public void setCommentMapper(CommodityCommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Autowired
    public void setCommentFactory(CommodityCommentFactory commentFactory) {
        this.commentFactory = commentFactory;
    }

    @Override
    public CommodityComment findByCommentId(Long commentId) {
        CommodityCommentPO commentPO = commentMapper.findByCommentId(commentId);
        CommodityComment commodityComment = null;
        if (commentPO != null) {
            commodityComment = commentFactory.create(commentPO);
        }
        return commodityComment;
    }

    @Override
    public List<CommodityComment> findPageByCommodityId(Long commodityId, Integer page, Integer limit) {
        List<CommodityCommentPO> commentPOList = commentMapper.findPageByCommodityId(commodityId, page, limit);
        if (commentPOList == null || commentPOList.isEmpty()) {
            return Collections.emptyList();
        }
        return commentPOList.stream().collect(
                ArrayList<CommodityComment>::new,
                (con, commentPO) -> con.add(commentFactory.create(commentPO)),
                ArrayList<CommodityComment>::addAll
        );
    }

    @Override
    public void addOne(CommodityComment commodityComment) {
        commentMapper.insertOne(commodityComment);
    }

    @Override
    public void removeOne(CommodityComment commodityComment) {
        commentMapper.deleteOne(commodityComment);
    }
}
