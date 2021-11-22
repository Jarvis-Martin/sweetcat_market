package com.sweetcat.usercoupon.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.CommodityNotExistedException;
import com.sweetcat.commons.exception.StoreNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.usercoupon.application.command.AddCommodityCouponCommand;
import com.sweetcat.usercoupon.application.command.AddUniversalCouponCommand;
import com.sweetcat.usercoupon.application.rpc.CommodityInfoRpc;
import com.sweetcat.usercoupon.application.rpc.CouponRpc;
import com.sweetcat.usercoupon.application.rpc.StoreInfoRpc;
import com.sweetcat.usercoupon.application.rpc.UserInfoRpc;
import com.sweetcat.usercoupon.domain.usercoupon.entity.CommodityCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UniversalCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.User;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UserCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.repository.UserCouponRepository;
import com.sweetcat.usercoupon.domain.usercoupon.vo.Commodity;
import com.sweetcat.usercoupon.domain.usercoupon.vo.Store;
import com.sweetcat.usercoupon.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.usercoupon.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/21-22:33
 * @version: 1.0
 */
@Service
public class UserCouponApplicationService {
    private UserInfoRpc userInfoRpc;
    private StoreInfoRpc storeInfoRpc;
    private CommodityInfoRpc commodityInfoRpc;
    private CouponRpc couponRpc;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private UserCouponRepository userCouponRepository;

    @Autowired
    public void setCouponRpc(CouponRpc couponRpc) {
        this.couponRpc = couponRpc;
    }

    @Autowired
    public void setStoreInfoRpc(StoreInfoRpc storeInfoRpc) {
        this.storeInfoRpc = storeInfoRpc;
    }

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
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
    public void setUserCouponRepository(UserCouponRepository userCouponRepository) {
        this.userCouponRepository = userCouponRepository;
    }

    /**
     * find userCoupon page by userId
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    public List<UserCoupon> findPageByUserId(Long userId, Integer page, Integer limit) {
        // 检查userid
        verifyIdFormatService.verifyIds(userId);
        // 检查用户
        checkUser(userId);
        // 调整 page limit
        limit = limit == null || limit <= 0 ? 15 : limit;
        page = page == null || page <= 0 ? 0 : page * limit;
        return userCouponRepository.findPageByUserId(userId, page, limit);
    }

    /**
     * 移除
     * @param userId
     * @param couponId
     */
    public void remove(Long userId, Long couponId) {
        // 检查 id
        verifyIdFormatService.verifyIds(userId, couponId);
        // 检查用户
        checkUser(userId);
        // 查 db
        UserCoupon userCoupon = userCouponRepository.findOneByUserIdAndCouponId(userId, couponId);
        // 移除
        userCouponRepository.remove(userCoupon);
    }

    /**
     * 添加。该操作一般由 用户领取优惠券而触发
     * @param command
     */
    public void addOneCommodityCoupon(AddCommodityCouponCommand command) {
        Long userId = command.getUserId();
        Long couponId = command.getCouponId();
        Long storeId = command.getStoreId();
        Long commodityId = command.getCommodityId();
        // 检查id
        verifyIdFormatService.verifyIds(userId, couponId, storeId, commodityId);
        // 检查用户
        checkUser(userId);
        // 检查商家
        checkStore(storeId);
        // 生成记录id
        long recordId = snowFlakeService.snowflakeId();
        // 构建 userCoupon
        UserCoupon userCoupon = new UserCoupon(recordId);
        // set 获得时间 of usercoupon
        userCoupon.setObtainTime(command.getObtainTime());
        // set user of usercoupon
        User user = new User(userId);
        userCoupon.setUser(user);
        // 构建 commodityCoupon of user Coupon
        CommodityCoupon commodityCoupon = new CommodityCoupon(
                couponId,
                command.getThresholdPrice(),
                command.getCounteractPrice()
        );
        // 构建、填充 商品券商家部分
        Store store = new Store(storeId);
        store.setStoreName(command.getStoreName());
        commodityCoupon.setStore(store);
        // 构建、填充 商品券的商品部分
        Commodity commodity = new Commodity(commodityId);
        commodity.setCommodityName(command.getCommodityName());
        commodity.setCommodityPicSmall(command.getCommodityPicSmall());
        commodityCoupon.setCommodity(commodity);
        // 商品券设置目标商品类型 0商品券；1通用券
        commodityCoupon.setTargetType(command.getTargetType());
        // 商品券设置 时间类型
        commodityCoupon.setTimeType(command.getTimeType());
        // 商品券 有效时长
        commodityCoupon.setValidDuration(command.getValidDuration());
        // 商品券 时间区间型的 开始时间
        commodityCoupon.setStartTime(command.getStartTime());
        // 商品券 时间区间型的 结束时间
        commodityCoupon.setDeadline(command.getDeadline());
        userCoupon.setCoupon(commodityCoupon);

        userCoupon.setTargetType(command.getTargetType());
        // 入库
        userCouponRepository.addOne(userCoupon);
    }

    /**
     * 添加。该操作一般由 用户领取优惠券而触发
     * @param command
     */
    public void addOneUniversalCoupon(AddUniversalCouponCommand command) {
        Long userId = command.getUserId();
        Long couponId = command.getCouponId();
        // 检查id
        verifyIdFormatService.verifyIds(userId, couponId);
        // 检查用户
        checkUser(userId);
        // 生成id
        long recordId = snowFlakeService.snowflakeId();
        // 构建 userCoupon
        UserCoupon userCoupon = new UserCoupon(recordId);
        // 设置usercoupon的u获得时间
        userCoupon.setObtainTime(command.getObtainTime());
        // 设置usercoupon的user
        User user = new User(userId);
        userCoupon.setUser(user);
        // 设置usercoupon的 universal coupon
        UniversalCoupon universalCoupon = new UniversalCoupon(
                couponId,
                command.getThresholdPrice(),
                command.getCounteractPrice()
        );
        // 商品券设置目标商品类型 0商品券；1通用券
        universalCoupon.setTargetType(command.getTargetType());
        // 商品券设置 时间类型
        universalCoupon.setTimeType(command.getTimeType());
        // 商品券 有效时长
        universalCoupon.setValidDuration(command.getValidDuration());
        // 商品券 时间区间型的 开始时间
        universalCoupon.setStartTime(command.getStartTime());
        // 商品券 时间区间型的 结束时间
        universalCoupon.setDeadline(command.getDeadline());
        userCoupon.setCoupon(universalCoupon);
        // 入库
        userCouponRepository.addOne(userCoupon);
    }

    /**
     * 检查用户是否存在，不存在则直接返回
     * @param userId
     */
    private void checkUser(Long userId) {
        // 检查用户
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * 检查店铺是否存在，不存在则直接返回
     * @param storeId
     */
    private void checkStore(Long storeId) {
        // 检查店铺
        StoreInfoRpcDTO storeInfo = storeInfoRpc.findOneByStoreId(storeId);
        // 店铺不存在
        if (storeInfo == null) {
            throw new StoreNotExistedException(
                    ResponseStatusEnum.STORENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.STORENOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * 检查商品是否存在，不存在则直接返回
     * @param commodityId
     */
    private void checkCommodity(Long commodityId) {
        // 检查商品
        CommodityInfoRpcDTO commodityInfo = commodityInfoRpc.findByCommodityId(commodityId);
        // 检查不存在
        if (commodityInfo == null) {
            throw new CommodityNotExistedException(
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.COMMODITYNOTEXISTED.getErrorMessage()
            );
        }
    }

}
