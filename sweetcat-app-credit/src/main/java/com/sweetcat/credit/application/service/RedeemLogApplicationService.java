package com.sweetcat.credit.application.service;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.credit.application.command.AddRedeemLogCommand;
import com.sweetcat.credit.application.rpc.UserInfoRpc;
import com.sweetcat.credit.domain.commodity.entity.BaseCommodity;
import com.sweetcat.credit.domain.commodity.repository.CommodityRepository;
import com.sweetcat.credit.domain.redeemlog.entity.RedeemLog;
import com.sweetcat.credit.domain.redeemlog.entity.RedeemUser;
import com.sweetcat.credit.domain.redeemlog.entity.RedeemedCommodity;
import com.sweetcat.credit.domain.redeemlog.repository.RedeemLogRepository;
import com.sweetcat.credit.infrastructure.cache.BloomFilter;
import com.sweetcat.credit.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.credit.infrastructure.service.snowflake_service.SnowFlakeService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-21:42
 * @version: 1.0
 */
@Service
public class RedeemLogApplicationService {
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private CommodityRepository commodityRepository;
    private RedeemLogRepository redeemLogRepository;
    private UserInfoRpc userInfoRpc;
    private BloomFilter bloomFilter;

    @Autowired
    public void setBloomFilter(BloomFilter bloomFilter) {
        this.bloomFilter = bloomFilter;
    }

    @Autowired
    public void setCommodityRepository(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
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
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setRedeemLogRepository(RedeemLogRepository redeemLogRepository) {
        this.redeemLogRepository = redeemLogRepository;
    }

    /**
     * ??????????????????
     *
     * @param page
     * @param limit
     * @return
     */
    @Transactional
    public List<RedeemLog> findPage(Integer page, Integer limit) {
        // page limit ??????
        limit = limit == null || limit < 0 ? 15 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // ???????????????
        return redeemLogRepository.findPage(page, limit);
    }

    /**
     * ???????????????????????????????????????????????? RedeemedCommodityEvent
     *
     * @param command
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOne(AddRedeemLogCommand command) {
        // ????????????
        Long redeemUserId = command.getRedeemUserId();
        Long commodityId = command.getCommodityId();
        // ?????? redeemUserId, commodityId
        verifyIdFormatService.verifyIds(redeemUserId, commodityId);
        bloomFilter.add(redeemUserId, commodityId);
        // ?????? user with userId ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(redeemUserId);
        // ???????????????
        checkUser(userInfo);
        // ????????????????????????????????????
        BaseCommodity baseCommodity = commodityRepository.findOneMarketItemId(command.getCommodityId());
        // ??????????????? ?????????
        checkCommodity(baseCommodity);
        // ?????? redeemLogId
        long redeemLogId = snowFlakeService.snowflakeId();
        // ?????? redeemUser
        RedeemUser redeemUser = new RedeemUser(redeemUserId);
        // ?????? redeemedCommodity
        RedeemedCommodity redeemedCommodity = new RedeemedCommodity(commodityId);
        redeemedCommodity.setCostCreditNumber(command.getCostCreditNumber());
        // ?????? redeemLog
        RedeemLog redeemLog = new RedeemLog(redeemLogId);
        // ?????? redeemUser
        redeemLog.setRedeemUser(redeemUser);
        // ?????? redeemedCommodity
        redeemLog.setRedeemedCommodity(redeemedCommodity);
        redeemLog.setCreateTime(command.getCreateTime());
        // ????????? db
        redeemLogRepository.addOne(redeemLog);
    }

    private void checkCommodity(BaseCommodity baseCommodity) {
        if (baseCommodity == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
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
     * @param userId
     * @param redeemLogId
     */
    @Transactional
    public void remove(Long userId, Long redeemLogId) {
        // ?????? userId??? redeemLogId
        verifyIdFormatService.verifyIds(userId, redeemLogId);
        // ?????? userId ??? redeemLogId ?????? redeemLog
        RedeemLog redeemLog = redeemLogRepository.findOne(userId, redeemLogId);
        // ???db ??????
        redeemLogRepository.remove(redeemLog);
    }

}
