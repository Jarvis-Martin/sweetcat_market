package com.sweetcat.storecommodity.interfaces.facade;

import com.sweetcat.storecommodity.application.command.AddCommodityPostChargeCommand;
import com.sweetcat.storecommodity.application.service.CommodityPostChargeApplicationService;
import com.sweetcat.storecommodity.domain.commonditypostcharge.entity.CommodityPostCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-19:54
 * @version: 1.0
 */
@Component
public class CommodityPostChargeFacade {
    private CommodityPostChargeApplicationService postChargeApplicationService;

    @Autowired
    public void setPostChargeApplicationService(CommodityPostChargeApplicationService postChargeApplicationService) {
        this.postChargeApplicationService = postChargeApplicationService;
    }

    /**
     * 添加一个记录
     *
     * @param charge charge
     */
    public void addOne(AddCommodityPostChargeCommand command) {
        postChargeApplicationService.addOne(command);
    }

    /**
     * find post charge recorde by commodityId and provinceCode
     *
     * @param commodityId  commodityId
     * @param provinceCode provinceCode
     * @return
     */
    public CommodityPostCharge find(Long commodityId, String provinceCode) {
        return postChargeApplicationService.find(commodityId, provinceCode);
    }

    /**
     * remove post charge recorde by commodity id and province code
     *
     * @param chargeId chargeId
     */
    public void remove(Long chargeId) {
        postChargeApplicationService.remove(chargeId);
    }
}
