package com.sweetcat.storeorder.infrastructure.repository;

import com.sweetcat.storeorder.domain.order.entity.AmountInfoOfOrder;
import com.sweetcat.storeorder.domain.order.entity.Order;
import com.sweetcat.storeorder.domain.order.repository.OrderRepository;
import com.sweetcat.storeorder.infrastructure.dao.*;
import com.sweetcat.storeorder.infrastructure.factory.OrderFactory;
import com.sweetcat.storeorder.infrastructure.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/4-22:28
 * @version: 1.0
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private OrderFactory orderFactory;
    private AmountOfCommodityMapper amountOfCommodityMapper;
    private AmountOfOrderMapper amountOfOrderMapper;
    private CommodityMapper commodityMapper;
    private CouponOfCommodityMapper couponOfCommodityMapper;
    private CouponOfOrderMapper couponOfOrderMapper;
    private OrderMapper orderMapper;
    private TimeInfoMapper timeInfoMapper;
    private UserAddressMapper userAddressMapper;

    @Autowired
    public void setOrderFactory(OrderFactory orderFactory) {
        this.orderFactory = orderFactory;
    }

    @Autowired
    public void setUserAddressMapper(UserAddressMapper userAddressMapper) {
        this.userAddressMapper = userAddressMapper;
    }

    @Autowired
    public void setTimeInfoMapper(TimeInfoMapper timeInfoMapper) {
        this.timeInfoMapper = timeInfoMapper;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
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
    public void setCommodityMapper(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    @Autowired
    public void setCouponOfCommodityMapper(CouponOfCommodityMapper couponOfCommodityMapper) {
        this.couponOfCommodityMapper = couponOfCommodityMapper;
    }

    @Autowired
    public void setCouponOfOrderMapper(CouponOfOrderMapper couponOfOrderMapper) {
        this.couponOfOrderMapper = couponOfOrderMapper;
    }

    /**
     * ????????????
     *
     * @param order
     */
    @Override
    public void addOne(Order order) {
        // ????????????
        orderMapper.addOne(order);
        // ????????????
        order.getCommodityInfoList().forEach(
                commodityInfo -> {
                    // ????????????
                    commodityMapper.addOne(commodityInfo);
                    // ????????????????????????
                    amountOfCommodityMapper.addOne(commodityInfo.getAmountInfo());
                    // ?????????????????????
                    commodityInfo.getAmountInfo().getDiscountPriceInfo().getCouponsOfCommodity().forEach(
                            couponOfCommodity -> couponOfCommodityMapper.addOne(couponOfCommodity)
                    );
                }
        );
        // ??????????????????
        AmountInfoOfOrder amountInfo = order.getAmountInfo();
        amountOfOrderMapper.addOne(amountInfo);

        // ?????????????????????
        amountInfo.getCouponOfOrders().forEach(
                coupon -> couponOfOrderMapper.addOne(coupon)
        );

        // ??????????????????
        userAddressMapper.addOne(order.getUserInfo().getAddressInfo());

        // ????????????????????????
        timeInfoMapper.addOne(order.getTimeInfo());
    }

    /**
     * ??????????????? by customer id
     *
     * @param customerId
     * @param page
     * @param limit
     */
    @Override
    public List<Order> findPageByCustomerId(Long customerId, Integer page, Integer limit) {
        List<OrderPO> orderPOPage = orderMapper.findPageByCustomerId(customerId, page, limit);
        if (orderPOPage == null || orderPOPage.isEmpty()) {
            return Collections.emptyList();
        }
        return orderPOPage.stream().collect(
                ArrayList<Order>::new,
                (con, orderPO) -> {
                    Long orderId = orderPO.getOrderId();
                    Order order = findNecessaryDataOfOrderAndCreateOrder(orderPO, orderId);
                    con.add(order);
                },
                ArrayList<Order>::addAll
        );
    }

    private Order findNecessaryDataOfOrderAndCreateOrder(OrderPO orderPO, Long orderId) {
        // ????????????????????????
        List<AmountOfCommodityPO> amountOfCommodityPOS = amountOfCommodityMapper.findAllByOrderId(orderId);
        // ??????????????????
        AmountOfOrderPO amountOfOrderPO = amountOfOrderMapper.findOneByOrderId(orderId);
        // ????????????????????????
        List<CommodityPO> commodityPOS = commodityMapper.findAllByOrderId(orderId);
        // ?????????????????????
        List<CouponOfCommodityPO> couponOfCommodityPOS = couponOfCommodityMapper.findAllByOrderId(orderId);
        // ?????????????????????
        List<CouponOfOrderPO> couponOfOrderPOS = couponOfOrderMapper.findAllByOrderId(orderId);
        // ??????????????????
        TimeInfoPO timeInfo = timeInfoMapper.findOneByOrderId(orderId);
        // ??????????????????
        UserAddressPO userAddressPO = userAddressMapper.findOneByOrderId(orderId);
        // ?????? order
        return orderFactory.create(orderPO, amountOfCommodityPOS, amountOfOrderPO, commodityPOS, couponOfCommodityPOS, couponOfOrderPOS,
                timeInfo, userAddressPO);
    }

    /**
     * ????????????id ???????????????
     *
     * @param orderId
     * @return
     */
    @Override
    public Order findOneByOrderId(Long orderId) {
        OrderPO orderPO = orderMapper.findOneByOrderId(orderId);
        if (orderPO == null) {
            return null;
        }
        return findNecessaryDataOfOrderAndCreateOrder(orderPO, orderId);
    }

    /**
     * ????????????
     *
     * @param order
     */
    @Override
    public void saveOne(Order order) {
        Long orderId = order.getOrderId();
        OrderPO oneByOrderId = orderMapper.findOneByOrderId(orderId);
        if (oneByOrderId == null) {
            return;
        }
        // ??????????????????
        orderMapper.updateOne(order);
        // ??????????????????
        order.getCommodityInfoList().forEach(
                commodityInfo -> {
                    // ????????????????????????
                    commodityMapper.updateOne(commodityInfo);
                    // ??????????????????????????????
                    amountOfCommodityMapper.updateOne(commodityInfo.getAmountInfo());
                    // ???????????????????????????
                    commodityInfo.getAmountInfo().getDiscountPriceInfo().getCouponsOfCommodity().forEach(
                            couponOfCommodity -> couponOfCommodityMapper.updateOne(couponOfCommodity)
                    );
                }
        );
        // ????????????????????????
        amountOfOrderMapper.updateOne(order.getAmountInfo());
        // ???????????????????????????
        order.getAmountInfo().getCouponOfOrders().forEach(
                coupon -> couponOfOrderMapper.updateOne(coupon)
        );
        // ????????????????????????
        timeInfoMapper.updateOne(order.getTimeInfo());

        // ????????????????????????
        userAddressMapper.updateOne(order.getUserInfo().getAddressInfo());
    }

    @Override
    public List<Order> findAllByUserIdAndAddressId(Long userId) {
        List<OrderPO> orderPOs = orderMapper.findAllByUserId(userId);
        return orderPOs.stream().collect(
                ArrayList<Order>::new,
                (con, orderPO) -> {
                    Long orderId = orderPO.getOrderId();
                    Order order = findNecessaryDataOfOrderAndCreateOrder(orderPO, orderId);
                    con.add(order);
                },
                ArrayList<Order>::addAll
        );
    }
}

