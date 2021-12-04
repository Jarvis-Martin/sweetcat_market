package com.sweetcat.userorder.infrastructure.repository;

import com.sweetcat.userorder.domain.order.entity.ChildrenOrder;
import com.sweetcat.userorder.domain.order.entity.Order;
import com.sweetcat.userorder.domain.order.repository.OrderRepository;
import com.sweetcat.userorder.infrastructure.dao.*;
import com.sweetcat.userorder.infrastructure.factory.ChildrenOrderFactory;
import com.sweetcat.userorder.infrastructure.po.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-22:37
 * @version: 1.0
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private OrderMapper orderMapper;
    private ChildrenOrderMapper childrenOrderMapper;
    private AddressMapper addressMapper;
    private AmountOfCommodityMapper amountOfCommodityMapper;
    private AmountOfOrderMapper amountOfOrderMapper;
    private CommodityInfoMapper commodityInfoMapper;
    private CouponMapper couponMapper;
    private StoreInfoOfCommodityMapper storeInfoOfCommodityMapper;
    private TimeInfoMapper timeInfoMapper;
    private ChildrenOrderFactory childrenOrderFactory;

    @Autowired
    public void setChildrenOrderFactory(ChildrenOrderFactory childrenOrderFactory) {
        this.childrenOrderFactory = childrenOrderFactory;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setChildrenOrderMapper(ChildrenOrderMapper childrenOrderMapper) {
        this.childrenOrderMapper = childrenOrderMapper;
    }

    @Autowired
    public void setAddressMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
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
    public void setCommodityInfoMapper(CommodityInfoMapper commodityInfoMapper) {
        this.commodityInfoMapper = commodityInfoMapper;
    }

    @Autowired
    public void setCouponMapper(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    @Autowired
    public void setStoreInfoOfCommodityMapper(StoreInfoOfCommodityMapper storeInfoOfCommodityMapper) {
        this.storeInfoOfCommodityMapper = storeInfoOfCommodityMapper;
    }

    @Autowired
    public void setTimeInfoMapper(TimeInfoMapper timeInfoMapper) {
        this.timeInfoMapper = timeInfoMapper;
    }

    /**
     * 添加一条 order
     *
     * @param order
     * @param <T>
     */
    @Override
    public <T extends Order> void addOne(T order) {
        addressMapper.addOne(order.getUserInfo().getAddressInfo());
        order.getCommodityInfoList().forEach(
                commodityInfo -> {
                    amountOfCommodityMapper.addOne(commodityInfo.getAmountInfo());
                    commodityInfoMapper.addOne(commodityInfo);
                }
        );
        order.getAmountInfo().getCoupons().forEach(
                coupon -> couponMapper.addOne(coupon)
        );
        timeInfoMapper.addOne(order.getTimeInfo());
        amountOfOrderMapper.addOne(order.getAmountInfo());
        if (Order.TYPE_UNSPLITED.equals(order.getType())) {
            orderMapper.addOne(order);
            order.getCommodityInfoList().forEach(
                    commodityInfo -> storeInfoOfCommodityMapper.addOne(commodityInfo.getStoreInfo())
            );
        }
        if (Order.TYPE_SPLITED.equals(order.getType())) {
            ChildrenOrder childrenOrder = (ChildrenOrder) order;
            childrenOrderMapper.addOne(childrenOrder);
            storeInfoOfCommodityMapper.addOne(order.getCommodityInfoList().get(0).getStoreInfo());
        }
    }

    /**
     * 移除一条订单
     *
     * @param order
     * @param <T>
     */
    @Override
    public <T extends Order> void removeOne(T order) {
        addressMapper.deleteOne(order.getUserInfo().getAddressInfo());
        order.getCommodityInfoList().forEach(
                commodityInfo -> {
                    amountOfCommodityMapper.deleteOne(commodityInfo.getAmountInfo());
                    commodityInfoMapper.deleteOne(commodityInfo);
                }
        );
        order.getAmountInfo().getCoupons().forEach(
                coupon -> couponMapper.deleteOne(coupon)
        );
        timeInfoMapper.deleteOne(order.getTimeInfo());
        amountOfOrderMapper.deleteOne(order.getAmountInfo());
        if (Order.TYPE_UNSPLITED.equals(order.getType())) {
            orderMapper.deleteOne(order);
            order.getCommodityInfoList().forEach(
                    commodityInfo -> storeInfoOfCommodityMapper.deleteOne(commodityInfo.getStoreInfo())
            );
        }
        if (Order.TYPE_SPLITED.equals(order.getType())) {
            ChildrenOrder childrenOrder = (ChildrenOrder) order;
            childrenOrderMapper.deleteOne(childrenOrder);
            storeInfoOfCommodityMapper.deleteOne(order.getCommodityInfoList().get(0).getStoreInfo());
        }
    }

    /**
     * 保存一条订单
     *
     * @param order
     * @param <T>
     */
    @Override
    public <T extends Order> void saveOne(T order) {
        addressMapper.updateOne(order.getUserInfo().getAddressInfo());
        order.getCommodityInfoList().forEach(
                commodityInfo -> {
                    amountOfCommodityMapper.updateOne(commodityInfo.getAmountInfo());
                    commodityInfoMapper.updateOne(commodityInfo);
                }
        );
        order.getAmountInfo().getCoupons().forEach(
                coupon -> couponMapper.updateOne(coupon)
        );
        timeInfoMapper.updateOne(order.getTimeInfo());
        amountOfOrderMapper.updateOne(order.getAmountInfo());
        if (Order.TYPE_UNSPLITED.equals(order.getType())) {
            orderMapper.updateOne(order);
            order.getCommodityInfoList().forEach(
                    commodityInfo -> storeInfoOfCommodityMapper.updateOne(commodityInfo.getStoreInfo())
            );
        }
        if (Order.TYPE_SPLITED.equals(order.getType())) {
            ChildrenOrder childrenOrder = (ChildrenOrder) order;
            childrenOrderMapper.updateOne(childrenOrder);
            storeInfoOfCommodityMapper.updateOne(order.getCommodityInfoList().get(0).getStoreInfo());
        }
    }

    @Override
    public List<ChildrenOrder> findPageByUserId(@Param("userId") Long userId, @Param("page") Integer page, @Param("limit") Integer limit) {
        List<ChildrenOrderPO> childrenOrderPOS = childrenOrderMapper.findPageByUserId(userId, page, limit);
        if (childrenOrderPOS == null || childrenOrderPOS.size() <= 0) {
            return null;
        }
        return childrenOrderPOS.stream().collect(
                ArrayList<ChildrenOrder>::new,
                (con, childrenOrderPO) -> {
                    AddressPO addressPO = addressMapper.findOneByOrderId(childrenOrderPO.getChildrenOrderId());
                    List<AmountOfCommodityPO> amountPageOfCommodityPO = amountOfCommodityMapper.findAllByOrderId(childrenOrderPO.getChildrenOrderId());
                    AmountOfOrderPO amountOfOrderPO = amountOfOrderMapper.findOneByOrderId(childrenOrderPO.getChildrenOrderId());
                    List<CommodityInfoPO> commodityInfoPOS = commodityInfoMapper.findAllByOrderId(childrenOrderPO.getChildrenOrderId());
                    List<CouponPO> couponPOS = couponMapper.findAllByOrderId(childrenOrderPO.getChildrenOrderId());
                    StoreInfoOfCommodityPO storeInfoOfCommodityPO = storeInfoOfCommodityMapper.findOneByOrderId(childrenOrderPO.getChildrenOrderId());
                    TimeInfoPO timeInfoPO = timeInfoMapper.findOneByOrderId(childrenOrderPO.getChildrenOrderId());
                    ChildrenOrder childrenOrder = childrenOrderFactory.create(childrenOrderPO, addressPO, amountPageOfCommodityPO,
                            amountOfOrderPO, commodityInfoPOS, couponPOS, storeInfoOfCommodityPO, timeInfoPO);
                    con.add(childrenOrder);
                },
                ArrayList<ChildrenOrder>::addAll
        );
    }

    @Override
    public ChildrenOrder findOneByUserIdAndOrderId(Long userId, Long childrenOrderId) {
        ChildrenOrderPO childrenOrderPO = childrenOrderMapper.findOneByUserIdAndOrderId(userId, childrenOrderId);
        AddressPO addressPO = addressMapper.findOneByOrderId(childrenOrderId);
        List<AmountOfCommodityPO> amountOfCommodityPOPage = amountOfCommodityMapper.findAllByOrderId(childrenOrderId);
        AmountOfOrderPO amountOfOrderPO = amountOfOrderMapper.findOneByOrderId(childrenOrderId);
        List<CommodityInfoPO> commodityInfoPOS = commodityInfoMapper.findAllByOrderId(childrenOrderId);
        List<CouponPO> couponPOS = couponMapper.findAllByOrderId(childrenOrderId);
        StoreInfoOfCommodityPO storeInfoOfCommodityPO = storeInfoOfCommodityMapper.findOneByOrderId(childrenOrderId);
        TimeInfoPO timeInfoPO = timeInfoMapper.findOneByOrderId(childrenOrderId);
        if (childrenOrderPO == null) {
            return null;
        }
        return childrenOrderFactory.create(childrenOrderPO, addressPO, amountOfCommodityPOPage,
                amountOfOrderPO, commodityInfoPOS, couponPOS, storeInfoOfCommodityPO, timeInfoPO);
    }
}
