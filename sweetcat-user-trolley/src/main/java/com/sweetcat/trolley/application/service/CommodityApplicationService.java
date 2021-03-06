package com.sweetcat.trolley.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.domainevent.trolley.AddedCommodityToTrolleyEvent;
import com.sweetcat.commons.domainevent.trolley.RemovedCommodityFromTrolleyEvent;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.trolley.application.command.AddTrolleyCommodityCommand;
import com.sweetcat.trolley.application.event.publish.DomainEventPublisher;
import com.sweetcat.trolley.application.rpc.CommodityInfoRpc;
import com.sweetcat.trolley.application.rpc.UserInfoRpc;
import com.sweetcat.trolley.domain.commodity.entity.Commodity;
import com.sweetcat.trolley.domain.commodity.repository.CommodityRepository;
import com.sweetcat.trolley.domain.commodity.vo.BaseInfo;
import com.sweetcat.trolley.domain.service.AddOneCommodityToTrolleyService;
import com.sweetcat.trolley.domain.trolley.entity.Trolley;
import com.sweetcat.trolley.domain.trolley.repository.TrolleyRepository;
import com.sweetcat.trolley.infrastructure.cache.TrolleyCommodityKeyCreator;
import com.sweetcat.trolley.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.trolley.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-20:23
 * @version: 1.0
 */
