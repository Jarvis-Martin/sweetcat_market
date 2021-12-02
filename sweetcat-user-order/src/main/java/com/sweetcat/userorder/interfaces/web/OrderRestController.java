package com.sweetcat.userorder.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.userorder.application.command.AddOrderCommand;
import com.sweetcat.userorder.domain.order.entity.ChildrenOrder;
import com.sweetcat.userorder.interfaces.facade.OrderFacade;
import com.sweetcat.userorder.interfaces.facade.assembler.OrderRestAssembler;
import com.sweetcat.userorder.interfaces.facade.restdto.OrderRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/2-20:06
 * @version: 1.0
 */
@RestController
@RequestMapping("/user_order")
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
     * 添加一条 order
     *
     * @param command
     */
    @PostMapping("/add")
    public ResponseDTO addOne(AddOrderCommand command) {
        orderFacade.addOne(command);
        return response("创建订单成功", "{}");
    }

    /**
     * 移除一条订单
     *
     * @param orderId
     */
    @DeleteMapping("{orderId}")
    public ResponseDTO removeOne(Long orderId) {
        orderFacade.removeOne(orderId);
        return response("删除订单成功", "{}");
    }

    @GetMapping("/orders")
    public ResponseDTO findPageByUserId(Long userId, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        List<ChildrenOrder> childrenCrderPage = orderFacade.findPageByUserId(userId, page, limit);
        if (childrenCrderPage == null) {
            return response("查询订单成功", "{}");
        }
        ArrayList<OrderRestDTO> orders = childrenCrderPage.stream().collect(
                ArrayList<OrderRestDTO>::new,
                (con, childrenOrder) -> {
                    con.add(orderRestAssembler.converterToOrderRestDTO(childrenOrder));
                },
                ArrayList<OrderRestDTO>::addAll
        );
        HashMap<String, Object> dataSection = new HashMap<>(2);
        dataSection.put("orders", orders);
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
