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
        // 检查id
        verifyIdFormatService.verifyIds(userId);
        // 检查用户
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        checkUserInfo(userInfo);
        // 调整page limit
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
        // 检查id
        checkIdAndRelativeObject(userId, commodityId);
        // 生成商品对应的key
        String commodityKey = TrolleyCommodityKeyCreator.getInstance().generateKey(userId.toString(), commodityId.toString());
        // 找到对应的购物车商品
        Commodity commodity = commodityRepository.findOneByUserIdAndCommodityId(userId.toString(), commodityId.toString());
        // 数量 + 1
        commodity.increaseCount();
        // 保存修改
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
        // 检查id
        checkIdAndRelativeObject(userId, commodityId);
        // 找到对应的购物车商品
        Commodity commodity = commodityRepository.findOneByUserIdAndCommodityId(userId.toString(), commodityId.toString());
        // 数量 + 1
        commodity.reduceCount();
        // 保存修改
        commodityRepository.save(commodity);
    }

    public void removeOne(Long userId, Long commodityId) {
        // 检查 用户，商品是否存在
        checkIdAndRelativeObject(userId, commodityId);
        String userIdStr = userId.toString();
        String commodityIdStr = commodityId.toString();
        // 移除购物车(redis.list) 中商品记录
        trolleyRepository.removeOneCommodity(userIdStr, commodityIdStr);
        // 找到并移除商品详细记录
        Commodity commodity = commodityRepository.findOneByUserIdAndCommodityId(userIdStr, commodityIdStr);
        commodityRepository.removeOne(commodity);
        // 触发领域事件
        long domainEventId = snowFlakeService.snowflakeId();
        //      -- 构建 领域事件对象
        RemovedCommodityFromTrolleyEvent removedCommodityFromTrolleyEvent = new RemovedCommodityFromTrolleyEvent(domainEventId);
        //      -- 填充 领域事件
        inflateRemovedCommodityFromTrolleyEvent(userId, commodityId, removedCommodityFromTrolleyEvent);
        //      -- log
        System.out.println("sweetcat-user-trolley: 触发领域事件 UserPublishedSKCommodityCommentEvent 时间为：" + Instant.now().toEpochMilli());
        //      -- 发送领域事件
        domainEventPublisher.syncSend("sweetcat_user_trolley:remove_commodity_from_trolley",removedCommodityFromTrolleyEvent);
    }

    private void inflateRemovedCommodityFromTrolleyEvent(Long userId, Long commodityId, RemovedCommodityFromTrolleyEvent removedCommodityFromTrolleyEvent) {
        removedCommodityFromTrolleyEvent.setOccurOn(Instant.now().toEpochMilli());
        removedCommodityFromTrolleyEvent.setUserId(userId);
        removedCommodityFromTrolleyEvent.setCommodityId(commodityId);
    }

    private void checkIdAndRelativeObject(Long userId, Long commodityId) {
        // 检查id
        verifyIdFormatService.verifyIds(userId, commodityId);
        // 检查用户
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        checkUserInfo(userInfo);
        // 检查商品
        CommodityInfoRpcDTO commodityInfoRpcDTO = commodityInfoRpc.findByCommodityId(commodityId);
        checkCommodity(commodityInfoRpcDTO);
    }

    public void addOne(AddTrolleyCommodityCommand command) {
        Long userId = command.getUserId();
        Long commodityId = command.getCommodityId();
        // rpc 获得用户
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 检查 用户
        checkUserInfo(userInfo);
        // rpc 获得商品
        CommodityInfoRpcDTO commodityInfoRpcDTO = commodityInfoRpc.findByCommodityId(commodityId);
        // 检查 商品
        checkCommodity(commodityInfoRpcDTO);
        // 创建 购物车商品实例
        Commodity commodity = new Commodity();
        // 生成构成车商品唯一标识（redis中key）
        String commodityKey = TrolleyCommodityKeyCreator.getInstance().generateKey(userId.toString(), commodityId.toString());
        //      -- 构建 Commodity.BaseInfo
        BaseInfo baseInfo = new BaseInfo();
        //      -- 填充 Commodity.BaseInfo
        inflateBaseInfo(command, commodityInfoRpcDTO, baseInfo);
        //      -- 填充 Commodity
        inflateTrolleyCommodity(commodity, commodityKey, baseInfo);
        // 查找 trolley
        Trolley trolley = trolleyRepository.findOneByUserIdWithPartOfData(userId.toString(), 0, 0);
        // 往 trolley 中加入商品的 redis key，代表加入购物车
        addOneCommodityToTrolleyService.addOneCommodityToTrolleyService(trolley, commodity);
        // 加购商品，存 redis
        commodityRepository.addOne(commodity);
        // 触发领域事件
        long domainEventId = snowFlakeService.snowflakeId();
        //      -- 构建 领域事件对象
        AddedCommodityToTrolleyEvent addedCommodityToTrolleyEvent = new AddedCommodityToTrolleyEvent(domainEventId);
        //      -- 填充 领域事件
        inflateAddedCommodityToTrolleyEvent(userId, commodityId, addedCommodityToTrolleyEvent);
        //      -- log
        System.out.println("sweetcat-user-trolley: 触发领域事件 UserPublishedSKCommodityCommentEvent 时间为：" + Instant.now().toEpochMilli());
        //      -- 发送领域事件
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
