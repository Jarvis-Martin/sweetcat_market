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
    public void removeOne(Long orderId) {
        orderApplicationService.removeOne(orderId);
    }

    public List<ChildrenOrder> findPageByUserId(Long userId, Integer page, Integer limit) {
        return orderApplicationService.findPageByUserId(userId, page, limit);
    }
}
