package com.sweetcat.credit.interfaces.facade;

import com.sweetcat.credit.application.service.CommodityApplicationService;
import com.sweetcat.credit.domain.commodity.entity.BaseCommodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/17-20:41
 * @version: 1.0
 */
@Component
public class CommodityFacade {
    private CommodityApplicationService commodityApplicationService;

    @Autowired
    public void setCommodityApplicationService(CommodityApplicationService commodityApplicationService) {
        this.commodityApplicationService = commodityApplicationService;
    }

    /**
     * 根据 商品分类查找商品分页数据
     *
     * @param commodityType
     * @param page
     * @param limit
     * @return
     */
    public List<BaseCommodity> findPageByCommodityType(Integer commodityType, Integer page, Integer limit) {
        return commodityApplicationService.findPageByCommodityType(commodityType, page, limit);
    }
}
