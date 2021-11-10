package com.sweetcat.geography.interfaces.facade.assembler;

import com.sweetcat.api.rpcdto.geography.GeographyRpcDTO;
import com.sweetcat.geography.domain.geography.entity.Geography;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-15:13
 * @version: 1.0
 */
@Component
public class RpcDTOAssembler {
    public GeographyRpcDTO converterGeographyRpcDTO(Geography geography) {
        GeographyRpcDTO geographyRpcDTO = new GeographyRpcDTO();
        geographyRpcDTO.setAddressCode(geography.getAddressCode());
        geographyRpcDTO.setAddressName(geography.getAddressName());
        geographyRpcDTO.setProvinceCode(geography.getProvinceCode());
        geographyRpcDTO.setCityCode(geography.getCityCode());
        geographyRpcDTO.setAreaCode(geography.getAreaCode());
        geographyRpcDTO.setTownCode(geography.getTownCode());
        return geographyRpcDTO;
    }
}
