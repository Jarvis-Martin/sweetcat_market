package com.sweetcat.footprint.infrastructure.repository;

import com.sweetcat.footprint.domain.footprint.entity.UserFootprint;
import com.sweetcat.footprint.domain.footprint.repository.UserFootprintRepository;
import com.sweetcat.footprint.infrastructure.dao.UserFootprintMapper;
import com.sweetcat.footprint.infrastructure.factory.UserFootprintFactory;
import com.sweetcat.footprint.infrastructure.po.UserFootprintPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-22:21
 * @version: 1.0
 */
@Repository
public class UserFootprintRepositoryImpl implements UserFootprintRepository {
    private UserFootprintMapper footprintMapper;
    private UserFootprintFactory footprintFactory;

    @Autowired
    public void setFootprintFactory(UserFootprintFactory footprintFactory) {
        this.footprintFactory = footprintFactory;
    }

    @Autowired
    public void setFootprintMapper(UserFootprintMapper footprintMapper) {
        this.footprintMapper = footprintMapper;
    }

    /**
     * 添加一条足迹记录
     *
     * @param footprint
     */
    @Override
    public void addOne(UserFootprint footprint) {
        footprintMapper.addOne(footprint);
    }

    /**
     * 删除一条足迹记录
     *
     * @param footprint
     */
    @Override
    public void deleteOne(UserFootprint footprint) {
        footprintMapper.deleteOne(footprint);
    }

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
    @Override
    public List<UserFootprint> findPageByDate(Long userId, @Param("date") Long date, @Param("page") Integer page, @Param("limit") Integer limit) {
        List<UserFootprintPO> footprintPOPage = footprintMapper.findPageByDate(userId, date, page, limit);
        if (footprintPOPage == null) {
            return null;
        }
        ArrayList<UserFootprint> footprintPage = footprintPOPage.stream().collect(
                ArrayList<UserFootprint>::new,
                (con, userFootprintPO) -> con.add(footprintFactory.create(userFootprintPO)),
                ArrayList::addAll
        );
        return footprintPage;
    }

    @Override
    public UserFootprint findByFootprintId(Long footprintId) {
        return footprintMapper.findByFootprintId(footprintId);
    }
}
