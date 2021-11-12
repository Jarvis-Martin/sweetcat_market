package com.sweetcat.favorite.interfaces.facade;

import com.sweetcat.favorite.application.command.AddUserFavoriteCommand;
import com.sweetcat.favorite.application.service.UserFavoriteApplicationService;
import com.sweetcat.favorite.domain.favorite.entity.UserFavorate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-10:26
 * @version: 1.0
 */
@Component
public class UserFootprintFacade {
    private UserFavoriteApplicationService favoriteApplicationService;

    @Autowired
    public void setFavoriteApplicationService(UserFavoriteApplicationService favoriteApplicationService) {
        this.favoriteApplicationService = favoriteApplicationService;
    }

    /**
     * 添加收藏商品记录
     *
     * @param command command
     */
    public void addOne(AddUserFavoriteCommand command) {
        favoriteApplicationService.addOne(command);
    }

    /**
     * 删除收藏的商品
     *
     * @param favoriteId favoriteId
     */
    public void deleteOne(Long favoriteId) {
        favoriteApplicationService.remove(favoriteId);
    }

    /**
     * 查询 用户id 为 userId的用户的收藏夹分页数据
     * @param userid userid
     * @param page page
     * @param limit limit
     * @return
     */
    public List<UserFavorate> findPageByUserId(Long userid, Integer page, Integer limit) {
        return favoriteApplicationService.findPageByUserId(userid, page, limit);
    }

}