@Service
public class CommodityApplicationService {
    private TrolleyRepository trolleyRepository;
    private CommodityRepository commodityRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private UserInfoRpc userInfoRpc;
    private CommodityInfoRpc commodityInfoRpc;
    private AddOneCommodityToTrolleyService addOneCommodityToTrolleyService;
    private SnowFlakeService snowFlakeService;
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
    public void setAddOneCommodityToTrolleyService(AddOneCommodityToTrolleyService addOneCommodityToTrolleyService) {
        this.addOneCommodityToTrolleyService = addOneCommodityToTrolleyService;
    }

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
    }

    @Autowired
    public void setTrolleyRepository(TrolleyRepository trolleyRepository) {
        this.trolleyRepository = trolleyRepository;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setCommodityRepository(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    public List<Commodity> findPageTrolleyCommodities(Long userId, Integer page, Integer limit) {
        // ??????id
        verifyIdFormatService.verifyIds(userId);
        // ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        checkUserInfo(userInfo);
        // ??????page limit
        limit = limit == null || limit <= 0 ? 10 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        Trolley trolley = trolleyRepository.findOneByUserIdWithPartOfData(userId.toString(), page, limit);
        List<String> commodityKeys = trolley.getCommodityKeys();
        if (commodityKeys == null || commodityKeys.size() <= 0) {
            return null;
        }
        return commodityKeys.stream().collect(
                ArrayList<Commodity>::new,
                (con, key) -> con.add(commodityRepository.findOneBKey(key)),
                ArrayList<Commodity>::addAll
        );
    }

    private void checkUserInfo(UserInfoRpcDTO userInfo) {
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    public void increaseCount(Long userId, Long commodityId) {
        // ??????id
        checkIdAndRelativeObject(userId, commodityId);
        // ?????????????????????key
        String commodityKey = TrolleyCommodityKeyCreator.getInstance().generateKey(userId.toString(), commodityId.toString());
        // ??????????????????????????????
        Commodity commodity = commodityRepository.findOneByUserIdAndCommodityId(userId.toString(), commodityId.toString());
        // ?????? + 1
        commodity.increaseCount();
        // ????????????
        commodityRepository.save(commodity);
    }

    private void checkCommodity(CommodityInfoRpcDTO commodityInfoRpcDTO) {
        if (commodityInfoRpcDTO == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorMessage()
            );
        }
    }

    public void reduceCount(Long userId, Long commodityId) {
        // ??????id
        checkIdAndRelativeObject(userId, commodityId);
        // ??????????????????????????????
        Commodity commodity = commodityRepository.findOneByUserIdAndCommodityId(userId.toString(), commodityId.toString());
        // ?????? + 1
        commodity.reduceCount();
        // ????????????
        commodityRepository.save(commodity);
    }

    public void removeOne(Long userId, Long commodityId) {
        // ?????? ???????????????????????????
        checkIdAndRelativeObject(userId, commodityId);
        String userIdStr = userId.toString();
        String commodityIdStr = commodityId.toString();
        // ???????????????(redis.list) ???????????????
        trolleyRepository.removeOneCommodity(userIdStr, commodityIdStr);
        // ?????????????????????????????????
        Commodity commodity = commodityRepository.findOneByUserIdAndCommodityId(userIdStr, commodityIdStr);
        commodityRepository.removeOne(commodity);
        // ??????????????????
        long domainEventId = snowFlakeService.snowflakeId();
        //      -- ?????? ??????????????????
        RemovedCommodityFromTrolleyEvent removedCommodityFromTrolleyEvent = new RemovedCommodityFromTrolleyEvent(domainEventId);
        //      -- ?????? ????????????
        inflateRemovedCommodityFromTrolleyEvent(userId, commodityId, removedCommodityFromTrolleyEvent);
        //      -- log
        System.out.println("sweetcat-user-trolley: ?????????????????? UserPublishedSKCommodityCommentEvent ????????????" + Instant.now().toEpochMilli());
        //      -- ??????????????????
        domainEventPublisher.syncSend("sweetcat_user_trolley:remove_commodity_from_trolley",removedCommodityFromTrolleyEvent);
    }

    private void inflateRemovedCommodityFromTrolleyEvent(Long userId, Long commodityId, RemovedCommodityFromTrolleyEvent removedCommodityFromTrolleyEvent) {
        removedCommodityFromTrolleyEvent.setOccurOn(Instant.now().toEpochMilli());
        removedCommodityFromTrolleyEvent.setUserId(userId);
        removedCommodityFromTrolleyEvent.setCommodityId(commodityId);
    }

    private void checkIdAndRelativeObject(Long userId, Long commodityId) {
        // ??????id
        verifyIdFormatService.verifyIds(userId, commodityId);
        // ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        checkUserInfo(userInfo);
        // ????????????
        CommodityInfoRpcDTO commodityInfoRpcDTO = commodityInfoRpc.findByCommodityId(commodityId);
        checkCommodity(commodityInfoRpcDTO);
    }

    public void addOne(AddTrolleyCommodityCommand command) {
        Long userId = command.getUserId();
        Long commodityId = command.getCommodityId();
        // rpc ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ?????? ??????
        checkUserInfo(userInfo);
        // rpc ????????????
        CommodityInfoRpcDTO commodityInfoRpcDTO = commodityInfoRpc.findByCommodityId(commodityId);
        // ?????? ??????
        checkCommodity(commodityInfoRpcDTO);
        // ?????? ?????????????????????
        Commodity commodity = new Commodity();
        // ????????????????????????????????????redis???key???
        String commodityKey = TrolleyCommodityKeyCreator.getInstance().generateKey(userId.toString(), commodityId.toString());
        //      -- ?????? Commodity.BaseInfo
        BaseInfo baseInfo = new BaseInfo();
        //      -- ?????? Commodity.BaseInfo
        inflateBaseInfo(command, commodityInfoRpcDTO, baseInfo);
        //      -- ?????? Commodity
        inflateTrolleyCommodity(commodity, commodityKey, baseInfo);
        // ?????? trolley
        Trolley trolley = trolleyRepository.findOneByUserIdWithPartOfData(userId.toString(), 0, 0);
        // ??? trolley ?????????????????? redis key????????????????????????
        addOneCommodityToTrolleyService.addOneCommodityToTrolleyService(trolley, commodity);
        // ?????????????????? redis
        commodityRepository.addOne(commodity);
        // ??????????????????
        long domainEventId = snowFlakeService.snowflakeId();
        //      -- ?????? ??????????????????
        AddedCommodityToTrolleyEvent addedCommodityToTrolleyEvent = new AddedCommodityToTrolleyEvent(domainEventId);
        //      -- ?????? ????????????
        inflateAddedCommodityToTrolleyEvent(userId, commodityId, addedCommodityToTrolleyEvent);
        //      -- log
        System.out.println("sweetcat-user-trolley: ?????????????????? UserPublishedSKCommodityCommentEvent ????????????" + Instant.now().toEpochMilli());
        //      -- ??????????????????
        domainEventPublisher.syncSend("sweetcat_user_trolley:add_commodity_to_trolley",addedCommodityToTrolleyEvent);
    }

    private void inflateAddedCommodityToTrolleyEvent(Long userId, Long commodityId, AddedCommodityToTrolleyEvent addedCommodityToTrolleyEvent) {
        addedCommodityToTrolleyEvent.setOccurOn(Instant.now().toEpochMilli());
        addedCommodityToTrolleyEvent.setUserId(userId);
        addedCommodityToTrolleyEvent.setCommodityId(commodityId);
    }

    private void inflateTrolleyCommodity(Commodity commodity, String commodityKey, BaseInfo baseInfo) {
        commodity.setKey(commodityKey);
        commodity.setBaseInfo(baseInfo);
        commodity.setCount(1L);
        commodity.setUpdateTime(Instant.now().toEpochMilli());
    }

    private void inflateBaseInfo(AddTrolleyCommodityCommand command, CommodityInfoRpcDTO commodityInfoRpcDTO, BaseInfo baseInfo) {
        baseInfo.setCommodityId(Long.parseLong(commodityInfoRpcDTO.getCommodityId()));
        baseInfo.setCommodityName(commodityInfoRpcDTO.getCommodityName());
        baseInfo.setSpecification(command.getSpecification());
        baseInfo.setOldPrice(commodityInfoRpcDTO.getOldPrice());
        baseInfo.setCurrentPrice(commodityInfoRpcDTO.getCurrentPrice());
        baseInfo.setCreateTime(commodityInfoRpcDTO.getCreateTime());
    }

}
