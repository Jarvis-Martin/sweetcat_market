package com.sweetcat.footprint.infrastructure.dao;

import com.sweetcat.footprint.domain.footprint.entity.UserFootprint;
import com.sweetcat.footprint.infrastructure.po.UserFootprintPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFootprintMapper {

    /**
     * 添加一条足迹记录
     *
     * @param footprint
     */
    void addOne(UserFootprint footprint);

    /**
     * 删除一条足迹记录
     *
     * @param footprint
     */
    void deleteOne(UserFootprint footprint);

    /**
     * 根据时间戳查找该时间戳之前的足迹分页数据
     *
     *
     * @param userId
     * @param date  时间戳
     * @param page  page
     * @param limit limit
     * @return
     */
    List<UserFootprintPO> findPageByDate(@Param("userId") Long userId, @Param("date") Long date, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * find footprint by footprint id
     *
     * @param footprintId
     * @return
     */
    UserFootprint findByFootprintId(Long footprintId);
}