package com.sweetcat.userorder.infrastructure.dao;

import com.sweetcat.userorder.domain.order.entity.ChildrenOrder;
import com.sweetcat.userorder.infrastructure.po.ChildrenOrderPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChildrenOrderMapper {
    void addOne(ChildrenOrder childrenOrder);

    void deleteOne(ChildrenOrder childrenOrder);

    void updateOne(ChildrenOrder childrenOrder);

    ChildrenOrderPO findOneByUserIdAndOrderId(@Param("userId") Long userId, @Param("orderId") Long orderId);

    List<ChildrenOrderPO> findPageByOrderId(@Param("orderId") Long orderId, @Param("page") Integer page, @Param("limit") Integer limit);

    List<ChildrenOrderPO> findPageByUserId(@Param("userId") Long userId, @Param("page") Integer page, @Param("limit") Integer limit);

}