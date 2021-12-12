package com.sweetcat.takewayorder.application.command.addordercommand;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-23:13
 * @version: 1.0
 */
@Data
public class AddOrderCommand {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单所属用户编号
     */
    private Long userId;

    private Long storeId;

    private TimeInfoOfAddOrderCommand timeInfoOfAddOrderCommand;

    private UserAddress userAddress;

    private StoreAddress storeAddress;

    /**
     * 订单实际支付金额
     */
    private BigDecimal priceOfPayment;
    /**
     * 订单商品总价
     */
    private BigDecimal priceOfCommodity;

    private List<Commodity> commodities;
}
