package com.sweetcat.secondkill.infrastructure.repository;

import com.sweetcat.secondkill.domain.commoditycomment.entity.SKCommodityComment;
import com.sweetcat.secondkill.domain.commoditycomment.repository.SKCommodityCommentRepository;
import com.sweetcat.secondkill.infrastructure.dao.SKCommodityCommentMapper;
import com.sweetcat.secondkill.infrastructure.factory.SKCommodityCommentFactory;
import com.sweetcat.secondkill.infrastructure.po.SKCommodityCommentPO;
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
        SKCommodityComment sKCommodityComment = null;
        if (commentPO != null) {
            sKCommodityComment = commentFactory.create(commentPO);
        }
        return sKCommodityComment;
    }

    @Override
    public List<SKCommodityComment> findPageByCommodityId(Long commodityId, Integer page, Integer limit) {
        List<SKCommodityCommentPO> commentPOList = commentMapper.findPageByCommodityId(commodityId, page, limit);
        if (commentPOList == null || commentPOList.isEmpty()) {
            return Collections.emptyList();
        }
        return commentPOList.stream().collect(
                    ArrayList<SKCommodityComment>::new,
                    (con, commentPO) -> con.add(commentFactory.create(commentPO)),
                    ArrayList<SKCommodityComment>::addAll
            );
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
