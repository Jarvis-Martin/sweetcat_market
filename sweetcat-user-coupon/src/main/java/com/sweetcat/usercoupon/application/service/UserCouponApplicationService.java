package com.sweetcat.usercoupon.application.service;

import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.StoreNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.usercoupon.application.command.AddCommodityCouponCommand;
import com.sweetcat.usercoupon.application.command.AddUniversalCouponCommand;
import com.sweetcat.usercoupon.application.rpc.CommodityInfoRpc;
import com.sweetcat.usercoupon.application.rpc.StoreInfoRpc;
import com.sweetcat.usercoupon.application.rpc.UserInfoRpc;
import com.sweetcat.usercoupon.domain.usercoupon.entity.CommodityCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UniversalCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.entity.User;
import com.sweetcat.usercoupon.domain.usercoupon.entity.UserCoupon;
import com.sweetcat.usercoupon.domain.usercoupon.repository.CouponInfoRepository;
import com.sweetcat.usercoupon.domain.usercoupon.repository.UserCouponRepository;
import com.sweetcat.usercoupon.domain.usercoupon.vo.Commodity;
import com.sweetcat.usercoupon.domain.usercoupon.vo.Store;
import com.sweetcat.usercoupon.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.usercoupon.infrastructure.service.snowflake_service.SnowFlakeService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;
    private UserCouponRepository userCouponRepository;
    private CouponInfoRepository couponInfoRepository;

    @Autowired
    public void setCouponRepository(CouponInfoRepository couponInfoRepository) {
        this.couponInfoRepository = couponInfoRepository;
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
    @Transactional
    public List<UserCoupon> findPageByUserId(Long userId, Integer page, Integer limit) {
        // ??????userid
        verifyIdFormatService.verifyIds(userId);
        // ????????????
        checkUser(userId);
        // ?????? page limit
        limit = limit == null || limit <= 0 ? 15 : limit;
        page = page == null || page <= 0 ? 0 : page * limit;
        return userCouponRepository.findPageByUserId(userId, page, limit);
    }

    /**
     * ??????
     * @param userId
     * @param couponId
     */
    @Transactional
    public void remove(Long userId, Long couponId) {
        // ?????? id
        verifyIdFormatService.verifyIds(userId, couponId);
        // ????????????
        checkUser(userId);
        // ??? db
        UserCoupon userCoupon = userCouponRepository.findOneByUserIdAndCouponId(userId, couponId);
        // ??????
        userCouponRepository.remove(userCoupon);
    }

    /**
     * ??????????????????????????? ??????????????????????????????
     * @param command
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOneCommodityCoupon(AddCommodityCouponCommand command) {
        Long userId = command.getUserId();
        Long couponId = command.getCouponId();
        Long storeId = command.getStoreId();
        Long commodityId = command.getCommodityId();
        // ??????id
        verifyIdFormatService.verifyIds(userId, couponId, storeId, commodityId);
        // ????????????
        checkUser(userId);
        // ????????????
        checkStore(storeId);
        // ????????????id
        long recordId = snowFlakeService.snowflakeId();
        // ?????? userCoupon
        UserCoupon userCoupon = new UserCoupon(recordId);
        // ?????? user of usercoupon
        User user = new User(userId);
        // ?????? commodityCoupon of user Coupon
        CommodityCoupon commodityCoupon = new CommodityCoupon(
                couponId,
                command.getThresholdPrice(),
                command.getCounteractPrice()
        );
        // ?????? commodityCoupon
        inflateCommodityCoupon(command, storeId, commodityId, commodityCoupon);
        // ?????? userCoupon
        inflateUserCoupon(command, userCoupon, user, commodityCoupon);
        // userCoupon ??????
        userCouponRepository.addOne(userCoupon);
        // userCoupon ????????? coupon ??????
        couponInfoRepository.addOne(commodityCoupon);
    }

    private void inflateCommodityCoupon(AddCommodityCouponCommand command, Long storeId, Long commodityId, CommodityCoupon commodityCoupon) {
        // ?????? ?????????????????????
        Store store = new Store(storeId);
        store.setStoreName(command.getStoreName());
        // ?????? ????????????????????????
        Commodity commodity = new Commodity(commodityId);
        commodity.setCommodityName(command.getCommodityName());
        commodity.setCommodityPicSmall(command.getCommodityPicSmall());
        // -- ??????
        commodityCoupon.setObtainTime(command.getObtainTime());
        commodityCoupon.setStore(store);
        commodityCoupon.setCommodity(commodity);
        // ????????????????????????????????? 0????????????1?????????
        commodityCoupon.setTargetType(command.getTargetType());
        // ??????????????? ????????????
        commodityCoupon.setTimeType(command.getTimeType());
        // ????????? ????????????
        commodityCoupon.setValidDuration(command.getValidDuration());
        // ????????? ?????????????????? ????????????
        commodityCoupon.setStartTime(command.getStartTime());
        // ????????? ?????????????????? ????????????
        commodityCoupon.setDeadline(command.getDeadline());
    }

    private void inflateUserCoupon(AddCommodityCouponCommand command, UserCoupon userCoupon, User user, CommodityCoupon commodityCoupon) {
        // set ???????????? of usercoupon
        userCoupon.setObtainTime(command.getObtainTime());
        userCoupon.setUser(user);
        userCoupon.setCoupon(commodityCoupon);
        userCoupon.setTargetType(command.getTargetType());
    }

    /**
     * ??????????????????????????? ??????????????????????????????
     * @param command
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void addOneUniversalCoupon(AddUniversalCouponCommand command) {
        Long userId = command.getUserId();
        Long couponId = command.getCouponId();
        // ??????id
        verifyIdFormatService.verifyIds(userId, couponId);
        // ????????????
        checkUser(userId);
        // ??????id
        long recordId = snowFlakeService.snowflakeId();
        // ?????? userCoupon
        UserCoupon userCoupon = new UserCoupon(recordId);
        // ??????usercoupon???u????????????
        userCoupon.setObtainTime(command.getObtainTime());
        // ??????usercoupon???user
        User user = new User(userId);
        userCoupon.setUser(user);
        // ??????usercoupon??? universal coupon
        UniversalCoupon universalCoupon = new UniversalCoupon(
                couponId,
                command.getThresholdPrice(),
                command.getCounteractPrice()
        );
        // ????????????????????????????????? 0????????????1?????????
        universalCoupon.setTargetType(command.getTargetType());
        // ??????????????? ????????????
        universalCoupon.setTimeType(command.getTimeType());
        // ????????? ????????????
        universalCoupon.setValidDuration(command.getValidDuration());
        // ????????? ?????????????????? ????????????
        universalCoupon.setStartTime(command.getStartTime());
        // ????????? ?????????????????? ????????????
        universalCoupon.setDeadline(command.getDeadline());
        userCoupon.setCoupon(universalCoupon);
        // ??????
        userCouponRepository.addOne(userCoupon);
    }

    /**
     * ???????????????????????????????????????????????????
     * @param userId
     */
    private void checkUser(Long userId) {
        // ????????????
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(userId);
        // ???????????????
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * ???????????????????????????????????????????????????
     * @param storeId
     */
    private void checkStore(Long storeId) {
        // ????????????
        StoreInfoRpcDTO storeInfo = storeInfoRpc.findOneByStoreId(storeId);
        // ???????????????
        if (storeInfo == null) {
            throw new StoreNotExistedException(
                    ResponseStatusEnum.STORENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.STORENOTEXISTED.getErrorMessage()
            );
        }
    }

    @Transactional
    public UserCoupon findfindOneByCouponId(Long userId, Long couponId) {
        verifyIdFormatService.verifyIds(couponId);
        return userCouponRepository.findOneByUserIdAndCouponId(userId, couponId);
    }
}
