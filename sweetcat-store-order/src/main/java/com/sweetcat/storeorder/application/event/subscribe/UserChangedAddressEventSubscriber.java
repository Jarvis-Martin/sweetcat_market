package com.sweetcat.storeorder.application.event.subscribe;

import com.sweetcat.commons.domainevent.userinfo.UserChangedAddressEvent;
import com.sweetcat.storeorder.application.command.changecustomeraddresscommand.ChangeCustomerAddressCommand;
import com.sweetcat.storeorder.application.service.OrderApplicationService;
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
 * @date: 2021-12-2021/12/11-10:17
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "sweetcat-user-order",
        selectorType = SelectorType.TAG,
        selectorExpression = "user_changed_address",
        consumerGroup = "CG_SWEETCAT_STORE_ORDER_USER_CHANGED_ADDRESS",
        consumeMode = ConsumeMode.ORDERLY
)
public class UserChangedAddressEventSubscriber implements RocketMQListener<UserChangedAddressEvent> {
    private OrderApplicationService orderApplicationService;

    @Autowired
    public void setOrderApplicationService(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @Override
    public void onMessage(UserChangedAddressEvent event) {
        System.out.println("sweetcat-store-order 接收领域事件 UserChangedAddressEvent 时间为：" + Instant.now().toEpochMilli());
        Long userId = event.getUserId();
        long addressId = event.getAddressId();
        ChangeCustomerAddressCommand command = new ChangeCustomerAddressCommand(userId, addressId);
        inflateChangeCustomerAddressCommand(event, command);
        orderApplicationService.changeUserAddress(command);
    }

    private void inflateChangeCustomerAddressCommand(UserChangedAddressEvent event, ChangeCustomerAddressCommand command) {
        command.setReceiverName(event.getReceiverName());
        command.setReceiverPhone(event.getReceiverPhone());
        command.setProvinceName(event.getProvinceName());
        command.setCityName(event.getCityName());
        command.setAreaName(event.getAreaName());
        command.setTownName(event.getTownName());
        command.setDetailAddress(event.getDetailAddress());
    }

}
