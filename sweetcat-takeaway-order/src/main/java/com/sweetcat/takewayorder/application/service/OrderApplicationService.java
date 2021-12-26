package com.sweetcat.takewayorder.application.service;

import com.sweetcat.api.rpcdto.commodityinfo.CommodityInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreAddressInfoRpcDTO;
import com.sweetcat.api.rpcdto.storeinfo.StoreInfoRpcDTO;
import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.api.rpcdto.userorder.UserOrderRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.AddressNotExistedException;
import com.sweetcat.commons.exception.OrderNotExistException;
import com.sweetcat.commons.exception.StoreNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.takewayorder.application.command.addordercommand.AddOrderCommand;
import com.sweetcat.takewayorder.application.command.addordercommand.StoreAddress;
import com.sweetcat.takewayorder.application.command.addordercommand.TimeInfoOfAddOrderCommand;
import com.sweetcat.takewayorder.application.command.addordercommand.UserAddress;
import com.sweetcat.takewayorder.application.command.changecustomeraddresscommand.ChangeCustomerAddressCommand;
import com.sweetcat.takewayorder.application.command.changecustomeraddresscommand.UserAddressOfCommand;
import com.sweetcat.takewayorder.application.rpc.CommodityInfoRpc;
import com.sweetcat.takewayorder.application.rpc.StoreInfoRpc;
import com.sweetcat.takewayorder.application.rpc.UserInfoRpc;
import com.sweetcat.takewayorder.application.rpc.UserOrderRpc;
import com.sweetcat.takewayorder.domain.order.entity.*;
import com.sweetcat.takewayorder.domain.order.repository.OrderRepository;
import com.sweetcat.takewayorder.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     *
     * @param command
     */
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
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

        StoreInfo storeInfo = new StoreInfo(orderId, storeId);
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
        orderRepository.addOne(order);
    }

    private void inflateAmountInfoOrder(AddOrderCommand command, AmountInfoOfOrder amountInfoOfOrder) {
        amountInfoOfOrder.setPriceOfPayment(command.getPriceOfPayment());
        amountInfoOfOrder.setPriceOfCommodity(command.getPriceOfCommodity());
    }

    private void inflateAddressInfoOfStore(StoreAddress storeAddress, AddressInfoOfStore addressInfoOfStore) {
        inflateAddressInfoOfStore(addressInfoOfStore, storeAddress.getProvinceName(), storeAddress.getCityName(), storeAddress.getAreaName(), storeAddress.getTownName(), storeAddress.getDetailAddress());
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
            throwOrderNotExistException();
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
     *
     * @param orderId
     * @return
     */
    @Transactional
    public Order findOneByOrderId(Long orderId) {
        verifyIdFormatService.verifyIds(orderId);
        return orderRepository.findOneByOrderId(orderId);
    }

    @Transactional
    public List<Order> findPage(Integer page, Integer limit) {
        limit = limit == null || limit < 0 ? 18 : limit;
        page = page == null || page < 0 ? 0 : page * limit;
        return orderRepository.findPage(page, limit);
    }

    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void changeCustomerAddress(ChangeCustomerAddressCommand command) {
        Long userId = command.getUserId();
        Long addressId = command.getAddressId();
        verifyIdFormatService.verifyIds(userId, addressId);
        UserInfoRpcDTO userInfoRpcDTO = userInfoRpc.getUserInfo(userId);
        checkUser(userInfoRpcDTO);
        List<Order> orders = orderRepository.findAllByUserIdAndAddressId(userId);
        if (orders == null || orders.isEmpty()) {
            throwOrderNotExistException();
        }
        String receiverName = command.getReceiverName();
        String receiverPhone = command.getReceiverPhone();
        String provinceName = command.getProvinceName();
        String areaName = command.getAreaName();
        String cityName = command.getCityName();
        String townName = command.getTownName();
        String detailAddress = command.getDetailAddress();
        orders.forEach(
                order -> {
                    UserInfo userInfo = order.getUserInfo();
                    UserInfo userInfoCloned = null;
                    try {
                        userInfoCloned = userInfo.clone();
                        Long orderId = order.getOrderId();
                        AddressInfoOfUser userAddress = new AddressInfoOfUser(orderId, userId, addressId);
                        inflateAddressInfoOfUser(receiverName, receiverPhone, provinceName, areaName, cityName, townName, detailAddress, userAddress);
                        userInfoCloned.setAddressInfoOfUser(userAddress);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    order.setUserInfo(userInfoCloned);
                    orderRepository.saveOne(order);
                }
        );

    }

    private void inflateAddressInfoOfUser(String receiverName, String receiverPhone, String provinceName, String areaName, String cityName, String townName, String detailAddress, AddressInfoOfUser userAddress) {
        userAddress.setReceiverName(receiverName);
        userAddress.setReceiverPhone(receiverPhone);
        userAddress.setProvinceName(provinceName);
        userAddress.setCityName(cityName);
        userAddress.setAreaName(areaName);
        userAddress.setTownName(townName);
        userAddress.setDetailAddress(detailAddress);
    }

    private void throwOrderNotExistException() {
        throw new OrderNotExistException(
                ResponseStatusEnum.ORDERNOTEXISTED.getErrorCode(),
                ResponseStatusEnum.ORDERNOTEXISTED.getErrorMessage()
        );
    }

    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void changeStoreAddress(Long storeId, Long orderId, Long addressId) {
        verifyIdFormatService.verifyIds(storeId, orderId, addressId);
        StoreInfoRpcDTO storeInfoRpcDTO = storeInfoRpc.findOneByStoreId(storeId);
        checkStore(storeInfoRpcDTO);
        StoreAddressInfoRpcDTO storeAddressRpcDTO = storeInfoRpc.findOneStoreAddressByStoreId(storeId);
        checkStoreAddress(storeAddressRpcDTO);

        Order order = orderRepository.findOneByOrderId(orderId);
        StoreInfo storeInfo = order.getStoreInfo();
        StoreInfo storeInfoCloned = null;
        try {
            storeInfoCloned = storeInfo.clone();
            AddressInfoOfStore addressInfoOfStore = new AddressInfoOfStore(orderId, storeId, addressId);
            inflateAddressInfoOfStore(addressInfoOfStore, storeAddressRpcDTO.getProvinceName(), storeAddressRpcDTO.getCityName(), storeAddressRpcDTO.getAreaName(), storeAddressRpcDTO.getTownName(), storeAddressRpcDTO.getDetailAddress());
            storeInfoCloned.setAddressInfo(addressInfoOfStore);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        order.setStoreInfo(storeInfo);
        orderRepository.saveOne(order);
    }

    private void inflateAddressInfoOfStore(AddressInfoOfStore addressInfoOfStore, String provinceName, String cityName, String areaName, String townName, String detailAddress) {
        addressInfoOfStore.setProvinceName(provinceName);
        addressInfoOfStore.setCityName(cityName);
        addressInfoOfStore.setAreaName(areaName);
        addressInfoOfStore.setTownName(townName);
        addressInfoOfStore.setDetailAddress(detailAddress);
    }

    private void checkStoreAddress(StoreAddressInfoRpcDTO storeAddressRpcDTO) {
        if (storeAddressRpcDTO == null) {
            throwAddressNotExistedException();
        }
    }

    private void throwAddressNotExistedException() {
        throw new AddressNotExistedException(
                ResponseStatusEnum.ADDRESSNOTEXISTED.getErrorCode(),
                ResponseStatusEnum.ADDRESSNOTEXISTED.getErrorMessage()
        );
    }
}
