package com.sweetcat.takewayorder.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.api.rpcdto.userorder.UserOrderRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.OrderNotExistException;
import com.sweetcat.commons.exception.StoreNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.takewayorder.application.command.AddOrderCommand;
import com.sweetcat.takewayorder.application.command.StoreAddress;
import com.sweetcat.takewayorder.application.command.TimeInfoOfAddOrderCommand;
import com.sweetcat.takewayorder.application.command.UserAddress;
import com.sweetcat.takewayorder.application.rpc.CommodityInfoRpc;
import com.sweetcat.takewayorder.application.rpc.StoreInfoRpc;
import com.sweetcat.takewayorder.application.rpc.UserInfoRpc;
import com.sweetcat.takewayorder.application.rpc.UserOrderRpc;
import com.sweetcat.takewayorder.domain.order.entity.*;
import com.sweetcat.takewayorder.domain.order.repository.OrderRepository;
import com.sweetcat.takewayorder.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-23:13
 * @version: 1.0
 */
@Service
public class OrderApplicationService {
    private OrderRepository orderRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private UserInfoRpc userInfoRpc;
    private StoreInfoRpc storeInfoRpc;
    private UserOrderRpc userOrderRpc;
    private CommodityInfoRpc commodityInfoRpc;

    @Autowired
    public void setCommodityInfoRpc(CommodityInfoRpc commodityInfoRpc) {
        this.commodityInfoRpc = commodityInfoRpc;
    }

