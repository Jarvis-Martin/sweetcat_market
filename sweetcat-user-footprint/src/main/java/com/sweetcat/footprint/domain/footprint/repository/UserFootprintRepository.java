package com.sweetcat.footprint.domain.footprint.repository;

import com.sweetcat.footprint.domain.footprint.entity.UserFootprint;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-22:20
 * @version: 1.0
 */
public interface UserFootprintRepository {

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
    List<UserFootprint> findPageByDate(Long userId, Long date, Integer page, Integer limit);

    /**
     * find footprint by footprint id
     * @param footprintId footprintId
     * @return
     */
    UserFootprint findByFootprintId(Long footprintId);

}
