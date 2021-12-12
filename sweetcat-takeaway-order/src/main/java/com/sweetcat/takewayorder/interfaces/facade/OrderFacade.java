package com.sweetcat.takewayorder.interfaces.facade;

import com.sweetcat.takewayorder.application.command.addordercommand.AddOrderCommand;
import com.sweetcat.takewayorder.application.service.OrderApplicationService;
import com.sweetcat.takewayorder.domain.order.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-23:19
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
     * 添加一条记录
     * @param command
     */
    public void addOne(AddOrderCommand command) {
        orderApplicationService.addOne(command);
    }

    /**
     * find order by orderId
     * @param orderId
     * @return
     */
    public Order findOneByOrderId(Long orderId) {
        return orderApplicationService.findOneByOrderId(orderId);
    }

    public List<Order> findPage(Integer page, Integer limit) {
        return orderApplicationService.findPage(page, limit);
    }
}
