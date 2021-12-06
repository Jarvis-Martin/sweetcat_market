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
     * 添加订单
     *
     * @param order
     */
    @Override
    public void addOne(Order order) {
        // 订单入库
        orderMapper.addOne(order);
        // 商品列表
        order.getCommodityInfoList().forEach(
                commodityInfo -> {
                    // 商品入库
                    commodityMapper.addOne(commodityInfo);
                    // 商品金额信息入库
                    amountOfCommodityMapper.addOne(commodityInfo.getAmountInfo());
                    // 商品优惠券入库
                    commodityInfo.getAmountInfo().getDiscountPriceInfo().getCouponsOfCommodity().forEach(
                            couponOfCommodity -> couponOfCommodityMapper.addOne(couponOfCommodity)
                    );
                }
        );
        // 订单金额入库
        AmountInfoOfOrder amountInfo = order.getAmountInfo();
        amountOfOrderMapper.addOne(amountInfo);

        // 订单优惠券入库
        amountInfo.getCouponOfOrders().forEach(
                coupon -> couponOfOrderMapper.addOne(coupon)
        );

        // 用户地址入库
        userAddressMapper.addOne(order.getUserInfo().getAddressInfo());

        // 订单时间信息入库
        timeInfoMapper.addOne(order.getTimeInfo());
    }

    /**
     * 查分页数据 by customer id
     *
     * @param customerId
     * @param page
     * @param limit
     */
    @Override
    public List<Order> findPageByCustomerId(Long customerId, Integer page, Integer limit) {
        List<OrderPO> orderPOPage = orderMapper.findPageByCustomerId(customerId, page, limit);
        if (orderPOPage == null) {
            return null;
        }
        return orderPOPage.stream().collect(
                ArrayList<Order>::new,
                (con, orderPO) -> {
                    Long orderId = orderPO.getOrderId();
                    // 订单商品金额信息
                    List<AmountOfCommodityPO> amountOfCommodityPOS = amountOfCommodityMapper.findAllByOrderId(orderId);
                    // 订单金额信息
                    AmountOfOrderPO amountOfOrderPO = amountOfOrderMapper.findOneByOrderId(orderId);
                    // 订单商品列表信息
                    List<CommodityPO> commodityPOS = commodityMapper.findAllByOrderId(orderId);
                    // 商品优惠券信息
                    List<CouponOfCommodityPO> couponOfCommodityPOS = couponOfCommodityMapper.findAllByOrderId(orderId);
                    // 订单优惠券信息
                    List<CouponOfOrderPO> couponOfOrderPOS = couponOfOrderMapper.findAllByOrderId(orderId);
                    // 订单时间信息
                    TimeInfoPO timeInfo = timeInfoMapper.findOneByOrderId(orderId);
                    // 优惠地址信息
                    UserAddressPO userAddressPO = userAddressMapper.findOneByOrderId(orderId);
                    // 构造 order
                    Order order = orderFactory.create(orderPO, amountOfCommodityPOS, amountOfOrderPO, commodityPOS, couponOfCommodityPOS, couponOfOrderPOS,
                            timeInfo, userAddressPO);
                    con.add(order);
                },
                ArrayList<Order>::addAll
        );
    }

    /**
     * 更具订单id 查订单信息
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
        // 订单商品金额信息
        List<AmountOfCommodityPO> amountOfCommodityPOS = amountOfCommodityMapper.findAllByOrderId(orderId);
        // 订单金额信息
        AmountOfOrderPO amountOfOrderPO = amountOfOrderMapper.findOneByOrderId(orderId);
        // 订单商品列表信息
        List<CommodityPO> commodityPOS = commodityMapper.findAllByOrderId(orderId);
        // 商品优惠券信息
        List<CouponOfCommodityPO> couponOfCommodityPOS = couponOfCommodityMapper.findAllByOrderId(orderId);
        // 订单优惠券信息
        List<CouponOfOrderPO> couponOfOrderPOS = couponOfOrderMapper.findAllByOrderId(orderId);
        // 订单时间信息
        TimeInfoPO timeInfo = timeInfoMapper.findOneByOrderId(orderId);
        // 优惠地址信息
        UserAddressPO userAddressPO = userAddressMapper.findOneByOrderId(orderId);
        // 构造 order
        return orderFactory.create(orderPO, amountOfCommodityPOS, amountOfOrderPO, commodityPOS, couponOfCommodityPOS, couponOfOrderPOS,
                timeInfo, userAddressPO);
    }

    /**
     * 保存更新
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
        // 更新订单信息
        orderMapper.updateOne(order);
        // 更新订单商品
        order.getCommodityInfoList().forEach(
                commodityInfo -> {
                    // 更新订单商品信息
                    commodityMapper.updateOne(commodityInfo);
                    // 更新订单商品金额信息
                    amountOfCommodityMapper.updateOne(commodityInfo.getAmountInfo());
                    // 更新商品优惠券信息
                    commodityInfo.getAmountInfo().getDiscountPriceInfo().getCouponsOfCommodity().forEach(
                            couponOfCommodity -> couponOfCommodityMapper.updateOne(couponOfCommodity)
                    );
                }
        );
        // 更新订单金额信息
        amountOfOrderMapper.updateOne(order.getAmountInfo());
        // 更新订单优惠券信息
        order.getAmountInfo().getCouponOfOrders().forEach(
                coupon -> couponOfOrderMapper.updateOne(coupon)
        );
        // 更新订单时间信息
        timeInfoMapper.updateOne(order.getTimeInfo());

        // 更新优惠地址信息
        userAddressMapper.updateOne(order.getUserInfo().getAddressInfo());
    }
}