    @Autowired
    public void setUserOrderRpc(UserOrderRpc userOrderRpc) {
        this.userOrderRpc = userOrderRpc;
    }

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setStoreInfoRpc(StoreInfoRpc storeInfoRpc) {
        this.storeInfoRpc = storeInfoRpc;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 添加一条记录
     * @param command
     */
    public void addOne(AddOrderCommand command) {
        Long orderId = command.getOrderId();
        Long userId = command.getUserId();
        Long storeId = command.getStoreId();
        verifyIdFormatService.verifyIds(orderId, userId, storeId);
        UserInfoRpcDTO userInfoRpcDTO = userInfoRpc.getUserInfo(userId);
        checkUser(userInfoRpcDTO);
        StoreInfoRpcDTO storeInfoRpcDTO = storeInfoRpc.findOneByStoreId(storeId);
        checkStore(storeInfoRpcDTO);
        UserOrderRpcDTO userOrder = userOrderRpc.findOneByUserIdAndOrderId(userId, orderId);
        checkUserOrder(userOrder);
        Order order = new Order(orderId);
        order.setOrderStatus(Order.STATUS_UNRECEIVE);

        ArrayList<CommodityInfo> commodityInfos = command.getCommodities().stream().collect(
                ArrayList<CommodityInfo>::new,
                (con, commodity) -> {
                    Long commodityId = commodity.getCommodityId();
                    CommodityInfoRpcDTO commodityInfoRpcDTO = commodityInfoRpc.findByCommodityId(commodityId);
                    CommodityInfo commodityInfo = new CommodityInfo(orderId, commodityId);
                    commodityInfo.setCommodityName(commodityInfoRpcDTO.getCommodityName());
                    commodityInfo.setCommodityPicSmall(commodityInfoRpcDTO.getPicSmall());
                    commodityInfo.setPriceWhenMakeOrder(commodity.getPriceOfCommodity());
                    commodityInfo.setCount(commodity.getCount());
                    AmountInfoOfCommodity amountInfoOfCommodity = new AmountInfoOfCommodity(orderId, commodityId);
                    amountInfoOfCommodity.setPriceOfCommodity(commodity.getPriceOfCommodity().multiply(BigDecimal.valueOf(commodity.getCount())));
                    amountInfoOfCommodity.setPriceOfPayment(commodity.getPriceOfPayment());
                    commodityInfo.setAmountInfo(amountInfoOfCommodity);
                    con.add(commodityInfo);
                },
                ArrayList<CommodityInfo>::addAll
        );
        order.setCommodityInfoList(commodityInfos);

        TimeInfo timeInfo = new TimeInfo(orderId);
        TimeInfoOfAddOrderCommand timeInfoOfAddOrderCommand = command.getTimeInfoOfAddOrderCommand();
        inflateTimeInfoOfAddOrderCommand(timeInfo, timeInfoOfAddOrderCommand);
        order.setTimeInfo(timeInfo);

        UserInfo userInfo = new UserInfo(userId);
        UserAddress userAddress = command.getUserAddress();
        userInfo.setUserName(userInfoRpcDTO.getNickname());
        userInfo.setUserAvatar(userInfoRpcDTO.getAvatarPath());
        AddressInfoOfUser addressInfoOfUser = new AddressInfoOfUser(orderId, userId, userAddress.getAddressId());
        inflateAddressInfoOfUser(userAddress, addressInfoOfUser);
        userInfo.setAddressInfoOfUser(addressInfoOfUser);
        order.setUserInfo(userInfo);

        StoreInfo storeInfo = new StoreInfo(storeId);
        StoreAddress storeAddress = command.getStoreAddress();
        storeInfo.setStoreName(storeInfoRpcDTO.getStoreName());
        storeInfo.setStoreLogo(storeInfoRpcDTO.getStoreLogo());
        AddressInfoOfStore addressInfoOfStore = new AddressInfoOfStore(orderId, storeId, storeAddress.getAddressId());
        inflateAddressInfoOfStore(storeAddress, addressInfoOfStore);
        storeInfo.setAddressInfo(addressInfoOfStore);
        order.setStoreInfo(storeInfo);

        AmountInfoOfOrder amountInfoOfOrder = new AmountInfoOfOrder(orderId);
        inflateAmountInfoOrder(command, amountInfoOfOrder);
        order.setAmountInfo(amountInfoOfOrder);

        // 为放方便测试代码流程正确性，此处先注释
        //        orderRepository.addOne(order);
    }

    private void inflateAmountInfoOrder(AddOrderCommand command, AmountInfoOfOrder amountInfoOfOrder) {
        amountInfoOfOrder.setPriceOfPayment(command.getPriceOfPayment());
        amountInfoOfOrder.setPriceOfCommodity(command.getPriceOfCommodity());
    }

    private void inflateAddressInfoOfStore(StoreAddress storeAddress, AddressInfoOfStore addressInfoOfStore) {
        addressInfoOfStore.setProvinceName(storeAddress.getProvinceName());
        addressInfoOfStore.setCityName(storeAddress.getCityName());
        addressInfoOfStore.setAreaName(storeAddress.getAreaName());
        addressInfoOfStore.setTownName(storeAddress.getTownName());
        addressInfoOfStore.setDetailAddress(storeAddress.getDetailAddress());
    }

    private void inflateAddressInfoOfUser(UserAddress userAddress, AddressInfoOfUser addressInfoOfUser) {
        addressInfoOfUser.setReceiverPhone(userAddress.getReceiverPhone());
        addressInfoOfUser.setReceiverName(userAddress.getReceiverName());
        addressInfoOfUser.setProvinceName(userAddress.getProvinceName());
        addressInfoOfUser.setCityName(userAddress.getCityName());
        addressInfoOfUser.setAreaName(userAddress.getAreaName());
        addressInfoOfUser.setTownName(userAddress.getTownName());
        addressInfoOfUser.setDetailAddress(userAddress.getDetailAddress());
    }

    private void inflateTimeInfoOfAddOrderCommand(TimeInfo timeInfo, TimeInfoOfAddOrderCommand timeInfoOfAddOrderCommand) {
        timeInfo.setPlaceOrderTime(timeInfoOfAddOrderCommand.getPlaceOrderTime());
        timeInfo.setPayOrderTime(timeInfoOfAddOrderCommand.getPayOrderTime());
        timeInfo.setTimeOutTime(timeInfoOfAddOrderCommand.getTimeOutTime());
        timeInfo.setCancelOrderTime(timeInfoOfAddOrderCommand.getCancelOrderTime());
        timeInfo.setFinishOrderTime(timeInfoOfAddOrderCommand.getFinishOrderTime());
        timeInfo.setDeliverCommodityTime(timeInfoOfAddOrderCommand.getDeliverCommodityTime());
        timeInfo.setReceivedCommodityTime(timeInfoOfAddOrderCommand.getReceivedCommodityTime());
    }

    private void checkUserOrder(UserOrderRpcDTO userOrder) {
        if (userOrder == null) {
            throw new OrderNotExistException(
                    ResponseStatusEnum.ORDERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.ORDERNOTEXISTED.getErrorMessage()
            );
        }
    }

    private void checkStore(StoreInfoRpcDTO storeInfo) {
        if (storeInfo == null) {
            throw new StoreNotExistedException(
                    ResponseStatusEnum.STORENOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.STORENOTEXISTED.getErrorCode()
            );
        }
    }

    private void checkUser(UserInfoRpcDTO userInfo) {
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
    }

    /**
     * find order by orderId
     * @param orderId
     * @return
     */
    public Order findOneByOrderId(Long orderId) {
        verifyIdFormatService.verifyIds(orderId);
        return orderRepository.findOneByOrderId(orderId);
    }

    public List<Order> findPage(Integer page, Integer limit) {
        limit = limit == null || limit < 0 ? 18 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        return  orderRepository.findPage(page, limit);
    }

}
