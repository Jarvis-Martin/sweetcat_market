package com.sweetcat.takewayorder.domain.order.repository;

import com.sweetcat.takewayorder.domain.order.entity.Order;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-23:00
 * @version: 1.0
 */
public interface OrderRepository {
    /**
     * 添加一条记录
     * @param order
     */
    void addOne(Order order);

    /**
     * 保存修改
     * @param order
     */
    void saveOne(Order order);

    /**
     * find order by orderId
     * @param orderId
     * @return
     */
    Order findOneByOrderId(Long orderId);

    List<Order> findPage(Integer page, Integer limit);
}
