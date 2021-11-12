package com.sweetcat.favorite.infrastructure.dao;

import com.sweetcat.favorite.domain.favorite.entity.UserFavorate;
import com.sweetcat.favorite.infrastructure.po.UserFavoritePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFavoriteMapper {
    /**
     * 添加收藏商品记录
     *
     * @param favorite
     */
    void addOne(UserFavorate favorite);

    /**
     * 删除收藏的商品
     *
     * @param favorate
     */
    void deleteOne(UserFavorate favorate);

    /**
     * 查询 用户id 为 userId的用户的收藏夹分页数据
     * @param userid userid
     * @param page page
     * @param limit limit
     * @return
     */
    List<UserFavoritePO> findPageByUserId(@Param("userId") Long userid, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 根据favoriteId查找 favorite
     * @param favoriteId
     * @return
     */
    UserFavoritePO findByFavoriteId(Long favoriteId);
}