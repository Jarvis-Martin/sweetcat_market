package com.sweetcat.storeorder.interfaces.rpc;

import com.sweetcat.storeorder.application.command.AddOrderCommand;
import com.sweetcat.storeorder.domain.order.entity.Order;
import com.sweetcat.storeorder.interfaces.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-16:59
 * @version: 1.0
 */
@RestController
@RequestMapping("/rpc/store_order")
public class OrderRpcController {
    private OrderFacade orderFacade;

    @Autowired
    public void setOrderFacade(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    /**
     * 添加订单
     *
     * @param command
     */
    @PostMapping("")
    public void addOne(AddOrderCommand command) {
        orderFacade.addOne(command);
    }

    /**
     * 查分页数据 by customer id
     *
     * @param customerId
     * @param page
     * @param limit
     */
    @GetMapping(value = "/orders", params = {"customerId", "_page", "_limit"})
    public List<Order> findPageByCustomerId(Long customerId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        return orderFacade.findPageByCustomerId(customerId, page, limit);
    }

    /**
     * 更具订单id 查订单信息
     *
     * @param orderId
     * @return
     */
    @GetMapping(value = "/order/{order_id}", params = {"orderId"})
    public Order findOneByOrderId(@PathVariable("order_id") Long orderId) {
        return orderFacade.findOneByOrderId(orderId);
    }

}
