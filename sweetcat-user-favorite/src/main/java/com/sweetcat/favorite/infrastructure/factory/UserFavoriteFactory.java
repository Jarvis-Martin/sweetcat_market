package com.sweetcat.favorite.infrastructure.factory;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.favorite.application.rpc.CommodityInfoRpc;
import com.sweetcat.favorite.domain.favorite.entity.UserFavorate;
import com.sweetcat.favorite.infrastructure.po.UserFavoritePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-21:54
 * @version: 1.0
 */
@Component
public class UserFavoriteFactory {
    private CommodityInfoRpc commodityInfoRpc;

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
    }

    public UserFavorate create(UserFavoritePO favoritePO) {
        // 根据favoritePO 构建userFavorite
        UserFavorate userFavorite = new UserFavorate(favoritePO.getFavoriteId(), favoritePO.getUserId(), favoritePO.getCommodityId());
        userFavorite.setPriceWhenFavorite(favoritePO.getPriceWhenFavorite());
        userFavorite.setCreateTime(favoritePO.getCreateTime());
        // rpc获取商品基本信息
        CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(favoritePO.getCommodityId());
        userFavorite.setPicSmall(commodityInfo.getPicSmall());
        userFavorite.setCommodityName(commodityInfo.getCommodityName());
        userFavorite.setCurrentPrice(commodityInfo.getCurrentPrice());
        userFavorite.setAddToCarNumber(commodityInfo.getAddToCarNumber());
        userFavorite.setCoinCounteractNumber(commodityInfo.getCoinCounteractNumber());
        userFavorite.setFeedbackCoinNumber(commodityInfo.getFeedbackCoinNumber());
        return userFavorite;
    }
}
