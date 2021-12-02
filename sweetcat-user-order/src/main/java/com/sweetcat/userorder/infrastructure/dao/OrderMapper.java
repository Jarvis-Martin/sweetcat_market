package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.domain.order.entity.Order;
import com.sweetcat.userorder.infrastructure.po.OrderPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 添加一条记录
     *
     * @param order
     */
    void addOne(Order order);

    /**
     * 删除一条记录
     *
     * @param order
     */
    void deleteOne(Order order);

    /**
     * 更新一条记录
     *
     * @param order
     */
    void updateOne(Order order);

    /**
     * 查分页数据 by userId
     *
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    List<OrderPO> findPageByUserId(@Param("userId") Long userId, @Param("page") Integer page, @Param("limit") Integer limit);

}