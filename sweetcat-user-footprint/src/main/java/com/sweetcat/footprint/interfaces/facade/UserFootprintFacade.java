package com.sweetcat.footprint.interfaces.facade;

import com.sweetcat.footprint.application.command.AddUserFootprintCommand;
import com.sweetcat.footprint.application.service.UserFootprintApplicationService;
import com.sweetcat.footprint.domain.carousel.entity.UserFootprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-22:44
 * @version: 1.0
 */
@Component
public class UserFootprintFacade {
    private UserFootprintApplicationService footprintApplicationService;

    @Autowired
    public void setFootprintApplicationService(UserFootprintApplicationService footprintApplicationService) {
        this.footprintApplicationService = footprintApplicationService;
    }

    /**
     * 添加一条足迹记录
     *
     * @param command
     */
    public void addOne(AddUserFootprintCommand command) {
        footprintApplicationService.addOne(command);
    }

    /**
     * 删除一条足迹记录
     *
     * @param footprintId
     */
    public void deleteOne(Long footprintId) {
        footprintApplicationService.deleteOne(footprintId);
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
    public List<UserFootprint> findByDate(Long userId, Long date, Integer page, Integer limit) {
        return footprintApplicationService.findByDate(userId, date, page, limit);
    }

}
