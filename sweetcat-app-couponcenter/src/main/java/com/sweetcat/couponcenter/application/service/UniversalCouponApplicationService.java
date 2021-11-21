package com.sweetcat.couponcenter.application.service;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.couponcenter.application.command.AddUniversalCouponCommand;
import com.sweetcat.couponcenter.application.rpc.UserInfoRpc;
import com.sweetcat.couponcenter.domain.coupon.entity.UniversalCoupon;
import com.sweetcat.couponcenter.domain.coupon.repository.UniversalCouponRepository;
import com.sweetcat.couponcenter.domain.coupon.vo.Creator;
import com.sweetcat.couponcenter.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.couponcenter.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-21:51
 * @version: 1.0
 */
@Service
public class UniversalCouponApplicationService {
    private SnowFlakeService snowFlakeService;
    private UniversalCouponRepository universalCouponRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private UserInfoRpc userInfoRpc;

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setUniversalCouponRepository(UniversalCouponRepository universalCouponRepository) {
        this.universalCouponRepository = universalCouponRepository;
    }

    /**
     * 查询分页数据
     *
     * @param targetType
     * @param page
     * @param limit
     * @return
     */
    public List<UniversalCoupon> findPage(Integer targetType, Integer page, Integer limit) {
        // 调整 page,limit
        limit = limit == null || limit < 0 ? 15 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        return universalCouponRepository.findPage(targetType, page, limit);
    }

    /**
     * add a universal
     * @param command
     */
    public void addOne(AddUniversalCouponCommand command) {
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
        // 生成通用券id
        long couponId = snowFlakeService.snowflakeId();
        // 优惠券创建人
        Creator creator = new Creator(command.getCreatorId());
        // 优惠券创建时间
        Long createTime = command.getCreateTime();
        // 构造优惠券对象
        UniversalCoupon universalCoupon = new UniversalCoupon(
                couponId,
                command.getThresholdPrice(),
                command.getCounteractPrice(),
                creator,
                createTime,
                createTime,
                command.getStock()
        );
        universalCoupon.setTargetType(command.getTargetType());
        universalCoupon.setTimeType(command.getTimeType());
        universalCoupon.setValidDuration(command.getValidDuration());
        universalCoupon.setStartTime(command.getStartTime());
        universalCoupon.setDeadline(command.getDeadline());
        // 入库
        universalCouponRepository.addOne(universalCoupon);
    }
}
