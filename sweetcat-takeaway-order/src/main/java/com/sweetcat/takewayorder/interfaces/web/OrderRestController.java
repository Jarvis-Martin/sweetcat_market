package com.sweetcat.takewayorder.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.takewayorder.application.command.AddOrderCommand;
import com.sweetcat.takewayorder.domain.order.entity.Order;
import com.sweetcat.takewayorder.interfaces.facade.OrderFacade;
import com.sweetcat.takewayorder.interfaces.facade.assembler.OrderRestAssembler;
import com.sweetcat.takewayorder.interfaces.facade.restdto.OrderSummaryRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-23:21
 * @version: 1.0
 */
@RestController
@RequestMapping("/takeaway_order")
public class OrderRestController {
    private OrderFacade orderFacade;
    private OrderRestAssembler orderRestAssembler;

    @Autowired
    public void setOrderRestAssembler(OrderRestAssembler orderRestAssembler) {
        this.orderRestAssembler = orderRestAssembler;
    }

    @Autowired
    public void setOrderFacade(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    /**
     * 添加一条记录
     * @param command
     */
    @PostMapping("/order")
    public ResponseDTO addOne(AddOrderCommand command) {
        orderFacade.addOne(command);
        return null;
    }

    /**
     * find order by orderId
     * @param orderId
     * @return
     */
    @GetMapping("/{order_id}")
    public ResponseDTO findOneByOrderId(@PathVariable("order_id") Long orderId) {
        Order order = orderFacade.findOneByOrderId(orderId);

        HashMap<String, Object> dataSection = new HashMap<>(2);
        OrderSummaryRestDTO orderSummaryRestDTO = orderRestAssembler.converterToOrderSummaryRestDTO(order);
        dataSection.put("order", orderSummaryRestDTO);
        return response("查询成功", dataSection);
    }

    /**
     * find order by orderId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/orders")
    public ResponseDTO findPage(Integer page, Integer limit) {
        List<Order> orders = orderFacade.findPage(page, limit);

        ArrayList<OrderSummaryRestDTO> orderSummaryRestDTOS = orders.stream().collect(
                ArrayList<OrderSummaryRestDTO>::new,
                (con, order) -> {
                    OrderSummaryRestDTO orderSummaryRestDTO = orderRestAssembler.converterToOrderSummaryRestDTO(order);
                    con.add(orderSummaryRestDTO);
                },
                ArrayList<OrderSummaryRestDTO>::addAll
        );

        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("order", orderSummaryRestDTOS);
        return response("查询成功", dataSection);
    }

    /**
     * 通用的放回 ResponseDTO
     *
     * @param tip  用户提示信息
     * @param data 数据部分
     * @return ResponseDTO
     */
    private ResponseDTO response(String tip, Object data) {
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                tip,
                data
        );
    }
}
