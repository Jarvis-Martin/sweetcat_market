package com.sweetcat.usercoupon.application.event.subscribe;

import com.sweetcat.commons.domainevent.couponcenter.UserAcquiredCommodityCouponEvent;
import com.sweetcat.usercoupon.application.command.AddCommodityCouponCommand;
import com.sweetcat.usercoupon.application.service.UserCouponApplicationService;
import com.sweetcat.usercoupon.domain.usercoupon.vo.CouponTargetType;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/22-14:49
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "credit_center_topic",
        selectorType = SelectorType.TAG,
        selectorExpression = "user_acquire_commodity_coupon",
        consumerGroup = "CG_user_coupon_acquire_commodity_coupon",
        consumeMode = ConsumeMode.ORDERLY
)
public class UserAcquiredCommodityCouponEventSubscriber implements RocketMQListener<UserAcquiredCommodityCouponEvent> {
    private UserCouponApplicationService userCouponApplicationService;

    @Autowired
    public void setUserCouponApplicationService(UserCouponApplicationService userCouponApplicationService) {
        this.userCouponApplicationService = userCouponApplicationService;
    }

    @Override
    public void onMessage(UserAcquiredCommodityCouponEvent event) {
        System.out.println("sweetcat-user-info 触发领域事件 UserAcquiredCommodityCouponEvent 时间为：" + Instant.now().toEpochMilli());
        // 一张优惠券 userCoupon
        if (CouponTargetType.TARGETTYPE_COMMODITY.equals(event.getTargetType())) {
            AddCommodityCouponCommand addCommodityCouponCommand = new AddCommodityCouponCommand();
            inflateAddCommodityCouponCommand(event, addCommodityCouponCommand);
            userCouponApplicationService.addOneCommodityCoupon(addCommodityCouponCommand);
        }
    }

    /**
     * 填充 addCommodityCouponCommand 对象
     * @param event
     * @param addCommodityCouponCommand
     */
    private void inflateAddCommodityCouponCommand(UserAcquiredCommodityCouponEvent event, AddCommodityCouponCommand addCommodityCouponCommand) {
        addCommodityCouponCommand.setUserId(event.getUserId());
        addCommodityCouponCommand.setCouponId(event.getCouponId());
        addCommodityCouponCommand.setThresholdPrice(event.getThresholdPrice());
        addCommodityCouponCommand.setCounteractPrice(event.getCounteractPrice());
        addCommodityCouponCommand.setObtainTime(event.getObtainTime());
        addCommodityCouponCommand.setTargetType(event.getTargetType());
        addCommodityCouponCommand.setStoreId(event.getStoreId());
        addCommodityCouponCommand.setStoreName(event.getStoreName());
        addCommodityCouponCommand.setCommodityId(event.getCommodityId());
        addCommodityCouponCommand.setCommodityName(event.getCommodityName());
        addCommodityCouponCommand.setCommodityPicSmall(event.getCommodityPicSmall());
        addCommodityCouponCommand.setTimeType(event.getTimeType());
        addCommodityCouponCommand.setValidDuration(event.getValidDuration());
        addCommodityCouponCommand.setStartTime(event.getStartTime());
        addCommodityCouponCommand.setDeadline(event.getDeadline());
    }
}
