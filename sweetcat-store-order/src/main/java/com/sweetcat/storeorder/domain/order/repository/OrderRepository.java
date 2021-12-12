package com.sweetcat.storeorder.domain.order.repository;

import com.sweetcat.storeorder.domain.order.entity.Order;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/4-22:25
 * @version: 1.0
 */
public interface OrderRepository {
    /**
     * 添加订单
     * @param order
     */
    void addOne(Order order);

    /**
     * 查分页数据 by customer id
     * @param customerId
     * @param page
     * @param limit
     */
    List<Order> findPageByCustomerId(Long customerId, Integer page, Integer limit);

    /**
     * 更具订单id 查订单信息
     * @param orderId
     * @return
     */
    Order findOneByOrderId(Long orderId);

    /**
     * 保存更新
     * @param order
     */
    void saveOne(Order order);

    List<Order> findAllByUserIdAndAddressId(Long userId);
}
