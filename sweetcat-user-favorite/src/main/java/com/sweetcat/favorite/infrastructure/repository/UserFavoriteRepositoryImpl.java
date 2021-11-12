package com.sweetcat.favorite.infrastructure.repository;

import com.sweetcat.favorite.domain.favorite.entity.UserFavorate;
import com.sweetcat.favorite.domain.favorite.repository.UserFavoriteRepository;
import com.sweetcat.favorite.infrastructure.dao.UserFavoriteMapper;
import com.sweetcat.favorite.infrastructure.factory.UserFavoriteFactory;
import com.sweetcat.favorite.infrastructure.po.UserFavoritePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-21:53
 * @version: 1.0
 */
@Repository
public class UserFavoriteRepositoryImpl implements UserFavoriteRepository {
    private UserFavoriteMapper favorateMapper;
    private UserFavoriteFactory favoriteFactory;

    @Autowired
    public void setFavorateMapper(UserFavoriteMapper favorateMapper) {
        this.favorateMapper = favorateMapper;
    }

    @Autowired
    public void setFavoriteFactory(UserFavoriteFactory favoriteFactory) {
        this.favoriteFactory = favoriteFactory;
    }

    /**
     * 添加收藏商品记录
     *
     * @param favorate favorate
     */
    @Override
    public void addOne(UserFavorate favorate) {
        favorateMapper.addOne(favorate);
    }

    /**
     * 删除收藏的商品
     *
     * @param favorate
     */
    @Override
    public void remove(UserFavorate favorate) {
        favorateMapper.deleteOne(favorate);
    }


    /**
     * 查询 用户id 为 userId的用户的收藏夹分页数据
     *
     * @param userid userid
     * @param page   page
     * @param limit  limit
     * @return
     */
    @Override
    public List<UserFavorate> findPageByUserId(Long userid, Integer page, Integer limit) {
        List<UserFavoritePO> userFavoritePOPage = favorateMapper.findPageByUserId(userid, page, limit);
        ArrayList<UserFavorate> userFavoratePage = userFavoritePOPage.stream().collect(
                ArrayList<UserFavorate>::new,
                (con, userFavoritePO) -> con.add(favoriteFactory.create(userFavoritePO)),
                ArrayList::addAll
        );
        return userFavoratePage;
    }


    /**
     * 根据favoriteId查找 favorite
     * @param favoriteId
     * @return
     */
    @Override
    public UserFavorate findByFavoriteId(Long favoriteId) {
        UserFavoritePO favoratePO = favorateMapper.findByFavoriteId(favoriteId);
        if (favoratePO == null) {
            return null;
        }
        return favoriteFactory.create(favoratePO);
    }
}
