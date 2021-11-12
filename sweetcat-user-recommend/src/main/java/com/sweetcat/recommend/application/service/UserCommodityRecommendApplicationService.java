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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UserCommodityRecommendFactory recommendFactory;
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
    public void setRecommendFactory(UserCommodityRecommendFactory recommendFactory) {
        this.recommendFactory = recommendFactory;
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
     * 添加商品推荐
     *
     * @param command
     */
    public void addOne(AddCommodityRecommendCommand command) {
        long referrerId = command.getUserId();
        long commodityId = command.getCommodityId();
        // 检查 id
        verifyIdFormatService.verifyId(referrerId, commodityId);
        // rpc检查用户是否存在
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(referrerId);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 构建 推荐人
        Referrer referrer = new Referrer(referrerId);
        // rpc获取商品基本信息
        CommodityInfoRpcDTO commodityInfoRpcDTO = commodityInfoRpc.findByCommodityId(commodityId);
        // 商品不存在
        if (commodityInfoRpcDTO == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMENTNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
        // 构建被推荐商品
        Commodity commodity = new Commodity(commodityId);
        String commodityName = commodityInfoRpcDTO.getCommodityName();
        String picSmall = commodityInfoRpcDTO.getPicSmall();
        BigDecimal currentPrice = commodityInfoRpcDTO.getCurrentPrice();
        commodity.setCommodityName(commodityName);
        commodity.setPicSmall(picSmall);
        commodity.setCurrentPrice(currentPrice);
        // 假数据：后期再完善
        commodity.setGoodReputationNumber(0L);
        // 假数据：后期再完善
        commodity.setCommoditySpecification("{}");
        // 生成 记录id
        long recordId = snowFlakeService.snowflakeId();
        // 创建领域对象
        RecommendForm recommendForm = new RecommendForm(recordId);
        // 设指领域对象参数
        recommendForm.setReferrer(referrer);
        recommendForm.setCommodity(commodity);
        recommendForm.setReason(command.getReason());
        recommendForm.setCommodityPics(command.getCommodityPics());
        recommendForm.setCreateTime(command.getCreateTime());
        recommendForm.setStar(command.getStar());
        // 加入db
        recommendFormRepository.addOne(recommendForm);
    }

    /**
     * 移除指定商品推荐
     *
     * @param recordId
     */
    public void remove(Long recordId) {
        // 检查 id
        verifyIdFormatService.verifyId(recordId);
        // 找到记录
        RecommendForm form = recommendFormRepository.findByRecordId(recordId);
        // 从db中删除
        recommendFormRepository.remove(form);
    }

    /**
     * 根据推荐人 id 查找分页数据
     *
     * @param referrerId
     * @param page
     * @param limit
     * @return
     */
    public List<RecommendForm> findPageByReferrerId(Long referrerId, Integer page, Integer limit) {
        // 检查id
        verifyIdFormatService.verifyId(referrerId);
        // rpc检查用户是否存在
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(referrerId);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // page limti格式检查
        limit = limit == null || limit < 0 ? 10 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        // 查询并返回
        return recommendFormRepository.findPageByReferrerId(referrerId, page, limit);
    }

    /**
     * 根据推荐记录 id 查找
     *
     * @param recordId recordId
     * @return
     */
    public RecommendForm findByRecordId(Long recordId) {
        // 检查id
        verifyIdFormatService.verifyId(recordId);
        // 查询并返回
        return recommendFormRepository.findByRecordId(recordId);
    }
}
