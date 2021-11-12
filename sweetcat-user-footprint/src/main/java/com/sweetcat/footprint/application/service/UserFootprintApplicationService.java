package com.sweetcat.footprint.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.footprint.application.command.AddUserFootprintCommand;
import com.sweetcat.footprint.application.rpc.CommodityInfoRpc;
import com.sweetcat.footprint.application.rpc.UserInfoRpc;
import com.sweetcat.footprint.domain.footprint.entity.UserFootprint;
import com.sweetcat.footprint.domain.footprint.repository.UserFootprintRepository;
import com.sweetcat.footprint.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.footprint.infrastructure.service.snowflake_service.SnowFlakeService;
import com.sweetcat.footprint.infrastructure.service.timestamp_format_verfiy_service.VerifyTimeStampFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-22:40
 * @version: 1.0
 */
@Service
public class UserFootprintApplicationService {
    private VerifyIdFormatService verifyIdFormatService;
    private VerifyTimeStampFormatService verifyTimeStampFormatService;
    private SnowFlakeService snowFlakeService;
    private UserInfoRpc userInfoRpc;
    private CommodityInfoRpc commodityInfoRpc;
    private UserFootprintRepository footprintRepository;

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setVerifyTimeStampFormatService(VerifyTimeStampFormatService verifyTimeStampFormatService) {
        this.verifyTimeStampFormatService = verifyTimeStampFormatService;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setFootprintRepository(UserFootprintRepository footprintRepository) {
        this.footprintRepository = footprintRepository;
    }


    /**
     * 添加一条足迹记录
     *
     * @param command
     */
    public void addOne(AddUserFootprintCommand command) {
        long userId = command.getUserId();
        long commodityId = command.getCommodityId();
        // 检查 用户id 是否存在
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 检查 商品id 是否存在
        CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(commodityId);
        // 商品不存在
        if (commodityInfo == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
        // 生成 足迹id
        long footprintId = snowFlakeService.snowflakeId();
        // 创建足迹
        UserFootprint userFootprint = new UserFootprint(footprintId, userId, commodityId);
        // 设置足迹商品的现价
        userFootprint.setCurrentPrice(commodityInfo.getCurrentPrice());
        // 设置商品主图
        userFootprint.setPicSmall(commodityInfo.getPicSmall());
        // 设置用户浏览时商品的价格
        userFootprint.setPriceWhenBrowser(commodityInfo.getCurrentPrice());
        // 设置足迹时间
        userFootprint.setStartTime(command.getStartTime());
        // 设置浏览时长
        userFootprint.setBrowserDuration(command.getBrowserDuration());
        // 加入db
        footprintRepository.addOne(userFootprint);
    }

    /**
     * 删除一条足迹记录
     *
     * @param footprintId footprintId
     */
    public void deleteOne(Long footprintId) {
        // 验证 足迹id格式
        verifyIdFormatService.verifyIds(footprintId);
        // 根据 foootprintid 找到 footprint
        UserFootprint footprint = footprintRepository.findByFootprintId(footprintId);
        // 删除
        footprintRepository.deleteOne(footprint);
    }

    /**
     * 根据时间戳查找该时间戳之前的足迹分页数据
     *
     *
     * @param userId
     * @param date  时间戳
     * @param page  page
     * @param limit limit
     * @return
     */
    public List<UserFootprint> findByDate(Long userId, Long date, Integer page, Integer limit) {
        // 检查时间格式
        verifyTimeStampFormatService.verifyTimeStamps(date);
        limit = limit == null || limit < 0 ? 18 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // rpc查寻商品现价
        List<UserFootprint> footprintPage = footprintRepository.findPageByDate(userId, date, page, limit);
        footprintPage.forEach(
                footprint -> {
                    // rpc 调用获得商品信息
                    CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(footprint.getCommodityId());
                    // 商品存在，给查询出的 footprint 填充 商品现价
                    if (commodityInfo != null) {
                        // 设置商品主图
                        footprint.setPicSmall(commodityInfo.getPicSmall());
                        // 设置足迹商品现价
                        footprint.setCurrentPrice(commodityInfo.getCurrentPrice());
                    }
                }
        );
        return footprintPage;
    }

}
