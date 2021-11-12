package com.sweetcat.recommend.infrastructure.factory;

import com.sweetcat.recommend.application.rpc.CommodityInfoRpc;
import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.recommend.domain.recommendform.entity.Commodity;
import com.sweetcat.recommend.domain.recommendform.entity.RecommendForm;
import com.sweetcat.recommend.domain.recommendform.entity.Referrer;
import com.sweetcat.recommend.infrastructure.po.UserCommodityRecommendPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-18:39
 * @version: 1.0
 */
@Component
public class UserCommodityRecommendFactory {
    private CommodityInfoRpc commodityInfoRpc;

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
    }

    public RecommendForm create(UserCommodityRecommendPO recommendPO) {
        RecommendForm recommendForm = new RecommendForm(recommendPO.getRecordId());
        // 构建 推荐人
        Referrer referrer = new Referrer(recommendPO.getReferrerId());
        recommendForm.setReferrer(referrer);
        // 构建被推荐商品
        Long commodityId = recommendPO.getCommodityId();
        Commodity commodity = new Commodity(commodityId);
        // rpc获取商品基本信息
        CommodityInfoRpcDTO commodityInfoRpcDTO = commodityInfoRpc.findByCommodityId(commodityId);
        // 商品不存在
        if (commodityInfoRpcDTO == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
        String commodityName = commodityInfoRpcDTO.getCommodityName();
        String picSmall = commodityInfoRpcDTO.getPicSmall();
        BigDecimal currentPrice = commodityInfoRpcDTO.getCurrentPrice();
        commodity.setCommodityName(commodityName);
        commodity.setPicSmall(picSmall);
        commodity.setCurrentPrice(currentPrice);
        // 假数据：后期再完善
        commodity.setGoodReputationNumber(0L);
        // 假数据：后期再完善
        commodity.setCommoditySpecification("{}");
        recommendForm.setCommodity(commodity);
        recommendForm.setReason(recommendPO.getReason());
        recommendForm.setCommodityPics(recommendPO.getCommodityPics());
        recommendForm.setCreateTime(recommendPO.getCreateTime());
        recommendForm.setStar(recommendPO.getStar());
        return recommendForm;
    }
}
