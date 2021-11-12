package com.sweetcat.favorite.interfaces.facade.assembler;

import com.sweetcat.favorite.domain.favorite.entity.UserFavorate;
import com.sweetcat.favorite.interfaces.facade.restdto.UserFavoriteDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-10:49
 * @version: 1.0
 */
@Component
public class UserFavoriteAssembler {
    public UserFavoriteDTO converterToUserFavoriteDTO(UserFavorate favorite) {
        UserFavoriteDTO userFavoriteDTO = new UserFavoriteDTO();
        userFavoriteDTO.setFavoriteId(favorite.getFavoriteId().toString());
        userFavoriteDTO.setUserId(favorite.getUserId().toString());
        userFavoriteDTO.setCommodityId(favorite.getCommodityId().toString());
        userFavoriteDTO.setPicSmall(favorite.getPicSmall());
        userFavoriteDTO.setCommodityName(favorite.getCommodityName());
        userFavoriteDTO.setCurrentPrice(favorite.getCurrentPrice());
        userFavoriteDTO.setPriceWhenFavorite(favorite.getPriceWhenFavorite());
        userFavoriteDTO.setCreateTime(favorite.getCreateTime());
        userFavoriteDTO.setAddToCarNumber(favorite.getAddToCarNumber());
        userFavoriteDTO.setCoinCounteractNumber(favorite.getCoinCounteractNumber());
        userFavoriteDTO.setFeedbackCoinNumber(favorite.getFeedbackCoinNumber());
        return userFavoriteDTO;
    }
}
