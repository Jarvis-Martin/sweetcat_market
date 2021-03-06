package com.sweetcat.favorite.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.domainevent.userfavorite.AddedCommodityToFavoriteEvent;
import com.sweetcat.commons.domainevent.userfavorite.RemovedCommodityFromFavoriteEvent;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.favorite.application.command.AddUserFavoriteCommand;
import com.sweetcat.favorite.application.event.publish.DomainEventPublisher;
import com.sweetcat.favorite.application.rpc.CommodityInfoRpc;
import com.sweetcat.favorite.application.rpc.UserInfoRpc;
import com.sweetcat.favorite.domain.favorite.entity.UserFavorate;
import com.sweetcat.favorite.domain.favorite.repository.UserFavoriteRepository;
import com.sweetcat.favorite.domain.favorite.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.favorite.infrastructure.service.snowflake_service.SnowFlakeService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-22:15
 * @version: 1.0
 */
@Service
public class UserFavoriteApplicationService {
    Logger logger = LoggerFactory.getLogger(UserFavoriteApplicationService.class);
    private UserFavoriteRepository favoriteRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private UserInfoRpc userInfoRpc;
    private CommodityInfoRpc commodityInfoRpc;
    private DomainEventPublisher domainEventPublisher;

    @Autowired
    public void setDomainEventPublisher(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

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
     * ????????????????????????
     *
     * @param command command
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOne(AddUserFavoriteCommand command) {
        long userId = command.getUserId();
        long commodityId = command.getCommodityId();
        // ??????????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ???????????????
        checkUserInfo(userInfo);
        // ??????????????????
        CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(commodityId);
        // ???????????????
        checkCommodityInfo(userInfo, commodityInfo);
        // ?????? id
        long favoriteId = snowFlakeService.snowflakeId();
        // ?????? favorite
        UserFavorate userFavorate = new UserFavorate(favoriteId, userId, commodityId);
        // ??????????????????????????????????????????
        userFavorate.setPriceWhenFavorite(commodityInfo.getCurrentPrice());
        // ??????????????????
        userFavorate.setCreateTime(command.getCreateTime());
        // ??????db
        favoriteRepository.addOne(userFavorate);
        // ?????????????????? AddedCommodityToFavoriteEvent
        long domainEventId = snowFlakeService.snowflakeId();
        //      -- ?????? AddedCommodityToFavoriteEvent ??????
        AddedCommodityToFavoriteEvent addedCommodityToFavoriteEvent = new AddedCommodityToFavoriteEvent(domainEventId);
        //      -- ?????? ????????????
        inflateAddedCommodityToFavoriteEvent(userId, commodityId, addedCommodityToFavoriteEvent);
        //      -- log
        logger.info("sweetcat-user-favorite ?????????????????? AddedCommodityToFavoriteEvent ????????????{}", Instant.now().toEpochMilli());
        //      -- ??????
        domainEventPublisher.syncSend("sweetcat_user_favorite:add_commodity_to_favorite",addedCommodityToFavoriteEvent);
    }

    private void inflateAddedCommodityToFavoriteEvent(long userId, long commodityId, AddedCommodityToFavoriteEvent addedCommodityToFavoriteEvent) {
        addedCommodityToFavoriteEvent.setOccurOn(Instant.now().toEpochMilli());
        addedCommodityToFavoriteEvent.setUserId(userId);
        addedCommodityToFavoriteEvent.setCommodityId(commodityId);
    }

    private void checkCommodityInfo(UserInfoRpcDTO userInfo, CommodityInfoRpcDTO commodityInfo) {
        if (commodityInfo == null) {
            if (userInfo == null) {
                throw new CommodityNotExistedException(
                        ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                        ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
                );
            }
        }
    }

    private void checkUserInfo(UserInfoRpcDTO userInfo) {
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * ?????????????????????
     *
     * @param favoriteId favoriteId
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void removeOne(Long favoriteId) {
        // ?????? favorite id
        UserFavorate userFavorate = favoriteRepository.findByFavoriteId(favoriteId);
        // ??????
        favoriteRepository.remove(userFavorate);
        // ?????????????????? RemovedCommodityFromFavoriteEvent
        long domainEventId = snowFlakeService.snowflakeId();
        //      -- ?????? RemovedCommodityFromFavoriteEvent ??????
        RemovedCommodityFromFavoriteEvent removedCommodityFromFavoriteEvent = new RemovedCommodityFromFavoriteEvent(domainEventId);
        //      -- ?????? ????????????
        inflateRemovedCommodityFromFavoriteEvent(userFavorate, removedCommodityFromFavoriteEvent);
        //      -- log
        logger.info("sweetcat-user-favorite ?????????????????? RemovedCommodityFromFavoriteEvent ????????????" + Instant.now().toEpochMilli());
        //      -- ??????
        domainEventPublisher.syncSend("sweetcat_user_favorite:remove_commodity_from_favorite", removedCommodityFromFavoriteEvent);
    }

    private void inflateRemovedCommodityFromFavoriteEvent(UserFavorate userFavorate, RemovedCommodityFromFavoriteEvent removedCommodityFromFavoriteEvent) {
        removedCommodityFromFavoriteEvent.setOccurOn(Instant.now().toEpochMilli());
        removedCommodityFromFavoriteEvent.setUserId(userFavorate.getUserId());
        removedCommodityFromFavoriteEvent.setCommodityId(userFavorate.getCommodityId());
    }

    /**
     * ?????? ??????id ??? userId?????????????????????????????????
     *
     * @param userid userid
     * @param page   page
     * @param limit  limit
     * @return
     */
    @Transactional
    public List<UserFavorate> findPageByUserId(Long userid, Integer page, Integer limit) {
        // ??????id
        verifyIdFormatService.verifyIds(userid);
        // ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userid);
        // ???????????????
        checkUserInfo(userInfo);
        limit = limit == null || limit < 0 ? 0 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // ??????
        return favoriteRepository.findPageByUserId(userid, page, limit);
    }
}