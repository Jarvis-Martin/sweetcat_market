package com.sweetcat.userorder.interfaces.facade.assembler;

import com.sweetcat.api.rpcdto.storeinfo.StoreAddressInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.userorder.*;
import com.sweetcat.userorder.application.rpc.StoreInfoRpc;
import com.sweetcat.userorder.domain.order.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/7-13:32
 * @version: 1.0
 */
@Component
public class OrderRpcAssembler {
    private StoreInfoRpc storeInfoRpc;

    @Autowired
    public void setStoreInfoRpc(StoreInfoRpc storeInfoRpc) {
        this.storeInfoRpc = storeInfoRpc;
    }

    public UserOrderRpcDTO converterToUserOrderRpcDTO(ChildrenOrder order) {
        Long orderId = order.getOrderId();
        Long userId = order.getUserId();
        UserOrderRpcDTO orderRpcDTO = new UserOrderRpcDTO();
        orderRpcDTO.setOrderId(order.getChildrenOrderId());
        orderRpcDTO.setOrderStatus(order.getOrderStatus());

        ArrayList<CommodityInfoRpcDTO> commodityInfoRpcDTOS = order.getCommodityInfoList().stream().collect(
                ArrayList<CommodityInfoRpcDTO>::new,
                (con, commodityInfo) -> {
                    Long commodityId = commodityInfo.getCommodityId();
                    CommodityInfoRpcDTO commodityInfoRpcDTO = new CommodityInfoRpcDTO(orderId, commodityId);
                    commodityInfoRpcDTO.setCommodityName(commodityInfo.getCommodityName());
                    commodityInfoRpcDTO.setCommodityPicSmall(commodityInfo.getCommodityPicSmall());
                    commodityInfoRpcDTO.setPriceWhenMakeOrder(commodityInfo.getPriceWhenMakeOrder());
                    commodityInfoRpcDTO.setSpecification(commodityInfo.getSpecification());
                    commodityInfoRpcDTO.setCount(commodityInfo.getCount());
                    StoreInfo storeInfo = commodityInfo.getStoreInfo();
                    Long storeId = storeInfo.getStoreId();
                    StoreRpcDTO storeRpcDTOOfCommodity = new StoreRpcDTO(orderId, storeId);
                    storeRpcDTOOfCommodity.setStoreName(storeInfo.getStoreName());
                    storeRpcDTOOfCommodity.setStoreLogo(storeInfo.getStoreLogo());
                    StoreAddressInfoRpcDTO storeAddressInfoRpcDTO = storeInfoRpc.findOneStoreAddressByStoreId(storeId);
                    StoreAddressRpcDTO storeAddressRpcDTO = new StoreAddressRpcDTO(storeId, storeAddressInfoRpcDTO.getAddressId());
                    iniflateStoreAddressRpcDTO(storeAddressInfoRpcDTO, storeAddressRpcDTO);
                    storeRpcDTOOfCommodity.setAddress(storeAddressRpcDTO);
                    commodityInfoRpcDTO.setStoreRpcDTO(storeRpcDTOOfCommodity);

                    AmountInfoOfCommodity amountInfoOfCommodity = commodityInfo.getAmountInfo();
                    AmountInfoOfCommodityRpcDTO amountInfoOfCommodityRpcDTO = new AmountInfoOfCommodityRpcDTO(orderId, commodityId);
                    amountInfoOfCommodityRpcDTO.setPriceOfCommodity(amountInfoOfCommodity.getPriceOfCommodity());
                    amountInfoOfCommodityRpcDTO.setPriceOfPayment(amountInfoOfCommodity.getPriceOfPayment());
                    DiscountPriceInfoRpcDTO discountPriceInfoRpcDTO = new DiscountPriceInfoRpcDTO();
                    DiscountPriceInfo discountPriceInfo = amountInfoOfCommodity.getDiscountPriceInfo();
                    discountPriceInfoRpcDTO.setCredit(discountPriceInfo.getCredit());
                    ArrayList<CouponRpcDTO> couponRpcDTOS = discountPriceInfo.getCoupons().stream().collect(
                            ArrayList<CouponRpcDTO>::new,
                            (con1, coupon) -> {
                                CouponRpcDTO couponRpcDTO = new CouponRpcDTO(orderId, coupon.getCouponId());
                                couponRpcDTO.setThresholdPrice(coupon.getThresholdPrice());
                                couponRpcDTO.setCounteractPrice(coupon.getCounteractPrice());
                                con1.add(couponRpcDTO);
                            },
                            ArrayList<CouponRpcDTO>::addAll
                    );
                    discountPriceInfoRpcDTO.setCouponRpcDTOS(couponRpcDTOS);
                    amountInfoOfCommodityRpcDTO.setDiscountPriceInfo(discountPriceInfoRpcDTO);
                    commodityInfoRpcDTO.setAmountInfo(amountInfoOfCommodityRpcDTO);
                    con.add(commodityInfoRpcDTO);
                },
                ArrayList<CommodityInfoRpcDTO>::addAll
        );
        orderRpcDTO.setCommodityInfoRpcDTOList(commodityInfoRpcDTOS);

        TimeInfoRpcDTO timeInfoRpcDTO = new TimeInfoRpcDTO(orderId);
        TimeInfo timeInfo = order.getTimeInfo();
        inflateTimeRpcDTO(timeInfoRpcDTO, timeInfo);
        orderRpcDTO.setTimeInfoRpcDTO(timeInfoRpcDTO);

        AddressInfo addressInfo = order.getUserInfo().getAddressInfo();
        UserInfoRpcDTO userInfoRpcDTO = new UserInfoRpcDTO(userId);
        AddressInfoRpcDTO addressInfoRpcDTO = new AddressInfoRpcDTO(orderId, userId, addressInfo.getAddressId());
        inflateAddressInfoRpcDTO(addressInfo, addressInfoRpcDTO);
        userInfoRpcDTO.setAddressInfoRpcDTO(addressInfoRpcDTO);
        orderRpcDTO.setUserInfoRpcDTO(userInfoRpcDTO);

        // 子订单已按商家拆分，故任何一件商品的商家都是一样的
        Long storeId = order.getCommodityInfoList().get(0).getStoreInfo().getStoreId();
        // 来自 sweetcat-store-info 的 store-info
        StoreInfoRpcDTO storeInfoFromStoreMicroService = storeInfoRpc.findOneByStoreId(storeId);
        // 来自 sweetcat-store-info 的 store-address
        StoreAddressInfoRpcDTO storeAddressFromMicroServer = storeInfoRpc.findOneStoreAddressByStoreId(storeId);
        StoreRpcDTO storeRpcDTO = new StoreRpcDTO(orderId, storeId);
        storeRpcDTO.setStoreName(storeInfoFromStoreMicroService.getStoreName());
        storeRpcDTO.setStoreLogo(storeInfoFromStoreMicroService.getStoreLogo());
        StoreAddressRpcDTO storeAddressRpcDTO = new StoreAddressRpcDTO(storeId, addressInfoRpcDTO.getAddressId());
        inflateStoreAddressRpcDTO(storeAddressFromMicroServer, storeAddressRpcDTO);
        storeRpcDTO.setAddress(storeAddressRpcDTO);
        orderRpcDTO.setStoreRpcDTO(storeRpcDTO);

        AmountInfo amountInfo = order.getAmountInfo();
        AmountInfoRpcDTO amountInfoRpcDTO = new AmountInfoRpcDTO(orderId);
        inflateAmountInfoRpcDTO(amountInfo, amountInfoRpcDTO);
        ArrayList<CouponRpcDTO> couponRpcDTOS = amountInfo.getCoupons().stream().collect(
                ArrayList<CouponRpcDTO>::new,
                (con, coupon) -> {
                    CouponRpcDTO couponRpcDTO = new CouponRpcDTO(orderId, coupon.getCouponId());
                    couponRpcDTO.setThresholdPrice(coupon.getThresholdPrice());
                    couponRpcDTO.setCounteractPrice(coupon.getCounteractPrice());
                    con.add(couponRpcDTO);
                },
                ArrayList<CouponRpcDTO>::addAll
        );
        amountInfoRpcDTO.setCouponRpcDTOS(couponRpcDTOS);
        orderRpcDTO.setAmountInfoRpcDTO(amountInfoRpcDTO);

        return orderRpcDTO;
    }

