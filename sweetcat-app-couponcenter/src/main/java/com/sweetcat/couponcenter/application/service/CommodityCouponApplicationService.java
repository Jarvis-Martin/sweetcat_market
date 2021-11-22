package com.sweetcat.couponcenter.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.StoreNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.couponcenter.application.command.AddCommodityCouponCommand;
import com.sweetcat.couponcenter.application.rpc.CommodityInfoRpc;
import com.sweetcat.couponcenter.application.rpc.StoreInfoRpc;
import com.sweetcat.couponcenter.application.rpc.UserInfoRpc;
import com.sweetcat.couponcenter.domain.coupon.entity.CommodityCoupon;
import com.sweetcat.couponcenter.domain.coupon.entity.Coupon;
import com.sweetcat.couponcenter.domain.coupon.repository.CommodityCouponRepository;
import com.sweetcat.couponcenter.domain.coupon.vo.Commodity;
import com.sweetcat.couponcenter.domain.coupon.vo.Creator;
import com.sweetcat.couponcenter.domain.coupon.vo.Store;
import com.sweetcat.couponcenter.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.couponcenter.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-21:47
 * @version: 1.0
 */
@Service
public class CommodityCouponApplicationService {
    private SnowFlakeService snowFlakeService;
    private VerifyIdFormatService verifyIdFormatService;
    private UserInfoRpc userInfoRpc;
    private StoreInfoRpc storeInfoRpc;
    private CommodityInfoRpc commodityInfoRpc;
    private CommodityCouponRepository commodityCouponRepository;

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
    }

    @Autowired
    public void setStoreInfoRpc(StoreInfoRpc storeInfoRpc) {
        this.storeInfoRpc = storeInfoRpc;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setCommodityCouponRepository(CommodityCouponRepository commodityCouponRepository) {
        this.commodityCouponRepository = commodityCouponRepository;
    }

    /**
     * 查询分页数据
     *
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    public List<CommodityCoupon> findPage(Integer targetType, Integer page, Integer limit) {
        // 调整 page,limit
        limit = limit == null || limit < 0 ? 15 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        return commodityCouponRepository.findPage(targetType, page, limit);
    }

    public CommodityCoupon findOneByCouponId(Long couponId) {
        verifyIdFormatService.verifyIds(couponId);
        return commodityCouponRepository.findOneByCouponId(couponId);
    }

    /**
     * add a commodity coupon
     * @param command
     */
    public void addOne(AddCommodityCouponCommand command) {
        Long creatorId = command.getCreatorId();
        Integer couponTargetType = command.getTargetType();
        // 检查id
        verifyIdFormatService.verifyIds(creatorId);
        // 检查用户
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(creatorId);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 商品券
        Long storeId = command.getStoreId();
        Long commodityId = command.getCommodityId();
        // 检查 id
        verifyIdFormatService.verifyIds(storeId, commodityId);
        // 检查 store
        StoreInfoRpcDTO storeInfo = storeInfoRpc.findOneByStoreId(storeId);
        // 店铺不存在
        if (storeInfo == null) {
            throw new StoreNotExistedException(
                    ResponseStatusEnum.STORENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.STORENOTEXISTED.getErrorMessage()
            );
        }
        // 检查 commodity
        CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(commodityId);
        // 商品不存在
        if (commodityInfo == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
        // 生成couponId
        long couponId = snowFlakeService.snowflakeId();
        Creator creator = new Creator(command.getCreatorId());
        Long createTime = command.getCreateTime();
        // 构建商品券
        CommodityCoupon commodityCoupon = new CommodityCoupon(
                couponId,
                command.getThresholdPrice(),
                command.getCounteractPrice(),
                creator,
                createTime,
                createTime,
                command.getStock(),
                command.getTargetType()
        );
        // 商品券商家信息
        Store store = new Store(storeId);
        store.setStoreName(storeInfo.getStoreName());
        commodityCoupon.setStore(store);
        // 商品券商品信息
        Commodity commodity = new Commodity(Long.parseLong(commodityInfo.getCommodityId()));
        commodity.setCommodityName(commodityInfo.getCommodityName());
        commodity.setCommodityPicSmall(command.getCommodityPicSmall());
        commodityCoupon.setCommodity(commodity);
        commodityCoupon.setTimeType(command.getTimeType());
        commodityCoupon.setTargetType(command.getTargetType());
        commodityCoupon.setValidDuration(command.getValidDuration());
        commodityCoupon.setStartTime(command.getStartTime());
        commodityCoupon.setDeadline(command.getDeadline());
        // 加入db
        commodityCouponRepository.addOne(commodityCoupon);
    }

}
