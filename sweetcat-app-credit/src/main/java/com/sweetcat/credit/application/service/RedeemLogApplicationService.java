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
import com.sweetcat.credit.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.credit.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 查找分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    public List<RedeemLog> findPage(Integer page, Integer limit) {
        // page limit 检查
        limit = limit == null || limit < 0 ? 15 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // 查询并返回
        return redeemLogRepository.findPage(page, limit);
    }

    /**
     * 添加一条兑换记录，一般由领域事件 RedeemedCommodityEvent
     *
     * @param command
     */
    public void addOne(AddRedeemLogCommand command) {
        // 提取数据
        Long redeemUserId = command.getRedeemUserId();
        Long commodityId = command.getCommodityId();
        // 检查 redeemUserId, commodityId
        verifyIdFormatService.verifyIds(redeemUserId, commodityId);
        // 检查 user with userId 是否存在
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(redeemUserId);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 检查要兑换的商品是否存在
        BaseCommodity baseCommodity = commodityRepository.findOneMarketItemId(command.getCommodityId());
        // 兑换的商品 不存在
        if (baseCommodity == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
        // 生成 redeemLogId
        long redeemLogId = snowFlakeService.snowflakeId();
        // 构建 redeemUser
        RedeemUser redeemUser = new RedeemUser(redeemUserId);
        // 构建 redeemedCommodity
        RedeemedCommodity redeemedCommodity = new RedeemedCommodity(commodityId);
        redeemedCommodity.setCostCreditNumber(command.getCostCreditNumber());
        // 构建 redeemLog
        RedeemLog redeemLog = new RedeemLog(redeemLogId);
        // 填充 redeemUser
        redeemLog.setRedeemUser(redeemUser);
        // 填充 redeemedCommodity
        redeemLog.setRedeemedCommodity(redeemedCommodity);
        redeemLog.setCreateTime(command.getCreateTime());
        // 添加入 db
        redeemLogRepository.addOne(redeemLog);
    }

    /**
     * 移除一条兑换记录
     *
     * @param userId
     * @param redeemLogId
     */
    public void remove(Long userId, Long redeemLogId) {
        // 检查 userId， redeemLogId
        verifyIdFormatService.verifyIds(userId, redeemLogId);
        // 根据 userId 和 redeemLogId 查找 redeemLog
        RedeemLog redeemLog = redeemLogRepository.findOne(userId, redeemLogId);
        // 从db 移除
        redeemLogRepository.remove(redeemLog);
    }

}
