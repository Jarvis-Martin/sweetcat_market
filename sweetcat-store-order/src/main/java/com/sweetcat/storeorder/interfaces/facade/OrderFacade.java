package com.sweetcat.storeorder.interfaces.facade;

import com.sweetcat.storeorder.application.command.AddOrderCommand;
import com.sweetcat.storeorder.application.service.OrderApplicationService;
import com.sweetcat.storeorder.domain.order.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-15:17
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
     * 添加订单
     * @param command
     */
    public void addOne(AddOrderCommand command) {
        orderApplicationService.addOne(command);
    }

    /**
     * 查分页数据 by customer id
     * @param customerId
     * @param page
     * @param limit
     */
    public List<Order> findPageByCustomerId(Long customerId, Integer page, Integer limit) {
        return orderApplicationService.findPageByCustomerId(customerId, page, limit);
    }

    /**
     * 更具订单id 查订单信息
     * @param orderId
     * @return
     */
    public Order findOneByOrderId(Long orderId) {
        return orderApplicationService.findOneByOrderId(orderId);
    }

}
