package com.sweetcat.takewayorder.infrastructure.factory;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.takewayorder.application.rpc.UserInfoRpc;
import com.sweetcat.takewayorder.domain.order.entity.*;
import com.sweetcat.takewayorder.infrastructure.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-23:17
 * @version: 1.0
 */
@Component
public class OrderFactory {
    private UserInfoRpc userInfoRpc;

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    public Order create(TakeawayOrderPO orderPO, List<CommodityOfOrderPO> commodityOfOrderPOS,
                        List<AmountOfCommodityPO> amountOfCommodityPOS,
                        TimeInfoOfOrderPO timeInfoOfOrderPO, UserAddressPO userAddressPO,
                        StoreInfoPO storeInfoPO, StoreAddressPO storeAddressPO,
                        AmountOfOrderPO amountOfOrderPO) {
        Long orderId = orderPO.getOrderId();
        Long userId = orderPO.getUserId();
        Long addressId = storeAddressPO.getAddressId();
        Order order = new Order(orderId);
        order.setOrderStatus(orderPO.getOrderStaus());

        TimeInfo timeInfo = new TimeInfo(orderId);
        inflateTimeInfo(timeInfoOfOrderPO, timeInfo);
        order.setTimeInfo(timeInfo);

        UserInfo userInfo = new UserInfo(orderId);
        UserInfoRpcDTO userInfoRpcDTO = userInfoRpc.getUserInfo(userId);
        chechUserInfo(userInfoRpcDTO);
        userInfo.setUserName(userInfoRpcDTO.getNickname());
        userInfo.setUserAvatar(userInfoRpcDTO.getAvatarPath());
        AddressInfoOfUser addressInfoOfUser = new AddressInfoOfUser(orderId, userId, userAddressPO.getAddressId());
        inflateAddressInfoOfUser(userAddressPO, addressInfoOfUser);
        userInfo.setAddressInfoOfUser(addressInfoOfUser);
        order.setUserInfo(userInfo);

        Long storeId = storeInfoPO.getStoreId();
        StoreInfo storeInfo = new StoreInfo(storeId);
        storeInfo.setStoreName(storeInfoPO.getStoreName());
        storeInfo.setStoreLogo(storeInfoPO.getStoreLogo());
        AddressInfoOfStore addressInfoOfStore = new AddressInfoOfStore(orderId, orderId, addressId);
        inflateAddressInfoOfStore(storeAddressPO, addressInfoOfStore);
        storeInfo.setAddressInfo(addressInfoOfStore);
        order.setStoreInfo(storeInfo);

        ArrayList<CommodityInfo> commodityInfos = commodityOfOrderPOS.stream().collect(
                ArrayList<CommodityInfo>::new,
                (con, commodityInfoPO) -> {
                    Long commodityId = commodityInfoPO.getCommodityId();
                    CommodityInfo commodityInfo = new CommodityInfo(orderId, commodityId);
                    commodityInfo.setCommodityName(commodityInfoPO.getCommodityName());
                    commodityInfo.setCommodityPicSmall(commodityInfoPO.getCommodityPicSmall());
                    commodityInfo.setPriceWhenMakeOrder(commodityInfoPO.getPriceWhenMakeOrder());
                    commodityInfo.setCount(commodityInfoPO.getCount());

                    AmountOfCommodityPO amountOfCommodityPO = amountOfCommodityPOS.stream().filter(
                            amountOfCommodityPO1 -> amountOfCommodityPO1.getCommodityId().equals(commodityId)
                    ).findFirst().get();

                    AmountInfoOfCommodity amountInfoOfCommodity = new AmountInfoOfCommodity(orderId, commodityId);
                    inflateAmountInfoOfCommodity(amountOfCommodityPO, amountInfoOfCommodity);
                    commodityInfo.setAmountInfo(amountInfoOfCommodity);
                },
                ArrayList<CommodityInfo>::addAll
        );
        order.setCommodityInfoList(commodityInfos);

        AmountInfoOfOrder amountInfoOfOrder = new AmountInfoOfOrder(orderId);
        inflateAmountInfoOfOrder(amountOfOrderPO, amountInfoOfOrder);
        order.setAmountInfo(amountInfoOfOrder);
        return order;
    }

    private void inflateAmountInfoOfOrder(AmountOfOrderPO amountOfOrderPO, AmountInfoOfOrder amountInfoOfOrder) {
        amountInfoOfOrder.setPriceOfCommodity(amountOfOrderPO.getPriceOfCommodity());
        amountInfoOfOrder.setPriceOfPayment(amountOfOrderPO.getPriceOfPayment());
    }

    private void inflateAmountInfoOfCommodity(AmountOfCommodityPO amountOfCommodityPO, AmountInfoOfCommodity amountInfoOfCommodity) {
        amountInfoOfCommodity.setPriceOfCommodity(amountOfCommodityPO.getPriceOfCommodity());
        amountInfoOfCommodity.setPriceOfPayment(amountOfCommodityPO.getPriceOfPayment());
    }

    private void inflateAddressInfoOfStore(StoreAddressPO storeAddressPO, AddressInfoOfStore addressInfoOfStore) {
        addressInfoOfStore.setProvinceName(storeAddressPO.getProvinceName());
        addressInfoOfStore.setCityName(storeAddressPO.getCityName());
        addressInfoOfStore.setAreaName(storeAddressPO.getAreaName());
        addressInfoOfStore.setTownName(storeAddressPO.getTownName());
        addressInfoOfStore.setDetailAddress(storeAddressPO.getDetailAddress());
    }

    private void inflateAddressInfoOfUser(UserAddressPO userAddressPO, AddressInfoOfUser addressInfoOfUser) {
        addressInfoOfUser.setReceiverName(userAddressPO.getReceiverName());
        addressInfoOfUser.setReceiverPhone(userAddressPO.getReceiverPhone());
        addressInfoOfUser.setProvinceName(userAddressPO.getProvinceName());
        addressInfoOfUser.setCityName(userAddressPO.getCityName());
        addressInfoOfUser.setAreaName(userAddressPO.getAreaName());
        addressInfoOfUser.setTownName(userAddressPO.getTownName());
        addressInfoOfUser.setDetailAddress(userAddressPO.getDetailAddress());
    }

    private void chechUserInfo(UserInfoRpcDTO userInfoRpcDTO) {
        if (userInfoRpcDTO == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    private void inflateTimeInfo(TimeInfoOfOrderPO timeInfoOfOrderPO, TimeInfo timeInfo) {
        timeInfo.setPlaceOrderTime(timeInfoOfOrderPO.getPlaceOrderTime());
        timeInfo.setPayOrderTime(timeInfoOfOrderPO.getPayOrderTime());
        timeInfo.setTimeOutTime(timeInfoOfOrderPO.getTimeOutTime());
        timeInfo.setCancelOrderTime(timeInfoOfOrderPO.getCancelOrderTime());
        timeInfo.setFinishOrderTime(timeInfoOfOrderPO.getFinishOrderTime());
        timeInfo.setDeliverCommodityTime(timeInfoOfOrderPO.getDeliverCommodityTime());
        timeInfo.setReceivedCommodityTime(timeInfoOfOrderPO.getReceivedCommodityTime());
    }
}
