package com.sweetcat.recommend.infrastructure.dao;

import com.sweetcat.recommend.domain.recommendform.entity.RecommendForm;
import com.sweetcat.recommend.infrastructure.po.UserCommodityRecommendPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCommodityRecommendMapper {

    /**
     * 添加商品推荐
     * @param form
     */
    void addOne(RecommendForm form);

    /**
     * 移除指定商品推荐
     * @param form
     */
    void deleteOne(RecommendForm form);

    /**
     * 根据推荐人 id 查找分页数据
     * @param referrerId
     * @param page
     * @param limit
     * @return
     */
    List<UserCommodityRecommendPO> findPageByReferrerId(@Param("referrerId") Long referrerId, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 根据推荐记录 id 查找
     * @param recommendId
     * @return
     */
    UserCommodityRecommendPO findByRecordId(Long recommendId);
}