package com.sweetcat.geography.interfaces.facade.assembler;

import com.sweetcat.geography.domain.geography.entity.Geography;
import com.sweetcat.geography.interfaces.facade.restdto.GeographyRestDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-15:56
 * @version: 1.0
 */
@Component
public class RestDTOAssembler {
    public GeographyRestDTO converterGeographyRestDTO(Geography geography) {
        GeographyRestDTO geographyRpcDTO = new GeographyRestDTO();
        geographyRpcDTO.setAddressCode(geography.getAddressCode());
        geographyRpcDTO.setAddressName(geography.getAddressName());
        geographyRpcDTO.setProvinceCode(geography.getProvinceCode());
        geographyRpcDTO.setCityCode(geography.getCityCode());
        geographyRpcDTO.setAreaCode(geography.getAreaCode());
        geographyRpcDTO.setTownCode(geography.getTownCode());
        return geographyRpcDTO;
    }
}
