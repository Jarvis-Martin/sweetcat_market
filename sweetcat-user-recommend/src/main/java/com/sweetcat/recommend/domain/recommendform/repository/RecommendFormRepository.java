package com.sweetcat.recommend.domain.recommendform.repository;

import com.sweetcat.recommend.domain.recommendform.entity.RecommendForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-18:36
 * @version: 1.0
 */
public interface RecommendFormRepository {
    /**
     * 添加商品推荐
     *
     * @param form
     */
    void addOne(RecommendForm form);

    /**
     * 移除指定商品推荐
     *
     * @param form
     */
    void remove(RecommendForm form);

    /**
     * 根据推荐人 id 查找分页数据
     *
     * @param referrerId
     * @param page
     * @param limit
     * @return
     */
    List<RecommendForm> findPageByReferrerId(@Param("referrerId") Long referrerId, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 根据推荐记录 id 查找
     *
     * @param recommendId
     * @return
     */
    RecommendForm findByRecordId(Long recommendId);
}
