package com.sweetcat.footprint.interfaces.facade.assembler;

import com.sweetcat.footprint.domain.carousel.entity.UserFootprint;
import com.sweetcat.footprint.interfaces.facade.restdto.UserFootprintRestDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-18:10
 * @version: 1.0
 */
@Component
public class UserFootprintAssembler {
    public UserFootprintRestDTO converterToUserFootprintRestDTO(UserFootprint footprint) {
        UserFootprintRestDTO userFootprintRestDTO = new UserFootprintRestDTO();
        userFootprintRestDTO.setFootprintId(footprint.getFootprintId().toString());
        userFootprintRestDTO.setUserId(footprint.getUserId().toString());
        userFootprintRestDTO.setCommodityId(footprint.getCommodityId().toString());
        userFootprintRestDTO.setPicSmall(footprint.getPicSmall());
        userFootprintRestDTO.setPriceWhenBrowser(footprint.getPriceWhenBrowser());
        userFootprintRestDTO.setStartTime(footprint.getStartTime());
        userFootprintRestDTO.setBrowserDuration(footprint.getBrowserDuration());
        return userFootprintRestDTO;
    }
}
