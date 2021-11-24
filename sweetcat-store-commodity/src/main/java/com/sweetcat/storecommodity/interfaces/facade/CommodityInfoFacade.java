package com.sweetcat.storecommodity.interfaces.facade;

import com.sweetcat.storecommodity.application.command.AddStoreCommodityCommand;
import com.sweetcat.storecommodity.application.service.CommodityInfoApplicationService;
import com.sweetcat.storecommodity.domain.commodityinfo.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-15:32
 * @Version: 1.0
 */
@Component
public class CommodityInfoFacade {
    private CommodityInfoApplicationService commodityInfoApplicationService;

    @Autowired
    public void setCommodityInfoApplicationService(CommodityInfoApplicationService commodityInfoApplicationService) {
        this.commodityInfoApplicationService = commodityInfoApplicationService;
    }

    public Commodity findByCommodityId(Long commodityId) {
        return commodityInfoApplicationService.findByCommodityId(commodityId);
    }

    public List<Commodity> findPageByStoreId(Long storeId, Integer page, Integer limit) {
        return commodityInfoApplicationService.findPageByStoreId(storeId, page, limit);
    }

    public List<Commodity> findPageNewCommodities(Integer page, Integer limit) {
        return commodityInfoApplicationService.findPageNewCommodities(page, limit);
    }
    public void addOne(AddStoreCommodityCommand addStoreCommodityCommand) {
        commodityInfoApplicationService.addOne(addStoreCommodityCommand);
    }

    public List<Commodity> findPageCreditCanOffsetAPart(Integer page, Integer limit) {
        return commodityInfoApplicationService.findPageCreditCanOffsetAPart(page, limit);
    }
}
