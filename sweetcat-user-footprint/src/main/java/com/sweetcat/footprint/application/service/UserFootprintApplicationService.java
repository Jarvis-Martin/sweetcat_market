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
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * ????????????????????????
     *
     * @param command
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOne(AddUserFootprintCommand command) {
        long userId = command.getUserId();
        long commodityId = command.getCommodityId();
        // ?????? ??????id ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ???????????????
        checkUser(userInfo);
        // ?????? ??????id ????????????
        CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(commodityId);
        // ???????????????
        if (commodityInfo == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
        // ?????? ??????id
        long footprintId = snowFlakeService.snowflakeId();
        // ????????????
        UserFootprint userFootprint = new UserFootprint(footprintId, userId, commodityId);
        // ???????????????????????????
        userFootprint.setCurrentPrice(commodityInfo.getCurrentPrice());
        // ??????????????????
        userFootprint.setPicSmall(commodityInfo.getPicSmall());
        // ????????????????????????????????????
        userFootprint.setPriceWhenBrowser(commodityInfo.getCurrentPrice());
        // ??????????????????
        userFootprint.setStartTime(command.getStartTime());
        // ??????????????????
        userFootprint.setBrowserDuration(command.getBrowserDuration());
        // ??????db
        footprintRepository.addOne(userFootprint);
    }

    private void checkUser(UserInfoRpcDTO userInfo) {
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * ????????????????????????
     *
     * @param footprintId footprintId
     */
    @Transactional
    public void deleteOne(Long footprintId) {
        // ?????? ??????id??????
        verifyIdFormatService.verifyIds(footprintId);
        // ?????? foootprintid ?????? footprint
        UserFootprint footprint = footprintRepository.findByFootprintId(footprintId);
        // ??????
        footprintRepository.deleteOne(footprint);
    }

    /**
     * ????????????????????????????????????????????????????????????
     *
     *
     * @param userId
     * @param date  ?????????
     * @param page  page
     * @param limit limit
     * @return
     */
    @Transactional
    public List<UserFootprint> findByDate(Long userId, Long date, Integer page, Integer limit) {
        // ??????????????????
        verifyTimeStampFormatService.verifyTimeStamps(date);
        limit = limit == null || limit < 0 ? 18 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // rpc??????????????????
        List<UserFootprint> footprintPage = footprintRepository.findPageByDate(userId, date, page, limit);
        footprintPage.forEach(
                footprint -> {
                    // rpc ????????????????????????
                    CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(footprint.getCommodityId());
                    // ?????????????????????????????? footprint ?????? ????????????
                    if (commodityInfo != null) {
                        // ??????????????????
                        footprint.setPicSmall(commodityInfo.getPicSmall());
                        // ????????????????????????
                        footprint.setCurrentPrice(commodityInfo.getCurrentPrice());
                    }
                }
        );
        return footprintPage;
    }

}
