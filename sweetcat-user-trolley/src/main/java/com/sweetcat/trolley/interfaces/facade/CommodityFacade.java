package com.sweetcat.trolley.interfaces.facade;

import com.sweetcat.trolley.application.command.AddTrolleyCommodityCommand;
import com.sweetcat.trolley.application.service.CommodityApplicationService;
import com.sweetcat.trolley.domain.commodity.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-21:52
 * @version: 1.0
 */
@Component
public class CommodityFacade {
    private CommodityApplicationService commodityApplicationService;

    @Autowired
    public void setCommodityApplicationService(CommodityApplicationService commodityApplicationService) {
        this.commodityApplicationService = commodityApplicationService;
    }

    public void addOneCommodity(AddTrolleyCommodityCommand command) {
        commodityApplicationService.addOne(command);
    }

    public void removeOne(Long userId, Long commodityId) {
        commodityApplicationService.removeOne(userId, commodityId);
    }

    public void reduceCount(Long userId, Long commodityId) {
        commodityApplicationService.reduceCount(userId, commodityId);
    }

    public void increaseCount(Long userId, Long commodityId) {
        commodityApplicationService.increaseCount(userId, commodityId);
    }

    public List<Commodity> findPageTrolleyCommodities(Long userId, Integer page, Integer limit) {
        return commodityApplicationService.findPageTrolleyCommodities(userId, page, limit);
    }

}