    private void iniflateStoreAddressRpcDTO(StoreAddressInfoRpcDTO storeAddressInfoRpcDTO, StoreAddressRpcDTO storeAddressRpcDTO) {
        storeAddressRpcDTO.setProvinceName(storeAddressInfoRpcDTO.getProvinceName());
        storeAddressRpcDTO.setCityName(storeAddressInfoRpcDTO.getCityName());
        storeAddressRpcDTO.setAreaName(storeAddressInfoRpcDTO.getAreaName());
        storeAddressRpcDTO.setTownName(storeAddressInfoRpcDTO.getTownName());
        storeAddressRpcDTO.setDetailAddress(storeAddressInfoRpcDTO.getDetailAddress());
    }

    private void inflateTimeRpcDTO(TimeInfoRpcDTO timeInfoRpcDTO, TimeInfo timeInfo) {
        timeInfoRpcDTO.setPlaceOrderTime(timeInfo.getPlaceOrderTime());
        timeInfoRpcDTO.setPayOrderTime(timeInfo.getPayOrderTime());
        timeInfoRpcDTO.setTimeOutTime(timeInfo.getTimeOutTime());
        timeInfoRpcDTO.setCancelOrderTime(timeInfo.getCancelOrderTime());
        timeInfoRpcDTO.setFinishOrderTime(timeInfo.getFinishOrderTime());
        timeInfoRpcDTO.setDeliverCommodityTime(timeInfo.getDeliverCommodityTime());
        timeInfoRpcDTO.setReceivedCommodityTime(timeInfo.getReceivedCommodityTime());
    }

