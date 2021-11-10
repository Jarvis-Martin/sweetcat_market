package com.sweetcat.geography.infrastructure.factory;

import com.sweetcat.geography.domain.geography.entity.Geography;
import com.sweetcat.geography.infrastructure.po.GeographyPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-14:11
 * @version: 1.0
 */
@Component
public class GeographyFactory {

    public Geography create(GeographyPO geographyPO) {
        return new Geography(
                geographyPO.getAddressCode(),
                geographyPO.getAddressName(),
                geographyPO.getProvinceCode(),
                geographyPO.getCityCode(),
                geographyPO.getAreaCode(),
                geographyPO.getTownCode(),
                geographyPO.getCreateTime(),
                geographyPO.getUpdateTime()
        );
    }
}
