package com.sweetcat.userorder.interfaces.facade.assembler;

import com.sweetcat.userorder.domain.order.entity.AmountInfo;
import com.sweetcat.userorder.domain.order.entity.ChildrenOrder;
import com.sweetcat.userorder.domain.order.entity.Order;
import com.sweetcat.userorder.domain.order.entity.StoreInfo;
import com.sweetcat.userorder.interfaces.facade.restdto.AmountInfoRestDTO;
import com.sweetcat.userorder.interfaces.facade.restdto.CommodityInfoRestDTO;
import com.sweetcat.userorder.interfaces.facade.restdto.OrderRestDTO;
import com.sweetcat.userorder.interfaces.facade.restdto.StoreRestDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/2-20:14
 * @version: 1.0
 */
@Component
public class OrderRestAssembler {
    public OrderRestDTO converterToOrderRestDTO(ChildrenOrder order) {
        OrderRestDTO orderRestDTO = new OrderRestDTO();
        orderRestDTO.setOrderId(order.getChildrenOrderId());
        ArrayList<CommodityInfoRestDTO> commodityInfoRestDTOS = order.getCommodityInfoList().stream().collect(
                ArrayList<CommodityInfoRestDTO>::new,
                (con, commodityInfo) -> {
                    CommodityInfoRestDTO commodityInfoRestDTO = new CommodityInfoRestDTO(commodityInfo.getCommodityId());
                    commodityInfoRestDTO.setCommodityName(commodityInfo.getCommodityName());
                    commodityInfoRestDTO.setCommodityPicSmall(commodityInfo.getCommodityPicSmall());
                    commodityInfoRestDTO.setSpecification(commodityInfo.getSpecification());
                    commodityInfoRestDTO.setCurrentPrice(commodityInfo.getPriceWhenMakeOrder());
                    commodityInfoRestDTO.setCount(commodityInfo.getCount());
                    con.add(commodityInfoRestDTO);
                },
                ArrayList<CommodityInfoRestDTO>::addAll
        );
        Integer orderStatus = order.getOrderInfo().getOrderStatus();
        orderRestDTO.setCommodityInfos(commodityInfoRestDTOS);
        orderRestDTO.setStatus(orderStatus);
        AmountInfo amountInfoOfOrder = order.getAmountInfo();
        AmountInfoRestDTO amountInfoRestDTO = new AmountInfoRestDTO();
        BigDecimal priceOfPayment = amountInfoOfOrder.getPriceOfPayment();
        amountInfoRestDTO.setPriceOfPayment(priceOfPayment);
        // 订单优惠金额 = 商品总原价 - 订单支付总额
        amountInfoRestDTO.setDiscountPrice(amountInfoOfOrder.getPriceOfCommodity().subtract(priceOfPayment));
        orderRestDTO.setAmountInfo(amountInfoRestDTO);
        long time = 0L;
        if (Order.STATUS_UNPAY.equals(orderStatus)) {
            time = order.getTimeInfo().getPlaceOrderTime();
        }
        orderRestDTO.setTime(time);
        StoreInfo storeInfo = order.getCommodityInfoList().get(0).getStoreInfo();
        StoreRestDTO storeRestDTO = new StoreRestDTO(storeInfo.getStoreId());
        storeRestDTO.setStoreName(storeInfo.getStoreName());
        storeRestDTO.setStoreLogo(storeInfo.getStoreLogo());
        orderRestDTO.setStoreInfo(storeRestDTO);

        return orderRestDTO;
    }
}
