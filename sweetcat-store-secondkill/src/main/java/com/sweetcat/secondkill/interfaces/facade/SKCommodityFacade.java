package com.sweetcat.secondkill.interfaces.facade;

import com.sweetcat.secondkill.application.command.AddSKCommodityCommand;
import com.sweetcat.secondkill.application.service.SKCommodityApplicationService;
import com.sweetcat.secondkill.domain.commodity.entity.SKCommodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/27-17:23
 * @version: 1.0
 */
@Component
public class SKCommodityFacade {
    private SKCommodityApplicationService skCommodityApplicationService;

    @Autowired
    public void setSkCommodityApplicationService(SKCommodityApplicationService skCommodityApplicationService) {
        this.skCommodityApplicationService = skCommodityApplicationService;
    }

    /**
     * 增加一件商品记录
     * @param command
     */
    public void addOne(AddSKCommodityCommand command) {
        skCommodityApplicationService.addOne(command);
    }

    /**
     * 移除秒杀商品
     * @param commodityId
     */
    public void removeOne(Long commodityId) {
        skCommodityApplicationService.removeOne(commodityId);
    }

    /**
     * 查找给定时间所在区间内的秒杀商品分页数据
     * @param currentTimeStamp
     * @param page
     * @param limit
     * @return
     */
    public List<SKCommodity> findPageByTime(Long currentTimeStamp, Integer page, Integer limit) {
        return skCommodityApplicationService.findPageByTime(currentTimeStamp, page, limit);
    }

    /**
     * 查找秒杀商品的详细信息
     * @param commodityId
     * @return
     */
    public SKCommodity findOneByCommodityId(Long commodityId) {
        return skCommodityApplicationService.findOneByCommodityId(commodityId);
    }
}
