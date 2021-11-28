package com.sweetcat.secondkill.interfaces.facade;

import com.sweetcat.secondkill.application.command.AddSKCommodityPostChargeCommand;
import com.sweetcat.secondkill.application.service.SKCommodityPostChargeApplicationService;
import com.sweetcat.secondkill.domain.commonditypostcharge.entity.SKCommodityPostCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-19:54
 * @version: 1.0
 */
@Component
public class SKCommodityPostChargeFacade {
    private SKCommodityPostChargeApplicationService postChargeApplicationService;

    @Autowired
    public void setPostChargeApplicationService(SKCommodityPostChargeApplicationService postChargeApplicationService) {
        this.postChargeApplicationService = postChargeApplicationService;
    }

    /**
     * 添加一个记录
     *
     * @param command command
     */
    public void addOne(AddSKCommodityPostChargeCommand command) {
        postChargeApplicationService.addOne(command);
    }

    /**
     * find post charge recorde by commodityId and provinceCode
     *
     * @param commodityId  commodityId
     * @param provinceCode provinceCode
     * @return
     */
    public SKCommodityPostCharge find(Long commodityId, String provinceCode) {
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
