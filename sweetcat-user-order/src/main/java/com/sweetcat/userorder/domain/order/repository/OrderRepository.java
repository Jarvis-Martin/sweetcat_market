package com.sweetcat.userorder.domain.order.repository;

import com.sweetcat.userorder.domain.order.entity.ChildrenOrder;
import com.sweetcat.userorder.domain.order.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-21:22
 * @version: 1.0
 */
public interface OrderRepository {
    /**
     * 添加一条 order
     * @param order
     * @param <T>
     */
    <T extends Order> void addOne(T order);

    /**
     * 移除一条订单
     * @param order
     * @param <T>
     */
    <T extends Order> void removeOne(T order);

    /**
     * 保存一条订单
     * @param order
     * @param <T>
     */
    <T extends Order> void saveOne(T order);

    List<ChildrenOrder> findPageByUserId(@Param("userId") Long userId, @Param("page") Integer page, @Param("limit") Integer limit);

    ChildrenOrder findOneByOrderId(Long userId, Long childrenOrderId);
}
