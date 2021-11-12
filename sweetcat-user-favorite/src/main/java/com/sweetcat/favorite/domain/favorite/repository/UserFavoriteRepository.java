package com.sweetcat.favorite.domain.favorite.repository;

import com.sweetcat.favorite.domain.favorite.entity.UserFavorate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-21:53
 * @version: 1.0
 */
public interface UserFavoriteRepository {
    /**
     * 添加收藏商品记录
     *
     * @param favorate
     */
    void addOne(UserFavorate favorate);

    /**
     * 删除收藏的商品
     *
     * @param favorate
     */
    void remove(UserFavorate favorate);

    /**
     * 查询 用户id 为 userId的用户的收藏夹分页数据
     *
     * @param userid userid
     * @param page   page
     * @param limit  limit
     * @return
     */
    List<UserFavorate> findPageByUserId(@Param("userId") Long userid, @Param("page") Integer page, @Param("limit") Integer limit);

    UserFavorate findByFavoriteId(Long favoriteId);
}
