package com.sweetcat.recommend.interfaces.facade.assembler;

import com.sweetcat.recommend.domain.recommendform.entity.RecommendForm;
import com.sweetcat.recommend.interfaces.facade.restdto.UserCommodityRecommendRestDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-20:05
 * @version: 1.0
 */
@Component
public class UserCommodityRecommendAssembler {
    public UserCommodityRecommendRestDTO converterToUserCommodityRecommendRestDTO(RecommendForm form) {
        UserCommodityRecommendRestDTO recommendRestDTO = new UserCommodityRecommendRestDTO();
        recommendRestDTO.setRecordId(form.getRecordId().toString());
        // 填充推荐人信息
        HashMap<String, Object> referrer = new HashMap<>(2);
        referrer.put("referrerId", form.getReferrer().getReferrerId().toString());
        recommendRestDTO.setReferrer(referrer);
        // 填充被推荐商品信息
        HashMap<String, Object> commodity = new HashMap<>(2);
        commodity.put("commodityId", form.getCommodity().getCommodityId().toString());
        commodity.put("commodityName", form.getCommodity().getCommodityName());
        commodity.put("picSmall", form.getCommodity().getPicSmall());
        commodity.put("currentPrice", form.getCommodity().getCurrentPrice());
        commodity.put("goodReputationNumber", form.getCommodity().getGoodReputationNumber());
        commodity.put("commoditySpecification", form.getCommodity().getCommoditySpecification());
        recommendRestDTO.setCommodity(commodity);

        recommendRestDTO.setReason(form.getReason());
        recommendRestDTO.setCommodityPics(form.getCommodityPics());
        recommendRestDTO.setCreateTime(form.getCreateTime());
        return recommendRestDTO;
    }
}
