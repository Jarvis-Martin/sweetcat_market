package com.sweetcat.takewayorder.interfaces.facade.assembler;

import com.sweetcat.takewayorder.domain.order.entity.AmountInfoOfOrder;
import com.sweetcat.takewayorder.domain.order.entity.Order;
import com.sweetcat.takewayorder.domain.order.entity.StoreInfo;
import com.sweetcat.takewayorder.interfaces.facade.restdto.AmountInfoRestDTO;
import com.sweetcat.takewayorder.interfaces.facade.restdto.CommodityInfoRestDTO;
import com.sweetcat.takewayorder.interfaces.facade.restdto.OrderSummaryRestDTO;
import com.sweetcat.takewayorder.interfaces.facade.restdto.StoreRestDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-23:26
 * @version: 1.0
 */
@Component
public class OrderRestAssembler {

    public OrderSummaryRestDTO converterToOrderSummaryRestDTO(Order order) {
        OrderSummaryRestDTO orderSummaryRestDTO = new OrderSummaryRestDTO(order.getOrderId());
        ArrayList<CommodityInfoRestDTO> commodityInfoRestDTOS = order.getCommodityInfoList().stream().collect(
                ArrayList<CommodityInfoRestDTO>::new,
                (con, commodityInfo) -> {
                    CommodityInfoRestDTO commodityInfoRestDTO = new CommodityInfoRestDTO(commodityInfo.getCommodityId());
                    inflateCommodityInfoRestDTO(commodityInfo, commodityInfoRestDTO);
                    con.add(commodityInfoRestDTO);
                },
                ArrayList<CommodityInfoRestDTO>::addAll
        );
        orderSummaryRestDTO.setCommodityInfos(commodityInfoRestDTOS);
        orderSummaryRestDTO.setStatus(order.getOrderStatus());

        AmountInfoRestDTO amountInfoRestDTO = new AmountInfoRestDTO();
        AmountInfoOfOrder amountInfo = order.getAmountInfo();
        // 优惠金额 = 商品总价 - 商品支付金额
        amountInfoRestDTO.setDiscountPrice(amountInfo.getPriceOfCommodity().subtract(amountInfo.getPriceOfPayment()));
        amountInfoRestDTO.setPriceOfPayment(amountInfo.getPriceOfPayment());
        orderSummaryRestDTO.setAmountInfo(amountInfoRestDTO);

        // 未支付状态限时，下单时间
        if (Order.STATUS_UNPAY.equals(order.getOrderStatus())) {
            orderSummaryRestDTO.setTime(order.getTimeInfo().getPlaceOrderTime());
        }

        StoreInfo storeInfo = order.getStoreInfo();
        StoreRestDTO storeRestDTO = new StoreRestDTO(storeInfo.getStoreId());
        inflateStoreRestDTO(storeInfo, storeRestDTO);
        orderSummaryRestDTO.setStoreInfo(storeRestDTO);
        return orderSummaryRestDTO;
    }

    private void inflateCommodityInfoRestDTO(com.sweetcat.takewayorder.domain.order.entity.CommodityInfo commodityInfo, CommodityInfoRestDTO commodityInfoRestDTO) {
        commodityInfoRestDTO.setCommodityName(commodityInfo.getCommodityName());
        commodityInfoRestDTO.setCommodityPicSmall(commodityInfo.getCommodityPicSmall());
        commodityInfoRestDTO.setCurrentPrice(commodityInfo.getPriceWhenMakeOrder());
        commodityInfoRestDTO.setCount(commodityInfo.getCount());
    }

    private void inflateStoreRestDTO(StoreInfo storeInfo, StoreRestDTO storeRestDTO) {
        storeRestDTO.setStoreName(storeInfo.getStoreName());
        storeRestDTO.setStoreLogo(storeInfo.getStoreLogo());
    }
}
