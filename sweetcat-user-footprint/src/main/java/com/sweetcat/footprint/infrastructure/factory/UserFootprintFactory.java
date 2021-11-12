package com.sweetcat.footprint.infrastructure.factory;

import com.sweetcat.footprint.domain.footprint.entity.UserFootprint;
import com.sweetcat.footprint.infrastructure.po.UserFootprintPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-22:22
 * @version: 1.0
 */
@Component
public class UserFootprintFactory {
    public UserFootprint create(UserFootprintPO footprintPO) {
        UserFootprint footprint = new UserFootprint(footprintPO.getFootprintId(), footprintPO.getUserId(), footprintPO.getCommodityId());
        footprint.setPicSmall(footprintPO.getPicSmall());
        footprint.setPriceWhenBrowser(footprintPO.getPriceWhenBrowser());
        footprint.setStartTime(footprintPO.getStartTime());
        footprint.setBrowserDuration(footprintPO.getBrowserDuration());
        return footprint;
    }
}
