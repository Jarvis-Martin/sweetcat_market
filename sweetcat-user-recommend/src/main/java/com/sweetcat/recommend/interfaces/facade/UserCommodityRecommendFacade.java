package com.sweetcat.recommend.interfaces.facade;

import com.sweetcat.recommend.application.command.AddCommodityRecommendCommand;
import com.sweetcat.recommend.application.service.UserCommodityRecommendApplicationService;
import com.sweetcat.recommend.domain.recommendform.entity.RecommendForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-20:05
 * @version: 1.0
 */
@Component
public class UserCommodityRecommendFacade {
    private UserCommodityRecommendApplicationService recommendApplicationService;

    @Autowired
    public void setRecommendApplicationService(UserCommodityRecommendApplicationService recommendApplicationService) {
        this.recommendApplicationService = recommendApplicationService;
    }

    /**
     * 添加商品推荐
     *
     * @param command
     */
    public void addOne(AddCommodityRecommendCommand command) {
        recommendApplicationService.addOne(command);
    }

    /**
     * 移除指定商品推荐
     *
     * @param recordId
     */
    public void remove(Long recordId) {
        recommendApplicationService.remove(recordId);
    }

    /**
     * 根据推荐人 id 查找分页数据
     *
     * @param referrerId
     * @param page
     * @param limit
     * @return
     */
    public List<RecommendForm> findPageByReferrerId(Long referrerId, Integer page, Integer limit) {
        return recommendApplicationService.findPageByReferrerId(referrerId, page, limit);
    }

    /**
     * 根据推荐记录 id 查找
     *
     * @param recordId recordId
     * @return
     */
    public RecommendForm findByRecordId(Long recordId) {
        return recommendApplicationService.findByRecordId(recordId);
    }
}