    private void inflateAmountInfoRpcDTO(AmountInfo amountInfo, AmountInfoRpcDTO amountInfoRpcDTO) {
        amountInfoRpcDTO.setPriceOfPayment(amountInfo.getPriceOfPayment());
        amountInfoRpcDTO.setPriceOfCommodity(amountInfo.getPriceOfCommodity());
        amountInfoRpcDTO.setCredit(amountInfo.getCredit());
    }

    private void inflateStoreAddressRpcDTO(StoreAddressInfoRpcDTO storeAddressFromMicroServer, StoreAddressRpcDTO storeAddressRpcDTO) {
        storeAddressRpcDTO.setAddressId(storeAddressFromMicroServer.getAddressId());
        iniflateStoreAddressRpcDTO(storeAddressFromMicroServer, storeAddressRpcDTO);
    }

    private void inflateAddressInfoRpcDTO(AddressInfo addressInfo, AddressInfoRpcDTO addressInfoRpcDTO) {
        addressInfoRpcDTO.setReceiverPhone(addressInfo.getReceiverPhone());
        addressInfoRpcDTO.setReceiverPhone(addressInfo.getReceiverPhone());
        addressInfoRpcDTO.setProvinceName(addressInfo.getProvinceName());
        addressInfoRpcDTO.setCityName(addressInfo.getCityName());
        addressInfoRpcDTO.setAreaName(addressInfo.getAreaName());
        addressInfoRpcDTO.setTownName(addressInfo.getTownName());
        addressInfoRpcDTO.setDetailAddress(addressInfo.getDetailAddress());
    }
}
