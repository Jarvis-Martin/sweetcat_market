package com.sweetcat.favorite.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.favorite.application.command.AddUserFavoriteCommand;
import com.sweetcat.favorite.application.rpc.CommodityInfoRpc;
import com.sweetcat.favorite.application.rpc.UserInfoRpc;
import com.sweetcat.favorite.domain.favorite.entity.UserFavorate;
import com.sweetcat.favorite.domain.favorite.repository.UserFavoriteRepository;
import com.sweetcat.favorite.domain.favorite.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.favorite.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-22:15
 * @version: 1.0
 */
@Service
public class UserFavoriteApplicationService {
    private UserFavoriteRepository favoriteRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private UserInfoRpc userInfoRpc;
    private CommodityInfoRpc commodityInfoRpc;

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
    }

    @Autowired
    public void setFavoriteRepository(UserFavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    /**
     * 添加收藏商品记录
     *
     * @param command command
     */
    public void addOne(AddUserFavoriteCommand command) {
        long userId = command.getUserId();
        long commodityId = command.getCommodityId();
        // 检查用户信息
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 检查商品信息
        CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(commodityId);
        // 商品不存在
        if (commodityInfo == null) {
            if (userInfo == null) {
                throw new CommodityNotExistedException(
                        ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                        ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
                );
            }
        }
        // 生成 id
        long favoriteId = snowFlakeService.snowflakeId();
        // 创建 favorite
        UserFavorate userFavorate = new UserFavorate(favoriteId, userId, commodityId);
        // 设置商品收藏时，商品最新价格
        userFavorate.setPriceWhenFavorite(commodityInfo.getCurrentPrice());
        // 设置收藏时间
        userFavorate.setCreateTime(command.getCreateTime());
        // 加入db
        favoriteRepository.addOne(userFavorate);
    }

    /**
     * 删除收藏的商品
     *
     * @param favoriteId favoriteId
     */
    public void remove(Long favoriteId) {
        // 获取 favorite id
        UserFavorate userFavorate = favoriteRepository.findByFavoriteId(favoriteId);
        // 移除
        favoriteRepository.remove(userFavorate);
    }

    /**
     * 查询 用户id 为 userId的用户的收藏夹分页数据
     *
     * @param userid userid
     * @param page   page
     * @param limit  limit
     * @return
     */
    public List<UserFavorate> findPageByUserId(Long userid, Integer page, Integer limit) {
        // 检查id
        verifyIdFormatService.verifyIds(userid);
        // 活得信息
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userid);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        limit = limit == null || limit < 0 ? 0 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // 查找
        return favoriteRepository.findPageByUserId(userid, page, limit);
    }
}