package com.sweetcat.userorder.interfaces.rpc;

import com.sweetcat.api.rpcdto.userorder.UserOrderRpcDTO;
import com.sweetcat.userorder.domain.order.entity.ChildrenOrder;
import com.sweetcat.userorder.interfaces.facade.OrderFacade;
import com.sweetcat.userorder.interfaces.facade.assembler.OrderRpcAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/7-13:19
 * @version: 1.0
 */
@RestController
@RequestMapping("/rpc/user_order")
public class OrderRpcController {
    private OrderFacade orderFacade;
    private OrderRpcAssembler orderRpcAssembler;

    @Autowired
    public void setOrderRpcAssembler(OrderRpcAssembler orderRpcAssembler) {
        this.orderRpcAssembler = orderRpcAssembler;
    }

    @Autowired
    public void setOrderFacade(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @GetMapping("/order/{order_id}")
    public UserOrderRpcDTO findOneByUserIdAndOrderId(Long userId, @PathVariable("order_id") Long orderId) {
        ChildrenOrder childrenOrder = orderFacade.findOneByUserIdAndOrderId(userId, orderId);
        return orderRpcAssembler.converterToUserOrderRpcDTO(childrenOrder);
    }
}
