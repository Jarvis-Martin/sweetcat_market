package com.sweetcat.recommend.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.recommend.application.command.AddCommodityRecommendCommand;
import com.sweetcat.recommend.application.rpc.CommodityInfoRpc;
import com.sweetcat.recommend.application.rpc.UserInfoRpc;
import com.sweetcat.recommend.domain.recommendform.entity.Commodity;
import com.sweetcat.recommend.domain.recommendform.entity.RecommendForm;
import com.sweetcat.recommend.domain.recommendform.entity.Referrer;
import com.sweetcat.recommend.domain.recommendform.repository.RecommendFormRepository;
import com.sweetcat.recommend.infrastructure.factory.UserCommodityRecommendFactory;
import com.sweetcat.recommend.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.recommend.infrastructure.service.snowflake_service.SnowFlakeService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-19:32
 * @version: 1.0
 */
@Service
public class UserCommodityRecommendApplicationService {
    private RecommendFormRepository recommendFormRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private CommodityInfoRpc commodityInfoRpc;
    private UserInfoRpc userInfoRpc;

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
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
    public void setRecommendFormRepository(RecommendFormRepository recommendFormRepository) {
        this.recommendFormRepository = recommendFormRepository;
    }

    /**
     * ??????????????????
     *
     * @param command
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOne(AddCommodityRecommendCommand command) {
        long referrerId = command.getUserId();
        long commodityId = command.getCommodityId();
        // ?????? id
        verifyIdFormatService.verifyId(referrerId, commodityId);
        // rpc????????????????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(referrerId);
        // ???????????????
        checkUser(userInfo);
        // ?????? ?????????
        Referrer referrer = new Referrer(referrerId);
        // rpc????????????????????????
        CommodityInfoRpcDTO commodityInfoRpcDTO = commodityInfoRpc.findByCommodityId(commodityId);
        // ???????????????
        if (commodityInfoRpcDTO == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
        // ?????????????????????
        Commodity commodity = new Commodity(commodityId);
        String commodityName = commodityInfoRpcDTO.getCommodityName();
        String picSmall = commodityInfoRpcDTO.getPicSmall();
        BigDecimal currentPrice = commodityInfoRpcDTO.getCurrentPrice();
        commodity.setCommodityName(commodityName);
        commodity.setPicSmall(picSmall);
        commodity.setCurrentPrice(currentPrice);
        // ???????????????????????????
        commodity.setGoodReputationNumber(0L);
        // ???????????????????????????
        commodity.setCommoditySpecification("{}");
        // ?????? ??????id
        long recordId = snowFlakeService.snowflakeId();
        // ??????????????????
        RecommendForm recommendForm = new RecommendForm(recordId);
        // ????????????????????????
        recommendForm.setReferrer(referrer);
        recommendForm.setCommodity(commodity);
        recommendForm.setReason(command.getReason());
        recommendForm.setCommodityPics(command.getCommodityPics());
        recommendForm.setCreateTime(command.getCreateTime());
        recommendForm.setStar(command.getStar());
        // ??????db
        recommendFormRepository.addOne(recommendForm);
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
     * @param recordId
     */
    @Transactional
    public void remove(Long recordId) {
        // ?????? id
        verifyIdFormatService.verifyId(recordId);
        // ????????????
        RecommendForm form = recommendFormRepository.findByRecordId(recordId);
        // ???db?????????
        recommendFormRepository.remove(form);
    }

    /**
     * ??????????????? id ??????????????????
     *
     * @param referrerId
     * @param page
     * @param limit
     * @return
     */
    @Transactional
    public List<RecommendForm> findPageByReferrerId(Long referrerId, Integer page, Integer limit) {
        // ??????id
        verifyIdFormatService.verifyId(referrerId);
        // rpc????????????????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(referrerId);
        // ???????????????
        checkUser(userInfo);
        // page limti????????????
        limit = limit == null || limit < 0 ? 10 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // ???????????????
        return recommendFormRepository.findPageByReferrerId(referrerId, page, limit);
    }

    /**
     * ?????????????????? id ??????
     *
     * @param recordId recordId
     * @return
     */
    @Transactional
    public RecommendForm findByRecordId(Long recordId) {
        // ??????id
        verifyIdFormatService.verifyId(recordId);
        // ???????????????
        return recommendFormRepository.findByRecordId(recordId);
    }
}
