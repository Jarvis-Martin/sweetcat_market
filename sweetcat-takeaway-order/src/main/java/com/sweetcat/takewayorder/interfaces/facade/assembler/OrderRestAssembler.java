package com.sweetcat.takewayorder.interfaces.facade.assembler;

import com.sweetcat.takewayorder.domain.order.entity.Order;
import com.sweetcat.takewayorder.interfaces.facade.restdto.OrderSummaryRestDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-23:26
 * @version: 1.0
 */
@Component
public class OrderRestAssembler {
    public OrderSummaryRestDTO converterToOrderSummaryRestDTO(Order order) {
        OrderSummaryRestDTO orderSummaryRestDTO = new OrderSummaryRestDTO();

        return orderSummaryRestDTO;
    }
}
