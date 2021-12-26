package com.sweetcat.recommend.infrastructure.repository;

import com.sweetcat.recommend.domain.recommendform.entity.RecommendForm;
import com.sweetcat.recommend.domain.recommendform.repository.RecommendFormRepository;
import com.sweetcat.recommend.infrastructure.dao.UserCommodityRecommendMapper;
import com.sweetcat.recommend.infrastructure.factory.UserCommodityRecommendFactory;
import com.sweetcat.recommend.infrastructure.po.UserCommodityRecommendPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-18:37
 * @version: 1.0
 */
@Repository
public class RecommendFormRepositoryImpl implements RecommendFormRepository {
    private UserCommodityRecommendMapper recommendMapper;
    private UserCommodityRecommendFactory recommendFactory;

    @Autowired
    public void setRecommendFactory(UserCommodityRecommendFactory recommendFactory) {
        this.recommendFactory = recommendFactory;
    }

    @Autowired
    public void setRecommendMapper(UserCommodityRecommendMapper recommendMapper) {
        this.recommendMapper = recommendMapper;
    }

    /**
     * 添加商品推荐
     *
     * @param form
     */
    @Override
    public void addOne(RecommendForm form) {
        recommendMapper.addOne(form);
    }

    /**
     * 移除指定商品推荐
     *
     * @param form
     */
    @Override
    public void remove(RecommendForm form) {
        recommendMapper.deleteOne(form);
    }

    /**
     * 根据推荐人 id 查找分页数据
     *
     * @param referrerId
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<RecommendForm> findPageByReferrerId(Long referrerId, Integer page, Integer limit) {
        List<UserCommodityRecommendPO> recommendFormPOPage = recommendMapper.findPageByReferrerId(referrerId, page, limit);
        if (recommendFormPOPage == null || recommendFormPOPage.isEmpty()) {
            return Collections.emptyList();
        }
        return recommendFormPOPage.stream().collect(
                ArrayList<RecommendForm>::new,
                (con, recommendFormPO) -> con.add(recommendFactory.create(recommendFormPO)),
                ArrayList::addAll
        );
    }

    /**
     * 根据推荐记录 id 查找
     *
     * @param recommendId
     * @return
     */
    @Override
    public RecommendForm findByRecordId(Long recommendId) {
        UserCommodityRecommendPO recommendPO = recommendMapper.findByRecordId(recommendId);
        if (recommendPO == null) {
            return null;
        }
        return recommendFactory.create(recommendPO);
    }
}
