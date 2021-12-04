package com.sweetcat.userorder.interfaces.facade;

import com.sweetcat.userorder.application.command.AddOrderCommand;
import com.sweetcat.userorder.application.service.OrderApplicationService;
import com.sweetcat.userorder.domain.order.entity.ChildrenOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/2-20:02
 * @version: 1.0
 */
@Component
public class OrderFacade {
    private OrderApplicationService orderApplicationService;

    @Autowired
    public void setOrderApplicationService(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    /**
     * 添加一条 order
     *
     * @param command
     */
    public void addOne(AddOrderCommand command) {
        orderApplicationService.addOne(command);
    }

    /**
     * 移除一条订单
     *
     * @param orderId
     */
    public void removeOne(Long userId, Long orderId) {
        orderApplicationService.removeOne(userId, orderId);
    }

    public List<ChildrenOrder> findPageByUserId(Long userId, Integer page, Integer limit) {
        return orderApplicationService.findPageByUserId(userId, page, limit);
    }

    public void cancelOrder(Long userId, Long orderId, Long cancelTime) {
        orderApplicationService.cancelOrder(userId, orderId, cancelTime);
    }

    public void changeAddress(Long userId, Long orderId, Long addressId) {
        orderApplicationService.changeAddress(userId, orderId, addressId);
    }
}
