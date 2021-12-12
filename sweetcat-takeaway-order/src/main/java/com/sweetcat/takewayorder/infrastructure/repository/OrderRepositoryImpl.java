package com.sweetcat.takewayorder.infrastructure.repository;

import com.sweetcat.takewayorder.domain.order.entity.Order;
import com.sweetcat.takewayorder.domain.order.entity.StoreInfo;
import com.sweetcat.takewayorder.domain.order.repository.OrderRepository;
import com.sweetcat.takewayorder.infrastructure.dao.*;
import com.sweetcat.takewayorder.infrastructure.factory.OrderFactory;
import com.sweetcat.takewayorder.infrastructure.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-23:01
 * @version: 1.0
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private AmountOfCommodityMapper amountOfCommodityMapper;
    private AmountOfOrderMapper amountOfOrderMapper;
    private CommodityOfOrderMapper commodityOfOrderMapper;
    private StoreAddressMapper storeAddressMapper;
    private StoreInfoMapper storeInfoMapper;
    private TakeawayOrderMapper orderMapper;
    private TimeInfoMapper timeInfoMapper;
    private UserAddressMapper userAddressMapper;
    private OrderFactory orderFactory;

    @Autowired
    public void setOrderMapper(TakeawayOrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setOrderFactory(OrderFactory orderFactory) {
        this.orderFactory = orderFactory;
    }

    @Autowired
    public void setAmountOfCommodityMapper(AmountOfCommodityMapper amountOfCommodityMapper) {
        this.amountOfCommodityMapper = amountOfCommodityMapper;
    }

    @Autowired
    public void setAmountOfOrderMapper(AmountOfOrderMapper amountOfOrderMapper) {
        this.amountOfOrderMapper = amountOfOrderMapper;
    }

    @Autowired
    public void setCommodityOfOrderMapper(CommodityOfOrderMapper commodityOfOrderMapper) {
        this.commodityOfOrderMapper = commodityOfOrderMapper;
    }

    @Autowired
    public void setStoreAddressMapper(StoreAddressMapper storeAddressMapper) {
        this.storeAddressMapper = storeAddressMapper;
    }

    @Autowired
    public void setStoreInfoMapper(StoreInfoMapper storeInfoMapper) {
        this.storeInfoMapper = storeInfoMapper;
    }

    @Autowired
    public void setTakeawayOrderMapper(TakeawayOrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setTimeInfoMapper(TimeInfoMapper timeInfoMapper) {
        this.timeInfoMapper = timeInfoMapper;
    }

    @Autowired
    public void setUserAddressMapper(UserAddressMapper userAddressMapper) {
        this.userAddressMapper = userAddressMapper;
    }

    /**
     * 添加一条记录
     * @param order
     */
    @Override
    public void addOne(Order order) {
        orderMapper.addOne(order);
        order.getCommodityInfoList().forEach(
                commodityInfo -> {
                    commodityOfOrderMapper.addOne(commodityInfo);
                    amountOfCommodityMapper.addOne(commodityInfo.getAmountInfo());
                }
        );
        timeInfoMapper.addOne(order.getTimeInfo());
        userAddressMapper.addOne(order.getUserInfo().getAddressInfoOfUser());
        StoreInfo storeInfo = order.getStoreInfo();
        storeInfoMapper.addOne(storeInfo);
        storeAddressMapper.addOne(storeInfo.getAddressInfo());
        amountOfOrderMapper.addOne(order.getAmountInfo());
    }

    /**
     * 保存修改
     * @param order
     */
    @Override
    public void saveOne(Order order) {
        orderMapper.updateOne(order);
        order.getCommodityInfoList().forEach(
                commodityInfo -> {
                    commodityOfOrderMapper.updateOne(commodityInfo);
                    amountOfCommodityMapper.updateOne(commodityInfo.getAmountInfo());
                }
        );
        timeInfoMapper.updateOne(order.getTimeInfo());
        userAddressMapper.updateOne(order.getUserInfo().getAddressInfoOfUser());
        StoreInfo storeInfo = order.getStoreInfo();
        storeInfoMapper.updateOne(storeInfo);
        storeAddressMapper.updateOne(storeInfo.getAddressInfo());
        amountOfOrderMapper.updateOne(order.getAmountInfo());
    }

    /**
     * find order by orderId
     * @param orderId
     * @return
     */
    @Override
    public Order findOneByOrderId(Long orderId) {
        TakeawayOrderPO orderPO = orderMapper.findOne(orderId);
        return findNecessaryDataOfOrderAndCreateOrder(orderId, orderPO);
    }


    @Override
    public List<Order> findPage(Integer page, Integer limit) {
        List<TakeawayOrderPO> orderPage = orderMapper.findPage(page, limit);
        return orderPage.stream().collect(
                ArrayList<Order>::new,
                (con, orderPO) -> {
                    Long orderId = orderPO.getOrderId();
                    Order order = findNecessaryDataOfOrderAndCreateOrder(orderId, orderPO);
                    con.add(order);
                },
                ArrayList<Order>::addAll
        );
    }

    private Order findNecessaryDataOfOrderAndCreateOrder(Long orderId, TakeawayOrderPO orderPO) {
        List<CommodityOfOrderPO> commodityOfOrderPOS = commodityOfOrderMapper.findAllByOrderId(orderId);
        List<AmountOfCommodityPO> amountOfCommodityPOS = amountOfCommodityMapper.findAll(orderId);
        TimeInfoOfOrderPO timeInfoOfOrderPO = timeInfoMapper.findOneByOrderId(orderId);
        UserAddressPO userAddressPO = userAddressMapper.findOneByOrderId(orderId);
        StoreInfoPO storeInfoPO = storeInfoMapper.findOneByOrderId(orderId);
        StoreAddressPO storeAddressPO = storeAddressMapper.findOneByOrderId(orderId);
        AmountOfOrderPO amountOfOrderPO = amountOfOrderMapper.findOneByOrderId(orderId);
        return orderFactory.create(orderPO, commodityOfOrderPOS, amountOfCommodityPOS, timeInfoOfOrderPO, userAddressPO,
                storeInfoPO, storeAddressPO, amountOfOrderPO);
    }

    @Override
    public List<Order> findAllByUserIdAndAddressId(Long userId) {
        List<TakeawayOrderPO> orderPOs = orderMapper.findAllByUserIdAndAddressId(userId);
        ArrayList<Order> orders = orderPOs.stream().collect(
                ArrayList<Order>::new,
                (con, orderPO) -> {
                    Long orderId = orderPO.getOrderId();
                    Order order = findNecessaryDataOfOrderAndCreateOrder(orderId, orderPO);
                    con.add(order);
                },
                ArrayList<Order>::addAll
        );
        return orders;
    }
}
